package app.view;

import app.controller.App;
import app.controller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUICheckboxQuestion extends GUIQuestion {

    public GUICheckboxQuestion(JPanel panel) {
        super(panel);
    }

    public void display(final App app, Question question) {
        final ArrayList<JCheckBox> checkboxes = new ArrayList<>();

        for (int i=0;i<question.getAnswers().size();i++) {
            checkboxes.add(new JCheckBox(question.getAnswers().get(i)));
            _panel.add(checkboxes.get(i));
        }
        _button_validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String final_answer = "";

                for (int i=0;i < checkboxes.size();i++) {
                    if (checkboxes.get(i).isSelected())
                        final_answer += checkboxes.get(i).getText() + ";";
                }
                app.validateAnswer(final_answer.substring(0, final_answer.length()-1));
            }
        });
        _panel.add(_button_validate);
    }
}
