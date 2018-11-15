package app.controller;

import app.model.DatabaseManager;
import app.view.GUI;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
description: the class "App" is the main class of controllers, and do the link between the GUI and the database
*/
public class App {

    private GUI _frame;
    private DatabaseManager _db_manager;
    private UsersManager _users_manager;
    private User _user;
    private QuestionsManager _question_manager;

    /*
    description: constructor of the class, initialises the database manager, the GUI, and the managers of objects Questions and Users
    return: nothing
    params: nothing
    */
    public App() {
        _db_manager = new DatabaseManager();
        _frame = new GUI("Amazing Quiz");
        _users_manager = new UsersManager();
        _question_manager = new QuestionsManager();
    }

    /*
    description: Called by the main, launch the project
    return: nothing
    params: nothing
    */
    public void run() {
        setUsers();
        setGUI();
    }

    /*
    description: initialise the GUI
    return: nothing
    params: nothing
    */
    public void setGUI() {
        _frame.getMenu().displayUserSelection(this, _users_manager.getUsers());
        _frame.setSize(500,500);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setVisible(true);
    }

    /*
    description: ask to the GUI to display the menu of the game
    return: nothing
    params: User user - object used to identify the user (the user is defined at the launch of the application)
    */
    public void launchMenu(User user) {
        _user = user;
        _frame.newGame();
        _frame.getMenu().displayMenu(this);
    }

    /*
    description: ask to the GUI to display the "Delete Account" menu (to delete an account)
    return: nothing
    params: nothing
    */
    public void deleteMenu() {
        _frame.getMenu().displayDeleteAccount(this);
    }

    /*
    description: ask to the GUI to display the "Question" menu (to create a question)
    return: nothing
    params: nothing
    */
    public void questionMenu() {
        _frame.getMenu().displayCreateQuestion(this, "");
    }

    /*
    description: ask to the GUI to display the user selection, at the launch of the application
    return: nothing
    params: nothing
    */
    public void userSelection() {
        _frame.getMenu().displayUserSelection(this, _users_manager.getUsers());
    }

    /*
    description: ask to the GUI to display the name menu
    return: nothing
    params: nothing
    */
    public void changeNameMenu() {_frame.getMenu().displayChangeName(this);}

    /*
    description: ask to the GUI to display the menu
    return: nothing
    params: nothing
    */
    public void returnMenu() {
        _frame.getMenu().displayMenu(this);
    }

    /*
    description: change the name of the user
    return: nothing
    params: String - new name of the user
    */
    public void changeNameUser(String name) {
        name = name.replace(",","").replace("'", "").replace("\"", "");
        _db_manager.updateNameUser(_user, name);
        _user.setName(name);
        _frame.getMenu().displayMenu(this);
    }

    /*
    description: delete the account of the current user
    return: nothing
    params: nothing
    */
    public void deleteUser() {
        _users_manager.deleteUser(_user);
        _db_manager.deleteUser(_user);
        _frame.getMenu().displayUserSelection(this, _users_manager.getUsers());
    }

    /*
    description: play to the game. If the game did not start, initialises the question. If the game is ended, ask to GUI to display the score
    return: nothing
    params: nothing
    */
    public void play() {

        if (_question_manager.getIndexQuestion() == 0) {
            ResultSet result = _db_manager.getQuestions();
            try {
                while (result.next()) {
                    String[] answers = (String[]) result.getArray("answers").getArray();
                    Integer[] good_answers = (Integer[]) result.getArray("good_answers").getArray();
                    ArrayList<String> answers_list = new ArrayList<>();
                    for (int i = 0; i < answers.length; i++)
                        answers_list.add(answers[i]);
                    _question_manager.addQuestion(new Question(
                            result.getString("author"), result.getString("content"), answers_list, good_answers, result.getInt("type_quiz")
                    ));
                }
            } catch (java.sql.SQLException e) {
                System.out.printf("java.sql.SQLException : " + e.getMessage());
            }
        }
        if (_question_manager.getIndexQuestion() < _question_manager.getNumberQuestions()) {
            _frame.getGame().displayGame(this);

        }
        else {
            _frame.getGame().displayEndGame(this);
            _question_manager.clearQuestion();
            _question_manager.resetIndexQuestion();
            _frame.newGame();
        }
    }


    /*
    description: validate if the answer given by the user is correct, and ask to GUI to display right or false
    return: nothing
    params: String answer - answer given by the user in the GUI
    */
    public void validateAnswer(String answer) {
        boolean result = _question_manager.getQuestion().checkGoodAnswers(answer);
        if (result) {
            _user.increaseScore();
            _frame.getGame().displayQuestionOk(this);
        } else {
            _frame.getGame().displayQuestionWrong(this, _question_manager.getQuestion().getGoodAnswer());
        }
        _question_manager.increaseIndexQuestion();
    }

    /*
    description: get the list of the score of the users, and ask to GUI to display the classment
    return: nothing
    params: nothing
    */
    public void classment() {
        ResultSet res_list_players = _db_manager.getUsersClassment();
        ArrayList<User> list_players = new ArrayList<User>();
        try {
            while (res_list_players.next()) {
                list_players.add(new User(res_list_players.getString("name"), res_list_players.getInt("score")));
            }
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
        }
        _frame.getMenu().displayClassment(this, list_players);
    }

    /*
    description: add a new user
    return: nothing
    params: String name - name of the user
    */
    public int createUser(String name) {
        name = name.replace(",","").replace("'", "").replace("\"", "");
        int id = _users_manager.countUsers()+1;
        _db_manager.createUser(id,name,0);
        _users_manager.addUser(new User(name,0));
        return id-1;
    }

    /*
    description: add the existing users to the user manager
    return: nothing
    params: nothing
    */
    private void setUsers() {
        ResultSet result = _db_manager.getUsers();
        try {
            while (result.next()) {
                _users_manager.addUser(new User(result.getString("name"),result.getInt("score")));
            }
        } catch (java.sql.SQLException e) {
            System.out.print("java.sql.SQLException : " + e.getMessage());
        }
    }

    /*
    description: if the user has new high score, saves it, and set his current score to 0
    return: nothing
    params: nothing
    */
    public void updateScore() {
        if (_user.getScore() > _user.getMaxScore()) {
            _user.setMaxScore(_user.getScore());
            _db_manager.updateMaxScore(_user);
        }
        _user.setScore(0);
        _frame.getMenu().displayMenu(this);
    }

    /*
    description: check if the created question is valid
    return: String - The message of success / fail
    params: String good_answer - the index of the good question (has to be between 1 and 4)
    params: String type_question - the index of the type of question (has to be between 1 and 4)
    */
    private String checkValidQuestion(String good_answer, String type_question) {

        try {
            int tmp = Integer.parseInt(type_question);
            if (tmp < 1 || tmp > 4)
                return "Error - type of question is invalid";
        } catch (NumberFormatException e) {
            return "Error - type of question is not a number";
        }

        if (good_answer.contains(";")) {
            for (int i=0;i<good_answer.split(";").length;i++) {
                try {
                    int tmp = Integer.parseInt(good_answer.split(";")[i]);
                    if (tmp < 1 || tmp > 4)
                        return "Error - one of the answer index is invalid";
                } catch (NumberFormatException e) {
                    return "Error - one of the answer index is not a number";
                }
            }
        }
        if (Integer.parseInt(type_question) != 3) {
            try {
                int tmp = Integer.parseInt(good_answer);
                if (tmp < 1 || tmp > 4)
                    return "Error - answer index is invalid";
            } catch (NumberFormatException e) {
                return "Error - answer index is not a number";
            }
        }

        return "Question added !";
    }

    /*
    description: create a new question in the database
    return: nothing
    params: String content - the question
    params: ArrayList<String> answers - the avaible answers for the question
    params: String good_answers - the index of the good answer(s)
    params: String type_question - the index of the type of question
    */
    public void createQuestion(String content, ArrayList<String> answers, String good_answers, String type_question) {

        String message = checkValidQuestion(good_answers, type_question);

        if (message.equals("Question added !")) {
            String[] str_modify_good_answers = good_answers.split(";");
            Integer[] modify_good_answers = new Integer[str_modify_good_answers.length];

            for (int i = 0; i < str_modify_good_answers.length; i++) {
                modify_good_answers[i] = Integer.parseInt(str_modify_good_answers[i]) - 1;
            }
            Question question = new Question(
                    _user.getName(), content, answers, modify_good_answers, Integer.parseInt(type_question)
            );
            _db_manager.createQuestion(question);
        }
        _frame.getMenu().displayCreateQuestion(this, message);
    }

    /*
    description: get the current user
    return: User - current user
    params: nothing
    */
    public User getUser() {
        return _user;
    }

    /*
    description: get the questions manager
    return: QuestionManager - current questions manager
    params: nothing
    */
    public QuestionsManager getQuestionManager(){
        return _question_manager;
    }
}
