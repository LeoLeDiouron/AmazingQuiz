package app.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI extends JFrame {

    private Menu _menu;
    private Game _game;
    private Border _border;
    private JPanel _panel;

    public GUI(String name) {
        super(name);
        _panel = new JPanel();
        _panel.setLayout(new BoxLayout(_panel,BoxLayout.PAGE_AXIS));
        this.add(_panel);
        _border = BorderFactory.createLineBorder(Color.BLACK);
        _menu = new Menu(_panel,_border);

    }

    public void newGame() {
        _game = new Game(_panel,_border);
    }

    public Menu getMenu() {
        return _menu;
    }

    public Game getGame() {
        return _game;
    }
}
