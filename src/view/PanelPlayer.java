package view;

import controller.World;

import javax.swing.*;
import java.awt.*;

public class PanelPlayer extends JPanel {
    private GamePanel game;
    private World world;


    public PanelPlayer(GamePanel game) {
        this.game = game;

        setLayout(new GridLayout(game.getW(), game.getH()));

        setBorder(BorderFactory.createLoweredBevelBorder());

        ButtonPlayer[][] arrayButton = game.getWorld().getArrayButton();
        for(int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton.length; j++) {
                add(arrayButton[i][j] = new ButtonPlayer(this));
            }
        }
    }

    public GamePanel getGame() {
        return game;
    }

    public void setGame(GamePanel game) {
        this.game = game;
    }
}
