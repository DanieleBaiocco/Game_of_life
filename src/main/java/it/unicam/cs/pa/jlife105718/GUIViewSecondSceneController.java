package it.unicam.cs.pa.jlife105718;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class GUIViewSecondSceneController implements PropertyListener, Initializable {

@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
@FXML private AnchorPane containerOfGrid;
private GridPane gridPane ;
private Function<List<Integer>, ? extends IPosizione> function;
private GameOfLifeController GRASPController;

    public void initializeGRASPController(Campo<? extends IPosizione> campo, Regole<Cellula> rule){
        GRASPController = GameOfLifeController.getInstance(campo,rule);
    }

     public void initGrid(List<Integer> list){
        switch (list.size()){
            case 1:
                initGrid1D(list.get(0));
                break;
            case 2:
                initGrid2D(list.get(0),list.get(1));
                break;
            case 3:
                initGrid3D(list.get(0),list.get(1),list.get(2));
                break;
        }
    }

    private void initGrid1D(int x1) {
    }

    private void initGrid2D(int x1, int x2) {
        firstLabel.setText("SESSO");
        for(int i=0; i<x1; i++){
            for(int j=0; j<x2; j++){
                Pane pane = new Pane();
                pane.setPrefSize(600,600);
                this.gridPane.add(pane,j,i);
                pane.setOnMouseClicked(event -> {
                    GRASPController.colorateDecolorateACellula(GridPane.getColumnIndex(pane),GridPane.getRowIndex(pane));
                });
                GRASPController.addAEntry(j,i);
                GRASPController.getCellulaFromInteger(j,i).addPropertyListener(this);
            }
        }
    }

    private void initGrid3D(int x1, int x2, int x3) {
    }

    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {
        int x = GRASPController.getCampo().getIntegerFromCellula(source,0);
        int y = GRASPController.getCampo().getIntegerFromCellula(source,1);
        System.out.println("Posizione colonna :"+x);
        System.out.println("Posizione riga :"+y);
        getPaneFromIntegers(x,y);

     /*  //metodo che fa la ricerca del gridpane corrispondente alla cellula
       if (state == Stato.MORTO){
            pane.setStyle("-fx-background-color: black");
        } else pane.setStyle("-fx-background-color: white");*/
    }

    private Node getPaneFromIntegers(int x, int y){
        Node pane=  this.gridPane.getChildren().get(0);
        Pane pane1 = (Pane) pane;
      //  System.out.println(GridPane.getColumnIndex(pane1)+ " "+ GridPane.getRowIndex(pane1));
        return pane;
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
