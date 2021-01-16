package it.unicam.cs.pa.jlife105718.View.GUIView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Ha la responsabilità di caricare e visualizzare lo stage della GUIView con la prima scena delle due.
 */
public class GUIViewStart extends Application{

    private static final Logger logger = Logger.getGlobal();

    /**
     * Crea una scena che ha come Parent l'FXML della prima scena. Lega la scena creata allo stage
     * passato come attributo e mostra lo stage.
     * @param primaryStage lo stage da mostrare
     * @throws Exception se c'è un errore durante il caricamento del Parent
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GameOfLifeFirstScene.fxml"));
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(new Scene(root, 1300, 700));
        primaryStage.show();
        logger.info("GuiView started.");
    }
}
