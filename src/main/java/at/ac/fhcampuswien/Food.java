package at.ac.fhcampuswien;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.awt.*;

public class Food extends Rectangle {
    int posX, posY;

    public Food(int x, int y) {
        super(App.blockSize, App.blockSize);
        posX = x;
        posY = y;
        setTranslateX(posX * App.blockSize);
        setTranslateY(posY * App.blockSize);
        //posX and posY aren't in pixels but blocks, that is why we have to multiply by blockSize to get actual position


        //set food color
        setFill(Color.ORANGE);
        setStroke(Color.RED);
    }


}
