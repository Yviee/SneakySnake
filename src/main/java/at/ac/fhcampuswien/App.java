package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class App extends Application {
    public static void main(String[] args){
        launch(args);
    }

    // We probably need VBox, because the old version code below uses an additional fxml file that is not included in here.
    // Our App class = video's Main class. Code will probably look the same in terms of VBox. Button has to be erased etc.

    // snake consists of pixel blocks
    static int blockSize = 10;

    // variables for Field in block sizes (1 block = 10 pixels)
    int width = 30;
    int height = 15;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sneaky Snake");
        Button btn = new Button();
        btn.setText("Hello JavaFX!");
        btn.setOnAction( (event) -> Platform.exit() );
        Pane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 150));
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

     */

}
