package view;

import controller.World;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
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

        setLayout(new BorderLayout());

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
}
