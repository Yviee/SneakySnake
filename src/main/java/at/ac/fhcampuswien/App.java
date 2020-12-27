package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.awt.*;

import static javax.swing.text.StyleConstants.setBackground;

public class App extends Application {
    public static void main(String[] args){
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

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        //root.setPadding(new Insets(10));



        //Button button = new Button();
        //button.setText("Start Game!");
        //button.setOnAction( (event) -> Platform.exit() );
        //root.getChildren().add(button);
        Scene scene = new Scene(root/**, 600, 550*/);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sneaky Snake");


        Field field = new Field(width,height);
        field.addSnake(new Snake(initLength,field));
        root.getChildren().add(field);

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
