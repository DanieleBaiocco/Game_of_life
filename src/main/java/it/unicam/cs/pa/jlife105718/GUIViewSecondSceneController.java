package it.unicam.cs.pa.jlife105718;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.util.List;
import java.util.function.Function;

public class GUIViewSecondSceneController implements PropertyListener {

@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
@FXML private GridPane griglia;
private Posizione posizioneScelta;
private List<Integer> listaDiParametri;
private int dimensioneScelta;
private Function<List<Integer>,? extends Posizione> transizioneScelta;
private Regole<Cellula> regolaScelta;
private GameOfLifeController GRASPController;
private Campo<?> campoGenerato;

    public void initializeGRASPController(Posizione pos, List<Integer> list, int dim, Regole<Cellula> rule){
   posizioneScelta=pos;
   listaDiParametri=list;
   dimensioneScelta=dim;
   regolaScelta=rule;
   switch (dimensioneScelta) {
       case 1:
            createCampo1D(pos);
           break;
       case 2:
           createCampo2D(pos);
           break;
       case 3 :
           createCampo3D(pos);
           break;
       default:
           campoGenerato = null;
     }
   GRASPController = GameOfLifeController.getInstance(this.campoGenerato,regolaScelta);
   }

    private void createCampo3D(Posizione pos) {
        switch (pos.getClass().toString()){
            case "PosizioneNumerica":
                campoGenerato = new Campo3D<PosizioneNumerica>(listaDiParametri.get(0),listaDiParametri.get(1),listaDiParametri.get(2),TransitionFactory.getInstance().getTransitionToInteger());
                break;
            case "PosizioneAlfabetica":
                campoGenerato = new Campo3D<PosizioneAlfabetica>(listaDiParametri.get(0),listaDiParametri.get(1),listaDiParametri.get(2),TransitionFactory.getInstance().getTransitionToChar());
    }}

    private void createCampo1D(Posizione pos) {
        switch (pos.getClass().toString()){
            case "PosizioneNumerica":
              campoGenerato = new Campo1D<PosizioneNumerica>(listaDiParametri.get(0),TransitionFactory.getInstance().getTransitionToInteger());
              break;
            case "PosizioneAlfabetica":
                campoGenerato = new Campo1D<PosizioneAlfabetica>(listaDiParametri.get(0),TransitionFactory.getInstance().getTransitionToChar());
        }
    }

    private void createCampo2D(Posizione pos) {
        switch (pos.getClass().toString()){
            case "PosizioneNumerica":
                campoGenerato = new Campo2D<PosizioneNumerica>(listaDiParametri.get(0),listaDiParametri.get(1),TransitionFactory.getInstance().getTransitionToInteger());

                break;
            case "PosizioneAlfabetica":
                campoGenerato = new Campo2D<PosizioneAlfabetica>(listaDiParametri.get(0),listaDiParametri.get(1),TransitionFactory.getInstance().getTransitionToChar());
                break;
    }

    }

     public void initGrid(){

     }

    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {

    }
}
