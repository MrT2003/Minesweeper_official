package view;

import javax.swing.*;
import java.awt.*;

public class ButtonPlayer extends JButton {
    private PanelPlayer p;

    public ButtonPlayer(PanelPlayer p) {
        this.p = p;
        setPreferredSize(new Dimension(30, 30));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("noUse"), 0, 0,getPreferredSize().width, getPreferredSize().height, null);
    }
}
