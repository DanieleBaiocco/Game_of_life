package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class GUIViewSecondSceneController implements PropertyListener {
@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
private List<Integer> listaDiParametri;
private int dimensioneScelta;
private GameOfLifeController GRASPController;
private Regole<Cellula> regolaScelta;
private ICampo<?> campoGenerato;
    public void initializeGRASPController(String pos,  Integer dim, String rule){
  dimensioneScelta = dim;
   firstLabel.setText(Integer.toString(dim));
   secondLabel.setText(pos);
   thirdLabel.setText(rule);
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
     switch (rule) {
         case "BasicRule":
             regolaScelta = RulesFactory.getRulesFactory(campoGenerato).getBasicRules();
             break;
         case "AlternativeRule":
         case "AlternativeRule2":
             regolaScelta = RulesFactory.getRulesFactory(campoGenerato).getAlternativeRules();
             break;
     }
   //GRASPController = GameOfLifeController.getInstance(this.campoGenerato,this.regolaScelta);
   }

    private void createCampo3D(String pos) {
        switch (pos){
            case "PosizioneNumerica":
                this.campoGenerato = new Campo3D<PosizioneNumericaIntera>(TransitionFactory.getInstance().getTransitionToInteger());
                break;
            case "PosizioneAlfabetica":
                this.campoGenerato = new Campo3D<PosizioneAlfabetica>(TransitionFactory.getInstance().getTransitionToChar());
    }}

    private void createCampo1D(String pos) {
        switch (pos){
            case "PosizioneNumerica":
              this.campoGenerato = new Campo1D<PosizioneNumericaIntera>(TransitionFactory.getInstance().getTransitionToInteger());
              break;
            case "PosizioneAlfabetica":
                this.campoGenerato = new Campo1D<PosizioneAlfabetica>(TransitionFactory.getInstance().getTransitionToChar());
        }
    }

    private void createCampo2D(String pos) {
        switch (pos){
            case "PosizioneNumerica":
                this.campoGenerato = new Campo2D<PosizioneNumericaIntera>(TransitionFactory.getInstance().getTransitionToInteger());
                break;
            case "PosizioneAlfabetica":
                this.campoGenerato = new Campo2D<PosizioneAlfabetica>(TransitionFactory.getInstance().getTransitionToChar());
                break;
    }

    }

     public GridPane initGrid(){
         GridPane griglia = new GridPane();
         griglia.setGridLinesVisible(true);
         for (int i = 0; i < 100; i++) {
             Label label = new Label("sorry");
             griglia.addRow(i, label);

         }return griglia;
             }



    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {

    }
}
