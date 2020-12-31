package at.ac.fhcampuswien;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
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
    static int blockSize = 20;

    // variables for Field in block sizes (1 block = 20 pixels)
    int width = 30;
    int height = 25;

    int initLength = 10;

    long later = System.nanoTime(); //to slow down loop:

    boolean changed = false;
    int nextUpdate;
    boolean hasNextUpdate = false;

    Field field;

    @Override
    public void start(Stage primaryStage) {
        //VBox root = new VBox(10);
        Pane root = new Pane();
        Scene scene = new Scene(root /**500, 550*/);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sneaky Snake");
        primaryStage.show();

        field = new Field(width, height);
        field.addSnake(new Snake(initLength, field));

        Label score = new Label("Score: 0");
        score.setFont(Font.font("tahoma", 32));

        //An infinity loop that doesn't block the UI thread, will rerun its handle method every frame. (Game loop)
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //to update only one time every second (10^9ns is 1s)
                if (now - later > 1000000000 / 8) {  //we can divide by any value(the bigger) the faster the snake moves
                    field.update();
                    later = now;
                    score.setText("Score: " + field.score);
                    changed = false;
                    if (hasNextUpdate) { //smoother control
                        setDirection(field.snake, nextUpdate);
                        hasNextUpdate = false;
                    }

                    if (field.isDead()) { //lets snake die and start from beginning
                        stop();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION); // shows message when snake dies
                        alert.setHeaderText("Better luck next time!");
                        alert.setContentText("Your score is " + field.score);
                        Platform.runLater((alert::showAndWait));

                        alert.setOnHidden(event -> { // resets game after message
                            root.getChildren().clear();
                            field = new Field(width, height);
                            field.addSnake(new Snake(initLength, field));
                            score.setText("Score: 0");
                            root.getChildren().addAll(field, score);
                            start();
                        });
                    }
                }
            }
        };
        timer.start();
        root.getChildren().addAll(field, score);

        //add a listener on the scene
        //call of methods getDirection() and setDirection()
        scene.setOnKeyPressed(event -> {  // ->lambda expression
            if (event.getCode().equals(KeyCode.UP) && field.snake.getDirection() != Block.DOWN) {
                setDirection(field.snake, Block.UP);
            }
            if (event.getCode().equals(KeyCode.DOWN) && field.snake.getDirection() != Block.UP) {
                setDirection(field.snake, Block.DOWN);
            }
            if (event.getCode().equals(KeyCode.RIGHT) && field.snake.getDirection() != Block.LEFT) {
                setDirection(field.snake, Block.RIGHT);
            }
            if (event.getCode().equals(KeyCode.LEFT) && field.snake.getDirection() != Block.RIGHT) {
                setDirection(field.snake, Block.LEFT);
            }
        });
    }

    public void setDirection(Snake snake, int direction) {
        if (!changed) {
            snake.setDirection(direction); // recursion
            changed = true;
        } else {
            hasNextUpdate = true;
            nextUpdate = direction;
        }
    }



        //primaryStage.setResizable(false);
    //root.setPadding(new Insets(10));
    //Button button = new Button();
    //button.setText("Start Game!");
    //button.setOnAction( (event) -> Platform.exit() );
    //root.getChildren().add(button);

    /*
    // First version Code (without gradle) - perhaps we might need some of it later?
     @Override
    public void start(Stage primaryStage) {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sneaky Snake");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    HBox, VBox and Stage are different ways of how to display the "window"

     */

}
