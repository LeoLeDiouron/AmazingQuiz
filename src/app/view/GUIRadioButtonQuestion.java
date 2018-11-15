package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
description: the class "GUIRadioButtonQuestion" manages the GUI about the question of type "Radio Button"
*/
public class GUIRadioButtonQuestion extends GUIQuestion {

    /*
    description:  constructor of the class, call the constructor of the parent class "GUIQuestion"
    return: nothing
    params: JPanel - panel created by the class "GUI"
    */
    public GUIRadioButtonQuestion(JPanel panel) {
        super(panel);
    }

    /*
    description: display the GUI of a question of type "Radio Button"
    return: nothing
    params: App - class App which communicate with the GUI
    params: Question - question to display
    */
    public void display(final App app, Question question) {
        final ArrayList<JRadioButton> answers = new ArrayList<>();

        for (int i=0;i<question.getAnswers().size();i++) {
            answers.add(new JRadioButton(question.getAnswers().get(i)));
            _panel.add(answers.get(i));
        }
        _button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i < answers.size();i++) {
                    if (answers.get(i).isSelected()) {
                        app.validateAnswer(answers.get(i).getText());
                    }
                }
            }
        });
        _panel.add(_button_validate);
    }
}
