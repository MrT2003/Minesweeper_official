package view;

import controller.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {
    private World world;
    private GameFrame gameFrame;
    private PanelNotification p1;
    private PanelPlayer p2;

    private int w;
    private int h;
    public GamePanel(int w, int h, int boom, GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.w = w;
        this.h = h;
        this. world = new World(w, h, boom);

        setLayout(new BorderLayout(20, 20));

        add(p1 = new PanelNotification(this), BorderLayout.NORTH);

        add(p2 = new PanelPlayer(this), BorderLayout.CENTER);

    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ButtonPlayer[][] arrayButton = p2.getArrayButton();
        for(int i = 0; i < arrayButton.length; i++) {
            for(int j = 0; j < arrayButton.length; j++) {
                if(e.getButton() == 1 && e.getSource() == arrayButton[i][j]) {
//                    System.out.println(i +" , " +j);
                    if(!world.open(i ,j)) {
                        int option = JOptionPane.showConfirmDialog(this, "Do You Want To Play Again?", "Notification", JOptionPane.YES_NO_OPTION);
                        if(option == JOptionPane.YES_NO_OPTION) {
                            gameFrame.setVisible(false);
                            new GameFrame();
                        } else {

                            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                    };
                }
            }
        }
    }

//    public boolean open(int i, int j) {
//        int number = arrayBoom[i][j];
//        if(number != -1) {
//            arrayButton[i][j].setNumber(number);
////            arrayButton[i][j].repaint();
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
