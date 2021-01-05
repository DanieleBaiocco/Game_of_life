package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GUIViewSecondSceneController implements PropertyListener {

@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private AnchorPane containerOfGrid;
@FXML private  AnchorPane parent;
@FXML private Pane leftPane;
@FXML private Pane topPane;
@FXML private Button startButton;
@FXML private Button stopButton;

@FXML private Button finishButton;
private ScheduledExecutorService executor;
private boolean exit = false;
private GridPane gridPane ;
private Controller<?> GRASPController;


    public void initializeGRASPController(Controller<?> controller){
        //System.getproperty
        GRASPController = controller;
    }

    public void initGrid(){
         int[] values = GRASPController.getCampo().getValues();
        switch (values.length){
            case 1:
                initGrid1D(values[0]);
                break;
            case 2:
                initGrid2D(values[0],values[1]);
                break;
            case 3:
                initGrid3D(values[0],values[1], values[2]);
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
        }
       else {
           exit = false;
           stopButton.setText("STOP");
        }
    }

    @FXML
    public void startSimulation(MouseEvent mouseEvent){
        Runnable startGen = new Runnable() {
            public void run() {
                if (!exit) {
                        GRASPController.nextGen();
                }else
                System.out.println("Sto aspettando");
            }
        };
        this.executor.scheduleAtFixedRate(startGen, 0, 1000, TimeUnit.MILLISECONDS);

    }

    private void initGrid1D(int x1) {
    }


    private void initGrid2D(int x1, int x2) {
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
        firstLabel.setText(Integer.toString(GRASPController.getCampo().getValues().length).concat("D"));
        secondLabel.setText(GRASPController.getRule().toString());
        double altezzaScacco = containerOfGrid.getPrefHeight()/x1;
        for(int i=0; i<x1; i++){
            for(int j=0; j<x2; j++){
                Pane pane = new Pane();
                pane.setPrefSize(700,700);
               // pane.setStyle("-fx-background-color: white");
                this.gridPane.add(pane,j,i);
                pane.setOnMouseClicked(event -> {
                    GRASPController.colorateDecolorateACellula(GridPane.getColumnIndex(pane),GridPane.getRowIndex(pane));
                });

                GRASPController.addAEntry(j,i);
                if(j==0){
                    double altezzaLabel = altezzaScacco* i + altezzaScacco/2;
                    Label label = new Label();
                    leftPane.getChildren().add(label);
                    label.setLayoutY(altezzaLabel);
                    label.setText(GRASPController.getRepresentation(1,j,i));
                }
                if(i==0){
                    double altezzaLabel = altezzaScacco* j + altezzaScacco/2;
                    Label label = new Label();
                    topPane.getChildren().add(label);
                    label.setLayoutX(altezzaLabel);
                    label.setText(GRASPController.getRepresentation(0,j,i));
                }
                GRASPController.getCampo().getCellulaFromInteger(j,i).addPropertyListener(this);
                //int leng = GRASPController.getCampo().getValues().length;
                if(GRASPController.getCellsToSetAlive()!=null){
                    for(int k=0; k<GRASPController.getCellsToSetAlive().length; k=k+2){
                        int firstCoo = GRASPController.getCellsToSetAlive()[k];
                        int secondCoo = GRASPController.getCellsToSetAlive()[k+1];
                        if(j==firstCoo&&i==secondCoo)
                            GRASPController.colorateDecolorateACellula(j,i);
                    }
                }
            }
        }
    }


    private void initGrid3D(int x1, int x2, int x3) {
    }


    //da refactorare per renderla generica a tutti i casi (anche per 1d e 3d)
    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {
        int[] result = GRASPController.getCampo().getIntegerFromCellula(source);
       int x=  result[0];
       int y = result[1];
        Pane pane = getPaneFromIntegers(x,y);

      //metodo che fa la ricerca del gridpane corrispondente alla cellula
       if (state == Stato.VIVO){
            pane.setStyle("-fx-background-color: black");
        } else {
           pane.setStyle("-fx-background-color: #f4f4f4");
           pane.setStyle("-fx-border-color: #838383");
       }
    }

    @FXML public void zoom(ScrollEvent event){
        zoom(Math.pow(1.01, event.getDeltaY()), event.getSceneX(), event.getSceneY());
    }

    private void zoom(double factor, double x, double y){
        double oldScale = parent.getScaleX();
        double scale = oldScale * factor;
        if (scale < 1) scale = 1;
        if (scale > 50)  scale =50;
        parent.setScaleX(scale);
        parent.setScaleY(scale);
    }

    private Pane getPaneFromIntegers(int x, int y){
        Pane paneToReturn = null;
         for(int i=1; i<gridPane.getChildren().size();i++){
             Pane pane = (Pane) gridPane.getChildren().get(i);
             if(GridPane.getColumnIndex(pane)==x && GridPane.getRowIndex(pane)==y){
                 paneToReturn= pane;
                 break;
             }
         }
         return paneToReturn;
        //System.out.println(GridPane.getColumnIndex(pane1)+ " "+ GridPane.getRowIndex(pane1));
    }

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
    }
}
