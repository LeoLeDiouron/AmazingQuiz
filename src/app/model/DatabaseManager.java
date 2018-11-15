package app.model;

import app.controller.Question;
import app.controller.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.System.exit;

public class DatabaseManager {

    private Connection _connection;
    private Statement _statement;

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

    public ResultSet exeResQuery(String query) {

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

    public void exeQuery(String query) {
        try {
            _statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
        }
    }

    public ResultSet getUsers() {
        return exeResQuery("SELECT * FROM USERS");
    }

    public ResultSet getUsersClassment() {return exeResQuery("SELECT * FROM USERS ORDER BY score DESC");}

    public ResultSet getQuestions() {
        return exeResQuery("SELECT * FROM QUESTIONS;");
    }

    public ResultSet updateNameUser(User user, String name) {
        return  exeResQuery("UPDATE USERS SET name=\'"+name+"\' WHERE name = \'"+user.getName() + "\'");}

    public void deleteUser(User user) {
        exeQuery("DELETE FROM USERS WHERE name=\'" + user.getName() + "\'");
    }

    public void createUser(int id, String name, int score) {
        try {
            String sql = "INSERT INTO users (id,name,score) VALUES (" + Integer.toString(id) + ",'" + name + "'," + Integer.toString(score) + ");";
            _statement.executeQuery(sql);
        } catch (java.sql.SQLException e) {
            System.out.printf("java.sql.SQLException : " + e.getMessage());
        }
    }

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

    public void updateMaxScore(User user) {
        exeQuery("UPDATE USERS SET score=" + Integer.toString(user.getMaxScore()) + " WHERE name=\'" + user.getName() + "\';");
    }
}
