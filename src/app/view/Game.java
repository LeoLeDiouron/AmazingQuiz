package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
description: the class "Game" manages the GUI about the game itself (displaying of the question, of the score...)
*/
public class Game {

    private JPanel _panel;
    private Border _border;

    private GUIRadioButtonQuestion _radio_button_question;
    private GUICheckboxQuestion _checkbox_question;
    private GUIDropdownQuestion _dropdown_question;
    private GUIFieldQuestion _field_question;

    /*
    description: constructor of the class, get the object panel and border from the GUI class
    return: nothing
    params: JPanel - panel to display informations
    params: Border - border created by the GUI class
    */
    public Game(JPanel panel, Border border) {
        _panel = panel;
        _border = border;
        init_gui_questions();
    }

    /*
    description: initialises the class of questions
    return: nothing
    params: nothing
    */
    private void init_gui_questions() {
        _radio_button_question = new GUIRadioButtonQuestion(_panel);
        _checkbox_question = new GUICheckboxQuestion(_panel);
        _dropdown_question = new GUIDropdownQuestion(_panel);
        _field_question = new GUIFieldQuestion(_panel);
    }

    /*
    description: display the questions in the game
    return: nothing
    params: App - class App which communicate with the GUI
    */
    public void displayGame(final App app) {
        JLabel text_title = new JLabel("Question number " + (app.getQuestionManager().getIndexQuestion()+1) + "/10 - Actual Score : " + app.getUser().getScore() + "/10");
        _panel.removeAll();
        Question question = app.getQuestionManager().getQuestion();
        JLabel text_question = new JLabel(question.getContent() + "(by " + question.getAuthor() + ")");
        _panel.add(text_title);
        _panel.add(text_question);
        if (question.getTypeQuiz() == 1) {
            _radio_button_question.display(app, question);
        } else if (question.getTypeQuiz() == 2) {
            _dropdown_question.display(app, question);
        } else if (question.getTypeQuiz() == 3) {
            _checkbox_question.display(app, question);
        } else {
            _field_question.display(app,question);
        }
        _panel.revalidate();
        _panel.repaint();
    }

    /*
    description: display the result of the question
    return: nothing
    params: App - class App which communicate with the GUI
    params: answer - message to display
    */
    public void displayResultQuestion(final App app, String answer) {
        _panel.removeAll();
        JLabel text_ok = new JLabel(answer);
        JButton button_validate = new JButton("Continue");
        button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.play();
            }
        });
        _panel.add(text_ok);
        _panel.add(button_validate);
        _panel.revalidate();
        _panel.repaint();
    }

    /*
    description: display the score at the end of the game
    return: nothing
    params: App - class App which communicate with the GUI
    */
    public void displayEndGame(final App app) {
        JLabel score = new JLabel("Your score is " + Integer.toString(app.getUser().getScore()) + "/10");
        JButton button_validate = new JButton("Back to the menu");

        button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.updateScore();
                app.returnMenu();
            }
        });

        _panel.removeAll();
        _panel.add(score);
        _panel.add(button_validate);
        _panel.revalidate();
        _panel.repaint();
    }
}
