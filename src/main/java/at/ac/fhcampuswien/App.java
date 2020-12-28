package at.ac.fhcampuswien;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.awt.*;

import static javax.swing.text.StyleConstants.setBackground;

public class App extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    // We probably need VBox, because the old version code below uses an additional fxml file that is not included in here.
    // Our App class = video's Main class. Code will probably look the same in terms of VBox. Button has to be erased etc.

    // snake consists of pixel blocks
    static int blockSize = 10;

    // variables for Field in block sizes (1 block = 10 pixels)
    int width = 60;
    int height = 55;

    int initLength = 8;

    long later = System.nanoTime(); //to slow down loop:

    @Override
    public void start(Stage primaryStage) {
        //VBox root = new VBox(10);
        Pane root = new Pane();

        //primaryStage.setResizable(false);


        //root.setPadding(new Insets(10));


        //Button button = new Button();
        //button.setText("Start Game!");
        //button.setOnAction( (event) -> Platform.exit() );
        //root.getChildren().add(button);


        Field field = new Field(width, height);
        field.addSnake(new Snake(initLength, field));
        root.getChildren().add(field);


        //An infinity loop that doesn't block the UI thread, will rerun its handle method every frame. (Game loop)
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                //to update only one time every second (10^9ns is 1s)
                if (now - later > 1000000000 / 8) {  //we can divide by any value(the bigger) the faster the snake moves
                    field.update();
                    later = now;
                }

            }
        };
        timer.start();
        Scene scene = new Scene(root/**, 600, 550*/);

        //add a listener on the scene
        //call of methods getDirection() and setDirection()
        scene.setOnKeyPressed(event -> {  // ->lambda expression
            if (event.getCode().equals(KeyCode.UP) && field.snake.getDirection() != Block.DOWN) {
                field.snake.setDirection(Block.UP);
            }
            if (event.getCode().equals(KeyCode.DOWN) && field.snake.getDirection() != Block.UP) {
                field.snake.setDirection(Block.DOWN);
            }
            if (event.getCode().equals(KeyCode.RIGHT) && field.snake.getDirection() != Block.LEFT) {
                field.snake.setDirection(Block.RIGHT);
            }
            if (event.getCode().equals(KeyCode.LEFT) && field.snake.getDirection() != Block.RIGHT) {
                field.snake.setDirection(Block.LEFT);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sneaky Snake");
        primaryStage.show();

    }


    /*
    // First version Code (without gradle) - perhaps we might need some of it later?
     @Override
    public void start(Stage primaryStage) {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sneaky Snake");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    HBox, VBox und Stage are different ways of how to display the "window"

     */

}
