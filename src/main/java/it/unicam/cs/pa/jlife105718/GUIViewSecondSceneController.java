package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GUIViewSecondSceneController implements PropertyListener, Initializable {

@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
@FXML private AnchorPane containerOfGrid;
@FXML private  AnchorPane parent;
@FXML private Button startButton;
@FXML private Button stopButton;
@FXML private Button finishButton;
private ScheduledExecutorService executor;
private boolean exit = false;
private GridPane gridPane ;
private Controller GRASPController;
private boolean flag;
private int[] cellsToColorate;
    public void initializeGRASPController(ICampo<? extends IPosizione> campo,CurrentRulesEnum rule){
        GRASPController = GameOfLifeController.getInstance(campo,rule);
        this.flag = true;
    }

    public int[] getCellsToColorate() {
        return cellsToColorate;
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

    public void setCellsToColorate(int[] cells){
        for(int i =0; i<cells.length; i++)
            System.out.println(cells[i]);
        this.cellsToColorate = cells;
    }

    public boolean getFlag(){
        return flag;
    }

    public void setFlag(boolean bool){
        flag= bool;
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
        this.executor = Executors.newScheduledThreadPool(1);
        this.executor.scheduleAtFixedRate(startGen, 0, 1, TimeUnit.SECONDS);
    }

    private void initGrid1D(int x1) {
    }


    private void initGrid2D(int x1, int x2) {
        firstLabel.setText("");
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
                GRASPController.getCampo().getCellulaFromInteger(j,i).addPropertyListener(this);
                //int leng = GRASPController.getCampo().getValues().length;
                if(!this.getFlag()){
                    for(int k=0; k<cellsToColorate.length; k=k+2){
                        int firstCoo = cellsToColorate[k];
                        int secondCoo = cellsToColorate[k+1];
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
        int x = GRASPController.getCampo().getIntegerFromCellula(source,0);
        int y = GRASPController.getCampo().getIntegerFromCellula(source,1);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridPane= new GridPane();
        gridPane.setGridLinesVisible(true);
        this.containerOfGrid.getChildren().add(gridPane);
        AnchorPane.setRightAnchor(gridPane, .0);
        AnchorPane.setTopAnchor(gridPane, .0);
        AnchorPane.setLeftAnchor(gridPane, .0);
        AnchorPane.setBottomAnchor(gridPane, .0);

    }
}
