package controller;

import view.ButtonPlayer;
import view.ButtonSmile;
import view.Label;

import javax.swing.*;
import java.util.Random;

public class World {
    private ButtonPlayer[][] arrayButton;
    private int[][] arrayBoom;      /*create array boom*/
    private ButtonSmile btSmile;
    private Label lbBoom, lbTime;
    private Random random;

    public World(int w, int h, int boom) {
        arrayButton = new ButtonPlayer[w][h];
        arrayBoom = new int[w][h];
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
        int location_x = random.nextInt(w);
        int location_y = random.nextInt(h);

        arrayBoom[location_x][location_y] = -1;             /*Size arrayBoom = 1  */
        int count = 1;
        while(count != boom) {
            location_x = random.nextInt(w);
            location_y = random.nextInt(h);
            if(arrayBoom[location_x][location_y] != -1) {
                arrayBoom[location_x][location_y] = -1;
                count = 0;
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
