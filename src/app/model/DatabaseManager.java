package app.model;

import app.controller.Question;
import app.controller.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.System.exit;

/*
description: the class "DatabaseManager" links the project with the Postgres database
*/
public class DatabaseManager {

    private Connection _connection;
    private Statement _statement;

    /*
    description: constructor of the class, initalises the connection with the database
    return: nothing
    params: nothing
    */
    public DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
            _connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/amazing_quiz",
                            "postgres", "loukoum");
            _statement = _connection.createStatement();
        } catch (java.lang.ClassNotFoundException e) {
            System.out.printf("java.lang.ClassNotFoundException : " + e.getMessage());
            exit(0);
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
            exit(0);
        }
    }

    /*
    description: execute a query and return the result
    return: ResultSet - the result of the query
    params: String - the query to execute
    */
    private ResultSet exeResQuery(String query) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e + "\n");
            exit(0);
        }

        try {
            ResultSet rs = _statement.executeQuery(query);
            return rs;
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
        }
        return null;
    }

    /*
    description: execute a query
    return: nothing
    params: String - the query to execute
    */
    private void exeQuery(String query) {
        try {
            _statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
        }
    }

    /*
    description: get all the users of the database
    return: ResultSet - the result from the database
    params: nothing
    */
    public ResultSet getUsers() {
        return exeResQuery("SELECT * FROM USERS");
    }

    /*
    description: get all the users of the database, sorted by their best score
    return: ResultSet - the result from the database
    params: nothing
    */
    public ResultSet getUsersClassment() {return exeResQuery("SELECT * FROM USERS ORDER BY score DESC");}

    /*
    description: get all the questions
    return: ResultSet - the result from the database
    params: nothing
    */
    public ResultSet getQuestions() {
        return exeResQuery("SELECT * FROM QUESTIONS;");
    }

    /*
    description: change the name of a user in the database
    return: nothing
    params: User - the user who change its name
    params: String - the new name
    */
    public void updateNameUser(User user, String name) {
        exeQuery("UPDATE USERS SET name=\'"+name+"\' WHERE name = \'"+user.getName() + "\'");
    }

    /*
    description: delete a user of the database
    return: nothing
    params: User - the user to delete
    */
    public void deleteUser(User user) {
        exeQuery("DELETE FROM USERS WHERE name=\'" + user.getName() + "\'");
    }

    /*
    description: add a new user to the database
    return: nothing
    params: Integer - id of the user
    params: String - name of the user
    params: Integer - score of the user
    */
    public void createUser(int id, String name, int score) {
        try {
            String sql = "INSERT INTO users (id,name,score) VALUES (" + Integer.toString(id) + ",'" + name + "'," + Integer.toString(score) + ");";
            _statement.executeQuery(sql);
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
        }
    }

    /*
    description: add a question to the database
    return: nothing
    params: Question - question to add
    */
    public void createQuestion(Question question) {
        ResultSet res = exeResQuery("SELECT count(id) as nb FROM QUESTIONS");
        try {
            res.next();
            int id = res.getInt("nb") + 1;

            String answers = "'{";
            ArrayList<String> answers_list = question.getAnswers();
            for (int i=0;i<answers_list.size();i++) {
                if (answers_list.get(i).length() > 0)
                    answers += answers_list.get(i) + ",";
            }
            answers = answers.substring(0,answers.length()-1) + "}'";

            String good_answers = "'{";
            Integer[] good_answers_list = question.getIndexGoodAnswer();
            for (int i=0;i<good_answers_list.length;i++) {
                good_answers += String.valueOf(good_answers_list[i]) + ",";
            }
            good_answers = good_answers.substring(0,good_answers.length()-1) + "}'";

            exeQuery("INSERT INTO QUESTIONS (id,author,content,answers,good_answers,type_quiz) VALUES (" + Integer.toString(id) + ",\'" + question.getAuthor() + "\',\'" + question.getContent() + "\'," + answers + "," + good_answers + "," + question.getTypeQuiz() + ");");
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
        }
    }

    /*
    description: update the best score of a user
    return: nothing
    params: User - the user who update its new best score
    */
    public void updateMaxScore(User user) {
        exeQuery("UPDATE USERS SET score=" + Integer.toString(user.getMaxScore()) + " WHERE name=\'" + user.getName() + "\';");
    }
}
