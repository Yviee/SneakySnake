package at.ac.fhcampuswien;

import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    public int posX;
    public int posY;
    public int oldPosX;
    public int oldPosY;

    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

    int direction = LEFT;

    Block previous;

    int maximumX;
    int maximumY;

    public Block(int x, int y, Block p, Field field) {
        super(App.blockSize, App.blockSize);
        this.posX = x;
        this.posY = y;
        setTranslateX(posX * App.blockSize);
        setTranslateY(posY * App.blockSize);
        previous = p;  //to be able to update the blocks..
        maximumX = field.getW();
        maximumY = field.getH();
    }

    public void update() {//movement method to update() logically
        oldPosX = posX;    //set old positions to new positions before moving
        oldPosY = posY;

        if (previous == null) {  //we're in the head block of the snake and we'll have choices in which direction to move next
            switch (direction) {
                case UP:
                    moveUp();
                    break;
                case RIGHT:
                    moveRight();
                    break;
                case DOWN:
                    moveDown();
                    break;
                case LEFT:
                    moveLeft();
                    break;
            }
        } else {    //if we're not in head of snake (block)
            posX = previous.oldPosX;
            posY = previous.oldPosY;
        }
        updatePosition(); // visual position update after logical update
    }

    //Methods for moving: the if conditions in each enables the snake when reaching top to come out of bottom etc.
    public void moveUp() {
        posY--; //decrement position Y
        if (posY < 0) {
            posY = maximumY - 1;
        }
    }

    public void moveDown() {
        posY++;      //increment position Y
        if (posY >= maximumY) {
            posY = 0;
        }
    }

    public void moveRight() {
        posX++;      //increment position X
        if (posX >= maximumX) {
            posX = 0;
        }
    }

    public void moveLeft() {
        posX--;      //decrement position X
        if (posX < 0) {
            posX = maximumX - 1;
        }
    }

    public void updatePosition() {  //method to update() visually
        //System.out.println(posX + "  /  " + posY);
        setTranslateX(posX * App.blockSize);
        setTranslateY(posY * App.blockSize);
    }
}