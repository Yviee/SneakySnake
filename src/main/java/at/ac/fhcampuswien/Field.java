package at.ac.fhcampuswien;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Field extends Pane {
    private int w, h;

    ArrayList<Block> blocks = new ArrayList<>();//snake consists of blocks in array
    int score = 0;
    Food f;
    Snake snake;

    public Field(int width, int height) {// game area
        w = width;
        h = height;
        setMinSize(w * App.blockSize, h * App.blockSize);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        addFood();
    }

    public void addSnake(Snake s) {
        this.snake = s;
        for (Block b : s.blocks) {
            addBlock(b);
        }
    }

    private void addBlock(Block b) {
        getChildren().add(b);//Child is in block
        blocks.add(b);
        b.setFill(Color.LIGHTSEAGREEN);
        snake.head.setFill(Color.CRIMSON);
    }

    public void update() {
        for (Block b : blocks) { //loop will go through all blocks and update every block in the field
            b.update();
        }
        // call method for food
        if (isEaten(f)) {
            score += 20;
            addFood();
            addNewBlock(); // adds new block to tail when food is eaten
        }
    }

    public boolean isDead(){
        for (Block b: blocks) {
            if(b != snake.head) {
                if(b.posX == snake.head.posX && b.posY == snake.head.posY) {
                    return true;
                }
            }
        }
        return false;
    }

    // adds new block to end of tail
    public void addNewBlock() {
        Block b = new Block(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
        snake.tail = b;
        addBlock(b);
    }

    // add food block to game field
    public void addFood() {
        // add food block every run in a random position

        int randomXPos = (int) (Math.random() * w);
        int randomYPos = (int) (Math.random() * h);

        // check if food appears in snake's body
        for (Block block : blocks) {
            while (randomXPos == block.posX && randomYPos == block.posY) {
                randomXPos = (int) (Math.random() * w);
                randomYPos = (int) (Math.random() * h);
            }
        }
        // create food object
        Food food = new Food(randomXPos, randomYPos);
        getChildren().add(food); //add object into  pane
        getChildren().remove(f); //removes food after being eaten; only new food will be shown
        f = food;
    }

    // if no food added, it will return false; otherwise it will check for position
    public boolean isEaten(Food f) {
        if (f == null) {
            return false;
        }
        return f.getPosX() == snake.head.posX && f.getPosY() == snake.head.posY;
    }

    public int getW() {
        return w;
    }//getter for width for snake class for initial position

    public int getH() {
        return h;
    }//getter for height for snake class for initial position
}