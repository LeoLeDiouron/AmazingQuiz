package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIFieldQuestion extends GUIQuestion{

    public GUIFieldQuestion(JPanel panel) {
        super(panel);
    }

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
