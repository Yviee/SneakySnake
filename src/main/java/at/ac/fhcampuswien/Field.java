package at.ac.fhcampuswien;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

public class Field extends Pane {
    private int w, h;

    ArrayList<Block> blocks = new ArrayList<Block>();//die Schlange besteht aus blocks und die sind in einem Array und das ist dann die Schlange
    Snake snake;

    public Field(int width, int height) {//die Fläche wo das Spiel ist
        w = width;
        h = height;
        setMinSize(w * App.blockSize, h * App.blockSize);
        setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        addFood();  //call method in field
    }

    public void addSnake(Snake s) {//
        this.snake = s;
        for (Block b : s.blocks) {
            addBlock(b);
        }
    }

    private void addBlock(Block b) {
        getChildren().add(b);//Child ist im Block
        blocks.add(b);
    }

    public void update() {
        for (Block b : blocks) { //loop will go through all blocks and update every block in the field
            b.update();
        }

    }

    //to add the food block to the game field
    public void addFood() {
        //method() will add food block every run in a random position
        int randomXPos = (int) (Math.random() * w);
        int randomYPos = (int) (Math.random() * h);

        //create food object
        Food food = new Food(randomXPos, randomYPos);
        getChildren().add(food); //add object into the pane


    }

    public int getW() {
        return w;
    }//getter für width for snake class for initial position

    public int getH() {
        return h;
    }//getter für height for snake class for initial position
}
