package it.unicam.cs.pa.jlife105718;

import javafx.css.Rule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GUIViewSecondSceneController implements PropertyListener{

@FXML GridPane gridPane;
@FXML Pane zerozero;
@FXML Pane zerouno;
@FXML Pane unozero;
@FXML Pane unouno;
@FXML Pane duezero;
@FXML Pane dueuno;
public GameOfLifeController initializeGRASPController(){
    Campo2D<PosizioneNumerica> campo = new Campo2D<PosizioneNumerica>(2,3, TransitionFactory.getInstance().getTransitionToInteger());
    Regole<Cellula> basicrule = RulesFactory.getRulesFactory(campo).getBasicRules();
    return GameOfLifeController.getInstance(campo, basicrule);
}
GameOfLifeController GRASPController = initializeGRASPController();

@FXML public void changeCellula(){
gridPane.setStyle("-fx-background-color: red");
}
private ArrayList<Integer> getArr (int x, int y){
    ArrayList<Integer> arr= new ArrayList<>();
    arr.add(x);
    arr.add(y);
    return arr;
}
   @FXML public void changeCellulaofzerozero() {
     GRASPController.colorateDecolorateACellula(getArr(0,0));
     zerozero.setStyle("-fx-background-color: black");
    }

    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {

    }
}
