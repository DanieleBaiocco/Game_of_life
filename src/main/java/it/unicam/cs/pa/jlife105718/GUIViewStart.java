package it.unicam.cs.pa.jlife105718;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIViewStart extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GameOfLifeFirstScene.fxml"));
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
