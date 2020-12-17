package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.function.Function;

public class GUIViewSecondSceneController implements PropertyListener {

@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
@FXML private AnchorPane containerOfGrid;
private Function<List<Integer>, ? extends IPosizione> function;
private GameOfLifeController GRASPController;

    public void initializeGRASPController(Campo<? extends IPosizione> campo, Regole<Cellula> rule){
        GRASPController = GameOfLifeController.getInstance(campo,rule);
    }

     public void initGrid(List<Integer> list){
        GridPane gridPane=  new GridPane();
        gridPane.setGridLinesVisible(true);

        switch (list.size()){
            case 1:
                initGrid1D(list.get(0), gridPane);
                break;
            case 2:
                initGrid2D(list.get(0),list.get(1),gridPane);
                break;
            case 3:
                initGrid3D(list.get(0),list.get(1),list.get(2),gridPane);
                break;
        }
        this.containerOfGrid.getChildren().add(gridPane);
        AnchorPane.setRightAnchor(gridPane, .0);
        AnchorPane.setTopAnchor(gridPane, .0);
        AnchorPane.setLeftAnchor(gridPane, .0);
        AnchorPane.setBottomAnchor(gridPane, .0);
    }

    private void initGrid1D(int x1, GridPane gridPane) {
    }

    private void initGrid2D(int x1, int x2, GridPane gridPane) {
        firstLabel.setText("SESSO");
        for(int i=0; i<x1; i++){
            for(int j=0; j<x2; j++){
                Pane pane = new Pane();
                pane.setPrefSize(600,600);

                gridPane.add(pane,j,i);
                pane.setOnMouseClicked(event -> {
                    System.out.println(GridPane.getColumnIndex(pane));
                    System.out.println(GridPane.getRowIndex(pane));
                });
                GRASPController.addAEntry(j,i);
                System.out.println("COlonna secondo GRASPController "+j +" Riga secondo GRASP "+i);
              //  gridPane.getChildren().forEach(x->System.out.println(x.getScaleX()+" "+ x.getScaleY()));


            }
        }
    }

    private void initGrid3D(int x1, int x2, int x3, GridPane gridPane) {
    }

    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {

    }
}
