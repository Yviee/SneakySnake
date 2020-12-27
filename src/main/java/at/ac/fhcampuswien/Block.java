package at.ac.fhcampuswien;

import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    int posX, posY, oldPosX, oldPosY;

    Block previous;
    public Block (int x, int y, Block p) {
        super (App.blockSize, App.blockSize); // = blockSize saved in Main - but why twice? Ich glaube einmal für x und einmal für y
        posX = x;
        posY = y;

        setTranslateX(posX * App.blockSize);
        setTranslateY(posY * App.blockSize);
    }

}
