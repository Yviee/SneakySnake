package at.ac.fhcampuswien;

import java.util.ArrayList;

public class Snake {
    ArrayList<Block> blocks = new ArrayList<Block>();

    Block head;

    public Snake (int initLength, Field field) {

        int initPosX = (int) (field.getWidth() / 2);
        int initPosY = (int) (field.getHeight() / 2);
    }
}
