package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
description: the class "GUIDropdownQuestion" manages the GUI about the question of type "Dropdown"
*/
public class GUIFieldQuestion extends GUIQuestion{

    /*
    description:  constructor of the class, call the constructor of the parent class "GUIQuestion"
    return: nothing
    params: JPanel - panel created by the class "GUI"
    */
    public GUIFieldQuestion(JPanel panel) {
        super(panel);
    }

    /*
    description: display the GUI of a question of type "Dropdown"
    return: nothing
    params: App - class App which communicate with the GUI
    params: Question - question to display
    */
    public void display(final App app, Question question) {
        final JTextArea user_answer = new JTextArea();

        user_answer.append("Your answer here.");
        _button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.validateAnswer(user_answer.getText().replace(",","").replace("'", "").replace("\"", ""));
            }
        });
        _panel.add(user_answer);
        _panel.add(_button_validate);
    }

}
