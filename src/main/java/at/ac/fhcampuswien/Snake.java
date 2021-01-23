package at.ac.fhcampuswien;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Snake {
    ArrayList<Block> blocks = new ArrayList<>();//this is the array where the blocks are going to be added to create the snake

    Block head;
    Block tail;

    public Snake(int initLength, Field field) {

        int initPosX = field.getW() / 2;
        int initPosY = field.getH() / 2;

        head = new Block(initPosX, initPosY, null, field);
        blocks.add(head);
        head.setFill(Color.CRIMSON);

        tail = head;

        for (int i = 1; i < initLength; i++) {
            Block b = new Block(initPosX + i, initPosY, tail, field);
            blocks.add(b);
            b.setFill(Color.LIGHTSEAGREEN);
            tail = b;
        }
    }

    //method to change direction of snake
    public void setDirection(int dir) {
        head.direction = dir;
    }

    //to solve problem: when we press right while going left it goes right and that's not allowed, etc..
    public int getDirection() {
        return head.direction;
    }
}