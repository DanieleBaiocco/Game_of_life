package it.unicam.cs.pa.jlife105718;

import it.unicam.cs.pa.jlife105718.View.GUIView.GUIViewStart;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

/**
 * Ha la responsabilità di lanciare l'applicazione.
 */
public class GameOfLifeApp {

    /**
     * Variabile di logging.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Personalizza il logger per permettere di esser scritto su file, inizializzare la directory dove i file di
     * logging vengono salvati e non essere stampato a console.
     * @throws IOException
     */
    private static void setLogger() {
        String dirPath = "fileDiLog";
        new File((dirPath)).mkdir();
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        Handler handler = null;
        try {
            handler = new FileHandler(dirPath+"/log_" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))+".txt");
            handler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(handler);
    }

    /**
     * Lancia la GUI View.
     * @param args Stringhe passate in input al metodo main.
     */
    public static void main(String[] args) {
        setLogger();
        logger.info("App started.");
        Application.launch(GUIViewStart.class);
        logger.info("App closed.");
    }
}
