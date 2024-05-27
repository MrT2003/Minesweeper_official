package controller;

public class Move {
    private int x, y;
    private boolean wasFlag;
    private boolean wasOpened;
    private int previousNumber;

    public Move(int x, int y, boolean wasFlag, boolean wasOpened, int previousNumber) {
        this.x = x;
        this.y = y;
        this.wasFlag = wasFlag;
        this.wasOpened = wasOpened;
        this.previousNumber = previousNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean wasFlag() {
        return wasFlag;
    }

    public boolean wasOpened() {
        return wasOpened;
    }

    public int getPreviousNumber() {
        return previousNumber;
    }
}
