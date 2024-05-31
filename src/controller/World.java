package controller;

import view.ButtonPlayer;
import view.ButtonSmile;
import view.Label;

import javax.swing.*;
import java.util.Random;
import java.util.Stack;

public class World {
    private Stack<Move> moveStack;
    private ButtonPlayer[][] arrayButton;
    private int[][] arrayBoom;      /*create array boom*/
    private boolean[][] arrayBoolean;

    public boolean[][] getArrayPutFlag() {
        return arrayPutFlag;
    }

    public void setArrayPutFlag(boolean[][] arrayPutFlag) {
        this.arrayPutFlag = arrayPutFlag;
    }

    private boolean[][] arrayPutFlag;
    private boolean isComplete;
    private boolean isEnd;
    private ButtonSmile btSmile;
    private Label lbBoom, lbTime;
    private Random random;
    private int boom;
    private Stack<boolean[][]> undoStack;

    public World(int w, int h, int boom) {
        this.boom = boom;
        arrayButton = new ButtonPlayer[w][h];
        arrayBoom = new int[w][h];
        arrayBoolean = new boolean[w][h];
        arrayPutFlag = new boolean[w][h];
        moveStack = new Stack<>();
        undoStack = new Stack<>();

        random = new Random();

        //Start here
        createArrayBoom(boom, w, h);  /*Choose location that have -1*/
        showNumber();   /*Hiện 0 và -1 */
        System.out.println(boom);
    }
    public void putFlag(int i, int j) {
        if(!arrayBoolean[i][j]) {
            if(arrayPutFlag[i][j]) {
                moveStack.push(new Move(i, j, true, false, -1));
                arrayPutFlag[j][j] = false;
                arrayButton[i][j].setNumber(-1);
                arrayButton[i][j].repaint();
            } else {
                moveStack.push(new Move(i, j, false, false, 9));
                arrayPutFlag[i][j] = true;
                arrayButton[i][j].setNumber(9);
                arrayButton[i][j].repaint();
            }
        }
    }
    public boolean open(int i, int j) {
        if (!isComplete && !isEnd) {
            if (!arrayBoolean[i][j]) {
                int previousNumber = arrayBoom[i][j];
                moveStack.push(new Move(i, j, false, true, previousNumber));
                // Lưu trạng thái của toàn bộ bảng trước khi dính boom
                boolean[][] currentState = new boolean[arrayBoolean.length][arrayBoolean[0].length];
                for (int a = 0; a < arrayBoolean.length; a++) {
                    System.arraycopy(arrayBoolean[a], 0, currentState[a], 0, arrayBoolean[a].length);
                }
                undoStack.push(currentState);

                // Open adjacent cells if the current cell is 0
                if (arrayBoom[i][j] == 0) {
                    arrayBoolean[i][j] = true;
                    arrayButton[i][j].setNumber(0);
                    arrayButton[i][j].repaint();

                    if (checkWin()) {
                        isEnd = true;
                        fullTrue();
                        return false;
                    }

                    for (int l = i - 1; l <= i + 1; l++) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (l >= 0 && l < arrayBoom.length && k >= 0 && k < arrayBoom[0].length) {
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
                        if (checkWin()) {
                            isEnd = true;
                            fullTrue();
                            return false;
                        }
                        return true;
                    }
                }
            }
            if (arrayBoom[i][j] == -1) {
                arrayButton[i][j].setNumber(11);
                arrayButton[i][j].repaint();
                isComplete = true;

                for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
                    for (int k = 0; k < arrayBoolean[0].length; k++) {
                        if (arrayBoom[j2][k] == -1 && !arrayBoolean[j2][k]) {
                            arrayButton[j2][k].setNumber(10);
                            arrayButton[j2][k].repaint();
                        }
                    }
                }
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public void undo() {
        if (!moveStack.isEmpty()) {
            Move lastMove = moveStack.pop();
            int x = lastMove.getX();
            int y = lastMove.getY();
            boolean wasFlag = lastMove.wasFlag();
            boolean wasOpened = lastMove.wasOpened();
            int previousNumber = lastMove.getPreviousNumber();

            if (wasFlag) {
                arrayPutFlag[x][y] = !arrayPutFlag[x][y];
                arrayButton[x][y].setNumber(arrayPutFlag[x][y] ? 9 : -1);
            } else if (wasOpened) {
                arrayBoolean[x][y] = false;
                arrayButton[x][y].setNumber(previousNumber);
            }

            // Reset game state flags if they were set due to the last move
            setComplete(false);
            setEnd(false);

            arrayButton[x][y].repaint();

            // Kiểm tra xem có trạng thái nào cần khôi phục không
            if (!undoStack.isEmpty()) {
                boolean[][] previousState = undoStack.pop();
                for (int i = 0; i < arrayBoolean.length; i++) {
                    System.arraycopy(previousState[i], 0, arrayBoolean[i], 0, previousState[i].length);
                }

                // Cập nhật lại giao diện người dùng
                for (int i = 0; i < arrayButton.length; i++) {
                    for (int j = 0; j < arrayButton[i].length; j++) {
                        if (arrayBoolean[i][j]) {
                            arrayButton[i][j].setNumber(arrayBoom[i][j]);
                        } else {
                            arrayButton[i][j].setNumber(-1);
                        }
                        arrayButton[i][j].repaint();
                    }
                }
            }
        }
    }


    public void showNumber() {
        for(int i = 0; i < arrayBoom.length; i++) {
            for(int j = 0; j < arrayBoom.length; j++) {
                //Check if location not have boom == 0
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
            //check whether location have boom or not
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
                //Count box not open
                if(!arrayBoolean[i][j]) {
                    count++;
                }
            }
        }
        //if enough => win
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

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

}
