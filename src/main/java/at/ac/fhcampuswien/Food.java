package at.ac.fhcampuswien;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.Random;

/**
 * !! BEFORE REVIEWING THE CODE, PLEASE READ OUR README FILE!!
 */

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
        setFill(randomColor());
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    public static Color randomColor() {
        Color[] arrayColors= {Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW,Color.ORANGE, Color.MEDIUMPURPLE, Color.DEEPPINK, Color.GREY};
        int colors = new Random().nextInt(arrayColors.length);
        return arrayColors[colors];

    }

}
