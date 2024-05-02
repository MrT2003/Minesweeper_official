package controller;

import view.ButtonPlayer;
import view.ButtonSmile;
import view.Label;

import javax.swing.*;
import java.util.Random;

public class World {
    private ButtonPlayer[][] arrayButton;
    private int[][] arrayBoom;      /*create array boom*/
    private boolean[][] arrayBoolean;

    private boolean isComplete;
    private boolean isEnd;
    private ButtonSmile btSmile;
    private Label lbBoom, lbTime;
    private Random random;
    private int boom;

    public World(int w, int h, int boom) {
        this.boom = boom;
        arrayButton = new ButtonPlayer[w][h];
        arrayBoom = new int[w][h];
        arrayBoolean = new boolean[w][h];

        random = new Random();

        createArrayBoom(boom, w, h);
        showNumber();
        System.out.println(boom);
        for(int i = 0; i < arrayBoom.length; i++) {
            for(int j = 0; j < arrayBoom.length; j++)  {
                System.out.print(arrayBoom[i][j] + " ");
            }
            System.out.println();
        }

    }
    public boolean open(int i, int j) {
        if(checkWin()) {
            isEnd = true;
            for(int j2 = 0; j2 < arrayBoolean.length; j2++) {
                for(int k = 0; k < arrayBoolean.length; k++) {
                    if(!arrayBoolean[j2][k]) {
                        arrayBoolean[j2][k] = true;
                    }
                }
            }
            return false;
        }
        if (!isComplete && !isEnd) {
            if (!arrayBoolean[i][j]) {
                if (arrayBoom[i][j] == 0) {
                    arrayBoolean[i][j] = true;
                    arrayButton[i][j].setNumber(0);
                    arrayButton[i][j].repaint();
                    for (int l = i - 1; l <= i + 1; l++) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (l >= 0 && l <= arrayBoom.length - 1 && k >= 0 && k <= arrayBoom.length - 1) {
                                if (!arrayBoolean[l][k]) {
                                    open(l, k);
                                }
                            }
                        }
                    }
                } else {
                    arrayBoolean[i][j] = true;
                    int number = arrayBoom[i][j];
                    if (number != -1) {
                        arrayButton[i][j].setNumber(number);
            arrayButton[i][j].repaint();
                        return true;
                    }
                }
            }
            if (arrayBoom[i][j] == -1) {
                isComplete = true;
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public void showNumber() {
        for(int i = 0; i < arrayBoom.length; i++) {
            for(int j = 0; j < arrayBoom.length; j++) {
                if(arrayBoom[i][j] == 0) {
                    int count = 0;
                    for(int l = i - 1; l <= i + 1; l++) {
                        for(int k = j - 1; k <= j + 1; k++) {
                            if(l >= 0 && l <= arrayBoom.length - 1 && k >= 0 && k <= arrayBoom.length - 1) {
                                if(arrayBoom[l][k] == -1) {
                                    count++;
                                }
                            }
                        }
                    }
                    arrayBoom[i][j] = count;
                }
            }
        }
    }


    public void createArrayBoom(int boom, int w, int h) {
        int location_x = random.nextInt(w);                /*Random vị trí x, y*/
        int location_y = random.nextInt(h);

        arrayBoom[location_x][location_y] = -1;             /*Giá trị tại vị trí arrayBoom = -1  */
        int count = 0;
        while(count != boom) {                              /*boom = 10*/
            location_x = random.nextInt(w);                 /*Random vị trí x, y*/
            location_y = random.nextInt(h);
            if(arrayBoom[location_x][location_y] != -1) {   /*Điều chỉnh cho = -1*/
                arrayBoom[location_x][location_y] = -1;
                count = 0;
                //Đếm số -1
                for(int i = 0; i < arrayBoom.length; i++) {
                    for(int j = 0; j < arrayBoom.length; j++) {
                        if(arrayBoom[i][j] == -1) {
                            count++;
                        }
                    }
                }
            }
        }
    }

    public void fullTrue() {
        for(int i = 0; i < arrayButton.length; i++) {
            for(int j = 0; j < arrayButton.length; j++) {
                if(!arrayBoolean[i][j]) {
                    arrayBoolean[i][j] = true;
                }
            }
        }
        isComplete = true;
    }

    public boolean checkWin() {
        int count = 0;
        for(int i = 0; i < arrayBoolean.length; i++) {
            for(int j = 0; j < arrayBoolean.length; j++) {
                if(!arrayBoolean[i][j]) {
                    count++;
                }
            }
        }
        if(count == boom) {
            return true;
        } else {
            return false;
        }
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

    public boolean[][] getArrayBoolean() {
        return arrayBoolean;
    }

    public void setArrayBoolean(boolean[][] arrayBoolean) {
        this.arrayBoolean = arrayBoolean;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public boolean getIsEnd() {
        return isEnd;
    }


}
