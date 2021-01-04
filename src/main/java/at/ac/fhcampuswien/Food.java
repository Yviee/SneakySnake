package at.ac.fhcampuswien;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.Random;

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
        //setStroke(Color.RED);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    public static Color randomColor() {
        Color[] arrayColors= {Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW,Color.ORANGE};
        int colors = new Random().nextInt(arrayColors.length);
        int nextColor = new Random().nextInt(arrayColors.length);

        while (colors == nextColor) {
            nextColor = new Random().nextInt(arrayColors.length);
        }
        return arrayColors[nextColor];
        /**if (colors == colors) {
            colors = new Random().nextInt(arrayColors.length);
        } else {
            return arrayColors[colors];
        }
        return null;

        for (int i = arrayColors.length; i >= 0; i--) {
            int j = new Random().nextInt(i);
            if (j != colors) {
                return arrayColors[colors];
            }
            else {
            j = new Random().nextInt(i);
            Color k = arrayColors[j];
            arrayColors[j] = arrayColors[i - 1];
            arrayColors[i - 1]  = k;

        } */

    }

}
