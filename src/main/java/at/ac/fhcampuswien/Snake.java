package at.ac.fhcampuswien;

import java.util.ArrayList;

public class Snake {
    ArrayList<Block> blocks = new ArrayList<Block>();//this is the array where the blocks are going to be added to create the snake

    Block head;

    public Snake (int initLength, Field field) {

        int initPosX = (int) (field.getWidth() / 2);//why the (int) before field.get? why not in the video?
        int initPosY = (int) (field.getHeight() / 2);

        head = new Block(initPosX, initPosY, null);//if you do not need something you write null
        Block previous = head;

        for(int i = 1; i < initLength; i++){
            Block b = new Block(initPosX+i, initPosY, previous);
            blocks.add(b);
            previous = b;
        }
    }
}
