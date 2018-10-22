package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDropdownQuestion extends GUIQuestion {

    public GUIDropdownQuestion(JPanel panel) {
        super(panel);
    }

    public void display(final App app, Question question) {
        String[] answers = new String[4];
        for (int i=0;i<question.getAnswers().size();i++) {
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
