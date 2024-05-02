package view;

import controller.World;

import javax.swing.*;
import java.awt.*;

public class PanelPlayer extends JPanel {
    private GamePanel game;
    private World world;

    private ButtonPlayer[][] arrayButton;


    public PanelPlayer(GamePanel game) {
        this.game = game;

        setLayout(new GridLayout(game.getW(), game.getH()));

        arrayButton = game.getWorld().getArrayButton();

        setBorder(BorderFactory.createLoweredBevelBorder());

        ButtonPlayer[][] arrayButton = game.getWorld().getArrayButton();
        for(int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton.length; j++) {
                add(arrayButton[i][j] = new ButtonPlayer(this));
                arrayButton[i][j].addMouseListener(game);
            }
        }
    }

    public GamePanel getGame() {
        return game;
    }

    public void setGame(GamePanel game) {
        this.game = game;
    }

    public ButtonPlayer[][] getArrayButton() {
        return arrayButton;
    }

    public void setArrayButton(ButtonPlayer[][] arrayButton) {
        this.arrayButton = arrayButton;
    }
}
