package it.unicam.cs.pa.jlife105718.View.GUIView;

import it.unicam.cs.pa.jlife105718.Controller.IController;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.PropertyListener;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GUIViewSecondSceneController implements PropertyListener {
    private static final Logger logger = Logger.getGlobal();
@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private AnchorPane containerOfGrid;
@FXML private Pane leftPane;
@FXML private Pane topPane;
@FXML private Button stopButton;
private ScheduledExecutorService executor;
private boolean exit = false;
private GridPane gridPane ;
private IController<?> currentController;

    /**
     * Inizializza la seconda scena creando un GridPane, rendendo le linee visibili, aggiungendo il GridPane all'AnchorPane e
     * creando un Executor della  Thread Pool con un solo thread all'interno
     */
    @FXML
    public void initialize() {
        gridPane= new GridPane();
        gridPane.setGridLinesVisible(true);
        this.containerOfGrid.getChildren().add(gridPane);
        AnchorPane.setRightAnchor(gridPane, .0);
        AnchorPane.setTopAnchor(gridPane, .0);
        AnchorPane.setLeftAnchor(gridPane, .0);
        AnchorPane.setBottomAnchor(gridPane, .0);
        executor = Executors.newScheduledThreadPool(1);
        logger.info("New scene correctly initialized.");
    }

    /**
     * metodo che serve per inizializzare il currentController che poi verrà usato per calcolare le configurazioni per ogni successiva
     * configurazione
     */
    public void initializeGRASPController(IController<?> controller){
        currentController = controller;
    }

    /**
     *  metodo chiamato dal GUIViewFirstSceneController per creare e visualizzare la griglia con le informazioni contenute nel currentController
     */
    public void initGrid(){
         int[] values = currentController.getCampo().getValues();
        switch (values.length){
            case 1:
                initGrid1D(values[0]);
                logger.info("Grid in 1D choosed");
                break;
            case 2:
                initGrid2D(values[0],values[1]);
                logger.info("Grid in 2D choosed");
                break;
            case 3:
                initGrid3D(values[0],values[1], values[2]);
                logger.info("Grid in 3D choosed");
                break;
        }
    }

    /**
     * Sul click del bottone Finish l'Executor della Thread Pool viene spento
     */
    @FXML
    public void finishSimulation(){
        this.executor.shutdown();
    }

    /**
     *Sul click del bottone Stop, il flag che non permette l'esecuzione del metodo nextGen() viene messo a true. Se viene fatto un altro
     * click il flag viene messo a false e l'esecuzione del nextGen() riprende
     */
    @FXML
    public void stopSimulation(){
        if(stopButton.getText().equals("STOP")){
            exit =true;
            stopButton.setText("RESUME");
            logger.info("Execution stopped.");
        }
       else {
           exit = false;
           stopButton.setText("STOP");
           logger.info("Execution resumed.");
        }
    }

    /**
     * Viene eseguito quando viene cliccato il bottone Start. Questo metodo manda in esecuzione a intervalli di 1 secondo
     * il metodo NextGen del currentController
     */
    @FXML
    public void startSimulation(){
        Runnable startGen = new Runnable() {
            public void run() {
                if (!exit) {
                        currentController.nextGen();
                        logger.info("Next Generation calculated and shown");
                }
            }
        };
        this.executor.scheduleAtFixedRate(startGen, 0, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * crea una griglia 1D
     */
    private void initGrid1D(int x1) {
    //implementazione omessa
    }

    /**
     * Ridimensiona il contenitore della griglia 2D in base ai valori di massima coordinata per l'asse x e per l'asse y
     */
    private void resizeContainerOfGrid(int x1, int x2){
        if(x1!=x2){
            if(x1<x2){
                double result = containerOfGrid.getPrefWidth()/x2;
                containerOfGrid.setPrefHeight(result*x1);
                leftPane.setPrefHeight(result*x1);
            } else{
                double result= containerOfGrid.getPrefHeight()/x1;
                containerOfGrid.setPrefWidth(result*x2);
                topPane.setPrefWidth(result*x2);
            }
        }
    }

    /**
     * Visualizza la dimensione e la regola scelte
     */
    private void layoutDimAndRule(Label dimLabel, Label ruleLabel){
        dimLabel.setText(Integer.toString(currentController.getCampo().getValues().length).concat("D"));
        ruleLabel.setText(currentController.getRule().toString());
    }

    /**
     * Crea un pane con le coordinate (j,i), lo inserisce tra i figli della griglia e gli aggiunge un eventHandler sull'evento
     * "sul click del mouse" che come risposta cambia lo stato della cellula (j,i) nel dominio
     */
    private void paneCreation(int j, int i){
        Pane pane = new Pane();
        pane.setPrefSize(700,700);
        this.gridPane.add(pane,j,i);
        pane.setOnMouseClicked(event -> {
            currentController.colorateDecolorateACellula(GridPane.getColumnIndex(pane),GridPane.getRowIndex(pane));
        });
    }

    /**
     * Ridimensiona la griglia 2D in modo da avere una griglia con tutti gli scacchi quadrati e ridimensionata
     */
    private void createAndSetPositionLabel(double altezzaScacco, int moltiplicatore, Pane pane, boolean isXequals0,int j, int i){
        double altezzaLabel = altezzaScacco* moltiplicatore + altezzaScacco/2;
        Label label = new Label();
        pane.getChildren().add(label);
        if(isXequals0){
            label.setLayoutY(altezzaLabel);
            label.setText(currentController.getRepresentation(1,j,i));
        }else {
            label.setLayoutX(altezzaLabel);
            label.setText(currentController.getRepresentation(0, j, i));
        }
    }

    /**
     * Cambia lo stato delle celle salvate all'interno del campo cellsToSetAlive nel controller passato dal GUIViewFirstSceneController.
     * Questo cambiamento interno si ripercuoterà anche a livello di view in quanto in ognuna di queste celle questa classe è in ascolto
     * in modo tale che quando qualcosa cambierà al loro interno questa classe visualizzerà il cambiamento di conseguenza
     */
    private void initGridFromCells(int j, int i){
        for(int k = 0; k< currentController.getCellsToSetAlive().length; k=k+2){
            int firstCoo = currentController.getCellsToSetAlive()[k];
            int secondCoo = currentController.getCellsToSetAlive()[k+1];
            if(j==firstCoo&&i==secondCoo){
                currentController.colorateDecolorateACellula(j,i);
                logger.info("Pane at position {"+j+","+i+"} turned black.");
            }
        }
    }

    /**
     * Per ogni possibile Cella all'interno del campo contenuto nel controller, aggiungo questa cella alla mappa <posizione, cella>
     * del campo, creo il pane che rappresenterà visualmente quella cella, aggiungo a quella cella questa classe in ascolto di qualche
     * cambiamento e, nel caso in cui il controller preveda delle celle da considerare VIVE, controllo se la cella che sto scorrendo
     * è tra queste e in caso le cambio lo stato in VIVA
     */
    private void initGrid2D(int x1, int x2) {
        resizeContainerOfGrid(x1,x2);
        layoutDimAndRule(firstLabel, secondLabel);
        double altezzaScacco = containerOfGrid.getPrefHeight()/x1;
        for(int i=0; i<x1; i++){
            for(int j=0; j<x2; j++){
                if(j==0)
                    createAndSetPositionLabel(altezzaScacco,i,leftPane,true,j,i);
                if(i==0)
                    createAndSetPositionLabel(altezzaScacco,j,topPane,false,j,i);
                currentController.addAEntry(j,i);
                logger.info("Pane at position {"+j+","+i+"} is initialized as white.");
                paneCreation(j,i);
                currentController.getCellulaFromInteger(j,i).addPropertyListener(this);
                if(currentController.getCellsToSetAlive()!=null){
                   initGridFromCells(j,i);
                }
            }
        }
    }

    /**
     * crea una griglia 3D
     */
    private void initGrid3D(int x1, int x2, int x3) {
    //implementazione omessa
    }

    /**
     * Il metodo dell'interfaccia viene sovrascritto in modo tale che quando una cellula sulla quale questa classe è iscritta
     * cambia stato, quella cellula notifica il cambiamento a questa classe che manderà in esecuzione il seguente metodo. Questo
     * metodo cambia il colore della cellula che è cambiata di stato: se è cambiata da morta a viva verrà visualizzata con un colore
     * scuro all'utente, viceversa con un colore chiaro
     */
    @Override
    public void onPropertyEvent(ICell source, String name, Stato state) {
        int[] result = currentController.getCampo().getIntegerFromCellula(source);
        Pane pane = getPaneFromIntegers(result);
       if (state == Stato.VIVO){
            pane.setStyle("-fx-background-color: black");
            logger.info("Cell {"+result[0]+","+result[1]+"} lives");
       } else {
           pane.setStyle("-fx-background-color: #f4f4f4");
           pane.setStyle("-fx-border-color: #838383");
           logger.info("Cell {"+result[0]+","+result[1]+"} dies");
       }
    }

    /**
     * Serve per ricavare il pane corrispondente alle coordinate passate
     */
    private Pane getPaneFromIntegers(int ... values){
        Pane paneToReturn = null;
         for(int i=1; i<gridPane.getChildren().size();i++){
             Pane pane = (Pane) gridPane.getChildren().get(i);
             if(GridPane.getColumnIndex(pane)==values[0] && GridPane.getRowIndex(pane)==values[1]){
                 paneToReturn= pane;
                 break;
             }
         }
         return paneToReturn;
    }

}
