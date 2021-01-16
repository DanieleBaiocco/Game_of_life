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
private IController<?> GRASPController;

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

    public void initializeGRASPController(IController<?> controller){
        GRASPController = controller;
    }

    public void initGrid(){
         int[] values = GRASPController.getCampo().getValues();
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

    @FXML
    public void finishSimulation(MouseEvent mouseEvent){
        this.executor.shutdown();
    }

    @FXML
    public void stopSimulation(MouseEvent mouseEvent){
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

    @FXML
    public void startSimulation(MouseEvent mouseEvent){
        Runnable startGen = new Runnable() {
            public void run() {
                if (!exit) {
                        GRASPController.nextGen();
                        logger.info("Next Generation calculated and shown");
                }
            }
        };
        this.executor.scheduleAtFixedRate(startGen, 0, 1000, TimeUnit.MILLISECONDS);
    }

    private void initGrid1D(int x1) {
    //implementazione omessa
    }

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

    private void layoutDimAndRule(Label dimLabel, Label ruleLabel){
        firstLabel.setText(Integer.toString(GRASPController.getCampo().getValues().length).concat("D"));
        secondLabel.setText(GRASPController.getRule().toString());
    }

    private void paneCreation(int j, int i){
        Pane pane = new Pane();
        pane.setPrefSize(700,700);
        this.gridPane.add(pane,j,i);
        pane.setOnMouseClicked(event -> {
            GRASPController.colorateDecolorateACellula(GridPane.getColumnIndex(pane),GridPane.getRowIndex(pane));
        });
    }

    private void createAndSetPositionLabel(double altezzaScacco, int moltiplicatore, Pane pane, boolean isXequals0,int j, int i){
        double altezzaLabel = altezzaScacco* moltiplicatore + altezzaScacco/2;
        Label label = new Label();
        pane.getChildren().add(label);
        if(isXequals0){
            label.setLayoutY(altezzaLabel);
            label.setText(GRASPController.getRepresentation(1,j,i));
        }else {
            label.setLayoutX(altezzaLabel);
            label.setText(GRASPController.getRepresentation(0, j, i));
        }
    }

    private void initGridFromCells(int j, int i){
        for(int k=0; k<GRASPController.getCellsToSetAlive().length; k=k+2){
            int firstCoo = GRASPController.getCellsToSetAlive()[k];
            int secondCoo = GRASPController.getCellsToSetAlive()[k+1];
            if(j==firstCoo&&i==secondCoo){
                GRASPController.colorateDecolorateACellula(j,i);
                logger.info("Pane at position {"+j+","+i+"} turned black.");
            }
        }
    }

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
                GRASPController.addAEntry(j,i);
                logger.info("Pane at position {"+j+","+i+"} is initialized as white.");
                paneCreation(j,i);
                GRASPController.getCellulaFromInteger(j,i).addPropertyListener(this);
                if(GRASPController.getCellsToSetAlive()!=null){
                   initGridFromCells(j,i);
                }
            }
        }
    }

    private void initGrid3D(int x1, int x2, int x3) {
    //implementazione omessa
    }

    @Override
    public void onPropertyEvent(ICell source, String name, Stato state) {
        int[] result = GRASPController.getCampo().getIntegerFromCellula(source);
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
