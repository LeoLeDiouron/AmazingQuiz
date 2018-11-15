package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
description: the class "GUIDropdownQuestion" manages the GUI about the question of type "Dropdown"
*/
public class GUIDropdownQuestion extends GUIQuestion {

    /*
    description:  constructor of the class, call the constructor of the parent class "GUIQuestion"
    return: nothing
    params: JPanel - panel created by the class "GUI"
    */
    public GUIDropdownQuestion(JPanel panel) {
        super(panel);
    }

    /*
    description: display the GUI of a question of type "Dropdown"
    return: nothing
    params: App - class App which communicate with the GUI
    params: Question - question to display
    */
    public void display(final App app, Question question) {
        String[] answers = new String[4];
        for (int i=0;i<question.getAnswers().size();i++) {
            System.out.print(i);
            System.out.print(question.getAnswers().get(i));
            answers[i] = question.getAnswers().get(i);
        }
        final JComboBox dropdown = new JComboBox(answers);
        _button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.validateAnswer((String)dropdown.getSelectedItem());
            }
        });
        _panel.add(dropdown);
        _panel.add(_button_validate);
    }
}
