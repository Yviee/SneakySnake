package at.ac.fhcampuswien;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake {
    ArrayList<Block> blocks = new ArrayList<Block>();//this is the array where the blocks are going to be added to create the snake

    Block head;

    public Snake(int initLength, Field field) {

        int initPosX = field.getW() / 2; //why the (int) before field.get? why not in the video?
        int initPosY = field.getH() / 2;
        //used the getters that were defined in class Field but not yet used,
        // probably solved the upper problem? #nur

        head = new Block(initPosX, initPosY, null, field);//if you do not need something you write null
        blocks.add(head);
        //to make the snake's head green (or any color)
        head.setFill(Color.GREEN.desaturate());
        //(desaturate) creates a new color that is a less saturated version of the green color

        Block previous = head;


        for (int i = 1; i < initLength; i++) {
            Block b = new Block(initPosX + i, initPosY, previous, field);
            blocks.add(b);
            previous = b;
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

