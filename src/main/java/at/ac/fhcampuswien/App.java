package at.ac.fhcampuswien;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import static javafx.application.Platform.exit;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // snake consists of pixel blocks
    static int blockSize = 20;

    // variables for Field in block sizes (1 block = 20 pixels)
    int width = 27;
    int height = 27;

    int initLength = 10;

    long later = System.nanoTime(); //to slow down loop

    boolean changed = false;
    int nextUpdate;
    boolean hasNextUpdate = false;

    Scene scene1, scene2;
    Field field;
    Image image = new Image("snake.png");

    @Override
    public void start(Stage window){
        Pane root = new Pane();

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(540);

        Button buttonStart = new Button("Start Game");
        buttonStart.setFont(new Font("Tahoma", 20));
        buttonStart.setOnAction(e->window.setScene(scene2));

        Button buttonExit = new Button("Exit");
        buttonExit.setFont(new Font("Tahoma", 20));
        buttonExit.setOnAction(e->exit());

        VBox layout1 = new VBox();
        layout1.getChildren().addAll(buttonStart, buttonExit);
        layout1.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, layout1);
        stackPane.setAlignment(layout1, Pos.CENTER);
        scene1 = new Scene(stackPane, 540,320);

        scene2 = new Scene(root, 540,600);

        window.setScene(scene1);
        window.setTitle("Sneaky Snake");
        window.setResizable(false);
        window.show();

        field = new Field(width, height);
        field.addSnake(new Snake(initLength, field));

        Label score = new Label("Score: 0");
        score.setFont(Font.font("tahoma", 32));
        score.relocate(10, 550);

        //An infinity loop that doesn't block the UI thread, will rerun its handle method every frame. (Game loop)
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //to update only one time every second (10^9ns is 1s)
                if (now - later > 1000000000 / 8) {  // divide by any value: the bigger the value, the faster the snake moves
                    field.update();
                    later = now;
                    score.setText("Score: " + field.score);
                    //score.setAlignment(Pos.BOTTOM_LEFT);
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
                        Image gameOver = new Image("game.png");
                        ImageView imageView1 = new ImageView(gameOver);
                        alert.setGraphic(imageView1);
                        imageView1.setPreserveRatio(true);
                        imageView1.setFitWidth(200);
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
        scene2.setOnKeyPressed(event -> {  // ->lambda expression
            if (event.getCode().equals(KeyCode.UP) && field.snake.getDirection() != Block.DOWN) {
                setDirection(field.snake, Block.UP);
            }
            else if (event.getCode().equals(KeyCode.DOWN) && field.snake.getDirection() != Block.UP) {
                setDirection(field.snake, Block.DOWN);
            }
            else if (event.getCode().equals(KeyCode.RIGHT) && field.snake.getDirection() != Block.LEFT) {
                setDirection(field.snake, Block.RIGHT);
            }
            else if (event.getCode().equals(KeyCode.LEFT) && field.snake.getDirection() != Block.RIGHT) {
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
}