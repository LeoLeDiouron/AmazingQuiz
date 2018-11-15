package app.view;

import javax.swing.*;

/*
description: the class "GUIQuestion" initialise some elements about the GUI of the questions
*/
public class GUIQuestion {

    JButton _button_validate;
    JPanel _panel;

    /*
    description: constructor of the class, initialises the objects related with the GUI of the questions
    return: nothing
    params: JPanel - panel created by the class "GUI"
    */
    GUIQuestion(JPanel panel) {
        _panel = panel;
        _button_validate = new JButton("Validate");
    }
}
