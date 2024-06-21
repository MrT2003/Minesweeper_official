/*
Name: Bùi Văn Minh Triều
Purpose: Arrange location for each widget
*/

package view;

import javax.swing.*;
import java.awt.*;

public class PanelNotification extends JPanel {
    private JPanel p11, p12, p13;
    private Label lbBoom, lbTime;
    private GamePanel game;
    private ButtonSmile bt;
    public PanelNotification(GamePanel game) {
        this.game = game;

        bt = game.getWorld().getBtSmile();

        setLayout(new BorderLayout());

        setBorder(BorderFactory.createLoweredBevelBorder());

        add(p11 = new JPanel(), BorderLayout.WEST);
        add(p12 = new JPanel(), BorderLayout.EAST);
        add(p13 = new JPanel(), BorderLayout.CENTER);
        p11.add(lbBoom = new Label(this, "000"));
        p12.add(lbTime = new Label(this,"000"));
        p13.add(bt = new ButtonSmile(this));

    }

    public GamePanel getGame() {
        return game;
    }

    public void setGame(GamePanel game) {
        this.game = game;
    }
}
