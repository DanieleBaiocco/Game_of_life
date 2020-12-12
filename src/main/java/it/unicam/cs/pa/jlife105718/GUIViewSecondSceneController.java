package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.function.Function;

public class GUIViewSecondSceneController implements PropertyListener {
@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
private Function<List<Integer>, ? extends IPosizione> function;
private GameOfLifeController GRASPController;

    public void initializeGRASPController(Campo<? extends IPosizione> campo, Regole<Cellula> rule){
        GRASPController = GameOfLifeController.getInstance(campo,rule);
    }

     public GridPane initGrid(){
        GridPane grid = new GridPane();
        GRASPController.getCampo().addAEntry(0,0);
        switch (GRASPController.getCampo().getDim()){
            case 1:
                grid=initGrid1D();
                break;
            case 2:
                grid=initGrid2D();
                break;
            case 3:
                grid=initGrid3D();
                break;
            default:
                grid= new GridPane();
        }
       return grid;
    }

    private GridPane initGrid1D() {
        return null;
    }

    private GridPane initGrid2D() {

        return null;
    }

    private GridPane initGrid3D() {
        return null;
    }


/* GridPane griglia = new GridPane();
         griglia.setGridLinesVisible(true);*/

    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {

    }
}
