package Launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Create Gif");
//        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root, 600, 450));

        // Display the window
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
