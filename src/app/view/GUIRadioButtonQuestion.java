package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIRadioButtonQuestion extends GUIQuestion {

    public GUIRadioButtonQuestion(JPanel panel) {
        super(panel);
    }

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
