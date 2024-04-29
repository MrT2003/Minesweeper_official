package controller;

import view.ButtonPlayer;
import view.ButtonSmile;
import view.Label;

import javax.swing.*;

public class World {
    private ButtonPlayer[][] arrayButton;
    private ButtonSmile btSmile;
    private Label lbBoom, lbTime;

    public World(int w, int h, int boom) {
        arrayButton = new ButtonPlayer[w][h];
    }

    public ButtonPlayer[][] getArrayButton() {
        return arrayButton;
    }

    public void setArrayButton(ButtonPlayer[][] arrayButton) {
        this.arrayButton = arrayButton;
    }

    public ButtonSmile getBtSmile() {
        return btSmile;
    }

    public void setBtSmile(ButtonSmile btSmile) {
        this.btSmile = btSmile;
    }

    public Label getLbTime() {
        return lbTime;
    }

    public void setLbTime(Label lbTime) {
        this.lbTime = lbTime;
    }

    public Label getLbBoom() {
        return lbBoom;
    }

    public void setLbBoom(Label lbBoom) {
        this.lbBoom = lbBoom;
    }
}
