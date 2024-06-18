/*
Name: Bùi Văn Minh Triều
Purpose: Taking
*/

package view;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    private PanelNotification p;
    private String number;
    public Label(PanelNotification p, String number) {
        this.p = p;
        this.number = number;
        setPreferredSize(new Dimension(78,46));
    }

    public void paint(Graphics g) {
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(0))), 0, 0,26, 46, null);
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(1))), 26, 0, 26, 46,null);
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(2))), 52, 0, 26, 46, null);
    }
}
