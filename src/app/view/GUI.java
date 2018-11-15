package app.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/*
description: the class "GUI" initialises the objects related with the GUI, and the class about GUI
*/
public class GUI extends JFrame {

    private Menu _menu;
    private Game _game;
    private Border _border;
    private JPanel _panel;

    /*
    description: constructor of the class, initialises the objects related with the GUI
    return: nothing
    params: String - name of the GUI
    */
    public GUI(String name) {
        super(name);
        _panel = new JPanel();
        _panel.setLayout(new BoxLayout(_panel,BoxLayout.PAGE_AXIS));
        this.add(_panel);
        _border = BorderFactory.createLineBorder(Color.BLACK);
        _menu = new Menu(_panel,_border);

    }

    /*
    description: create a new object "Game" to restart the GUI elements about the game part
    return: nothing
    params: String - name of the GUI
    */
    public void newGame() {
        _game = new Game(_panel,_border);
    }

    /*
    description: get the object "Menu", used by the class "App"
    return: Menu - object related to the GUI of the menu
    params: nothing
    */
    public Menu getMenu() {
        return _menu;
    }

    /*
    description: get the object "Game", used by the class "App"
    return: Game - object related to the GUI of the game
    params: nothing
    */
    public Game getGame() {
        return _game;
    }
}
