package app.view;

import app.controller.App;
import app.controller.Question;
import app.controller.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {

    private Menu _menu;
    private Game _game;

    public GUI(String name) {
        super(name);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        this.add(panel);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        _menu = new Menu(panel,border);
        _game = new Game(panel,border);
    }

    public Menu getMenu() {
        return _menu;
    }

    public Game getGame() {
        return _game;
    }
}
