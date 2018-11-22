package app.view;

import app.controller.App;
import app.controller.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
description: the class "Menu" manages the displaying of all the parts of the menu of the game
*/
public class Menu {

    private JPanel _panel;
    private Border _border;

    /*
    description: constructor of the class, get the object panel and border from the GUI class
    return: nothing
    params: JPanel - panel to display informations
    params: Border - border created by the GUI class
    */
    public Menu(JPanel panel, Border border) {
        _panel = panel;
        _border = border;
    }

    /*
    description: displays the connection to the game, where the user has to create a new player or select an existing one
    return: nothing
    params: App - class App which communicate with the GUI
    params: ArrayList<User> - list of the users from the database
    */
    public void displayUserSelection(final App app, final ArrayList<User> users) {

        JLabel text_title = new JLabel("THE AMAZING QUIZ");
        JLabel text_create = new JLabel("Create a new account...");
        JLabel text_select = new JLabel("... or select an existing one");
        final JTextArea textarea_name = new JTextArea(1,10);
        textarea_name.setBorder(BorderFactory.createCompoundBorder(_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JButton button_create = new JButton("Validate");
        ArrayList<JButton> users_components = new ArrayList<JButton>();
        for (int i=0; i < users.size();i++) {
            users_components.add(new JButton(users.get(i).getName()));
            final int _i = i;
            users_components.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.launchMenu(users.get(_i));
                }
            });
        }
        button_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = app.createUser(textarea_name.getText());
                app.launchMenu(users.get(id));
            }
        });
        _panel.removeAll();
        _panel.add(text_title);
        _panel.add(text_create);
        _panel.add(textarea_name);
        _panel.add(button_create);
        _panel.add(text_select);
        for (int i=0;i<users_components.size();i++) {
            _panel.add(users_components.get(i));
        }
        _panel.revalidate();
        _panel.repaint();
    }

    /*
    description: displays all the avaible features of the menu
    return: nothing
    params: App - class App which communicate with the GUI
    */
    public void displayMenu(final App app) {
        JLabel text_title = new JLabel("Welcome " + app.getUser().getName() + ", your best score is " + Integer.toString(app.getUser().getMaxScore()) + "/10.");
        JButton button_play = new JButton("Play");
        JButton button_question = new JButton("Create a question");
        JButton button_classment = new JButton("See the classment");
        JButton button_changename = new JButton("Change my name");
        JButton button_delete = new JButton("Delete my account");
        JButton button_quit = new JButton("Return to user selection");

        button_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.play();
            }
        });
        button_question.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.questionMenu();
            }
        });
        button_classment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.classment();
            }
        });
        button_changename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.changeNameMenu();
            }
        });
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.deleteMenu();
            }
        });
        button_quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.userSelection();
            }
        });

        _panel.removeAll();
        _panel.add(text_title);
        _panel.add(button_play);
        _panel.add(button_question);
        _panel.add(button_classment);
        _panel.add(button_changename);
        _panel.add(button_delete);
        _panel.add(button_quit);
        _panel.revalidate();
        _panel.repaint();
    }

    /*
    description: displays the list of users sorted by their best score
    return: nothing
    params: App - class App which communicate with the GUI
    params: ArrayList<User> - list of users sorted by their best score
    */
    public void displayClassment(final App app, ArrayList<User> list_players) {
        JLabel text_title = new JLabel("Classment");
        JButton button_quit = new JButton("Return to menu");
        String classment = "<html>";
        for (int i = 0; i < list_players.size(); i++) {
            classment += list_players.get(i).getName() + " : " + String.valueOf(list_players.get(i).getMaxScore()) + "<br/>";
        }
        classment += "</html>";
        JLabel text_classment = new JLabel(classment);

        button_quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.returnMenu();
            }
        });

        _panel.removeAll();
        _panel.add(text_title);
        _panel.add(text_classment);
        _panel.add(button_quit);
        _panel.revalidate();
        _panel.repaint();
    }

    /*
    description: displays the menu to change the name of the player
    return: nothing
    params: App - class App which communicate with the GUI
    */
    public void displayChangeName(final App app) {
        JLabel text_title = new JLabel("Write your new name");
        final JTextArea textarea_name = new JTextArea();
        JButton button_validate = new JButton("Validate");
        JButton button_cancel = new JButton("Cancel");

        button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.changeNameUser(textarea_name.getText());
            }
        });
        button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.launchMenu(app.getUser());
            }
        });

        _panel.removeAll();
        _panel.add(text_title);
        _panel.add(textarea_name);
        _panel.add(button_validate);
        _panel.add(button_cancel);
        _panel.revalidate();
        _panel.repaint();
    }

    /*
    description: displays the menu to delete the player
    return: nothing
    params: App - class App which communicate with the GUI
    */
    public void displayDeleteAccount(final App app) {
        JLabel text_title = new JLabel("Are you sure to delete your account ?");
        JButton button_validate = new JButton("Yes");
        JButton button_cancel = new JButton("No");

        button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.deleteUser();
            }
        });
        button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.launchMenu(app.getUser());
            }
        });

        _panel.removeAll();
        _panel.add(text_title);
        _panel.add(button_validate);
        _panel.add(button_cancel);
        _panel.revalidate();
        _panel.repaint();
    }

    /*
    description: displays the menu to create a new question
    return: nothing
    params: App - class App which communicate with the GUI
    params: String - the message to display after the user has created a question (success or fail)
    */
    public void displayCreateQuestion(final App app, String message) {

        JLabel text_title = new JLabel("Create a new question");
        JLabel text_message = new JLabel(message);
        final JTextArea content_question = new JTextArea();
        final JTextArea answers_area[] = new JTextArea[4];
        final String numbers[] = {"first", "second", "third", "fourth"};
        final JTextArea correct_answer = new JTextArea();
        final JTextArea type_quiz = new JTextArea();
        JButton button_validate = new JButton("Validate");
        JButton button_cancel = new JButton("Cancel");

        content_question.setBorder(BorderFactory.createCompoundBorder(_border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        for (int i=0;i<answers_area.length;i++) {
            answers_area[i] = new JTextArea();
            answers_area[i].setBorder(BorderFactory.createCompoundBorder(_border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            answers_area[i].append("Your " + numbers[i] + " answer here");
        }
        correct_answer.setBorder(BorderFactory.createCompoundBorder(_border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        content_question.append("Your question here...");
        correct_answer.append("What is the correct answer ? (between 1 and 4)");
        type_quiz.append("What is the type of your question (1:radiobutton, 2:dropdown list, 3:checkbox, 4:field) ?");
        button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> answers = new ArrayList<>();
                for (int i=0;i<answers_area.length;i++) {
                    answers.add(answers_area[i].getText().replace(',', ' ').replace('\'', ' ').replace('"',' '));
                }
                app.createQuestion(content_question.getText(), answers, correct_answer.getText(), type_quiz.getText());
            }
        });
        button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.returnMenu();
            }
        });

        _panel.removeAll();
        _panel.add(text_title);
        if (message != "") {
            _panel.add(text_message);
        }
        _panel.add(content_question);
        for (int i=0;i<answers_area.length;i++) {
            _panel.add(answers_area[i]);
        }
        _panel.add(correct_answer);
        _panel.add(type_quiz);
        _panel.add(button_validate);
        _panel.add(button_cancel);
        _panel.revalidate();
        _panel.repaint();
    }

}
