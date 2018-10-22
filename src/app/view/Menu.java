package app.view;

import app.controller.App;
import app.controller.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu {

    private JPanel _panel;
    private Border _border;

    public Menu(JPanel panel, Border border) {
        _panel = panel;
        _border = border;
    }

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

    public void displayMenu(final App app) {
        JLabel text_title = new JLabel("Welcome " + app.getUser().getName() + ", your best score is " + Integer.toString(app.getUser().getMaxScore()) + "/10.");
        JButton button_play = new JButton("Play");
        JButton button_question = new JButton("Create a question");
        JButton button_classment = new JButton("See the classment");
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
        _panel.add(button_delete);
        _panel.add(button_quit);
        _panel.revalidate();
        _panel.repaint();
    }

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

    public void displayQuestionMenu(final App app) {
        JLabel text_title = new JLabel("Create a new question");
        final JTextArea content_question = new JTextArea();
        final JTextArea first_answer = new JTextArea();
        final JTextArea second_answer = new JTextArea();
        final JTextArea third_answer = new JTextArea();
        final JTextArea fourth_answer = new JTextArea();
        final JTextArea correct_answer = new JTextArea();
        final JTextArea type_quiz = new JTextArea();
        JButton button_validate = new JButton("Validate");
        JButton button_cancel = new JButton("Cancel");

        content_question.setBorder(BorderFactory.createCompoundBorder(_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        first_answer.setBorder(BorderFactory.createCompoundBorder(_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        second_answer.setBorder(BorderFactory.createCompoundBorder(_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        third_answer.setBorder(BorderFactory.createCompoundBorder(_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        fourth_answer.setBorder(BorderFactory.createCompoundBorder(_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        correct_answer.setBorder(BorderFactory.createCompoundBorder(_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        content_question.append("Your question here...");
        first_answer.append("Your first answer here...");
        second_answer.append("Your second answer here...");
        third_answer.append("Your third answer here...");
        fourth_answer.append("Your fourth answer here...");
        correct_answer.append("What is the correct answer ? (between 1 and 4)");
        type_quiz.append("What is the type of your question (1:radiobutton, 2:dropdown list, 3:checkbox, 4:field) ?");
        button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> answers = new ArrayList<>();
                answers.add(first_answer.getText());
                answers.add(second_answer.getText());
                answers.add(third_answer.getText());
                answers.add(fourth_answer.getText());
                app.createQuestion(content_question.getText(), answers, correct_answer.getText(), Integer.parseInt(type_quiz.getText()));
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
        _panel.add(content_question);
        _panel.add(first_answer);
        _panel.add(second_answer);
        _panel.add(third_answer);
        _panel.add(fourth_answer);
        _panel.add(correct_answer);
        _panel.add(type_quiz);
        _panel.add(button_validate);
        _panel.add(button_cancel);
        _panel.revalidate();
        _panel.repaint();
    }

}
