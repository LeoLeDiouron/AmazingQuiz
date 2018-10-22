package app.view;

import javax.swing.*;

public class GUIQuestion {

    JButton _button_validate;
    JPanel _panel;

    GUIQuestion(JPanel panel) {
        _panel = panel;
        _button_validate = new JButton("Validate");
    }
}
