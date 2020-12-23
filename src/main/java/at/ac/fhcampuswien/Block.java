package at.ac.fhcampuswien;

import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    int posX, posY, oldPosX, oldPosY;

    Block previous;
    public Block (int x, int y, Block p) {
        super (App.blockSize, App.blockSize); // = blockSize saved in Main - but why twice?
        posX = x;
        posY = y;
    }

}
