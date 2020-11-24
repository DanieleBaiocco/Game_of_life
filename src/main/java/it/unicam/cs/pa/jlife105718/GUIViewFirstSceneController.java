package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIViewFirstSceneController implements Initializable {
    @FXML private AnchorPane mainAnchorPane;
@FXML private RadioButton oneDRadioButton;
@FXML private RadioButton twoDRadioButton;
@FXML private RadioButton threeDRadioButton;
@FXML private RadioButton integerNumbersRadioButton;
@FXML private RadioButton doubleNumbersRadioButton;
@FXML private RadioButton alphabetRadioButton;
@FXML private RadioButton standardRuleRadioButton;
@FXML private RadioButton alternativeRuleRadioButton;
@FXML private RadioButton alternativeRule2RadioButton;
@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
@FXML private TextField firstTextField;
@FXML private TextField secondTextField;
@FXML private TextField thirdTextField;
@FXML private Button button1;
@FXML private Pane pane2;
private ToggleGroup dimensionChoosedToggleGroup;
private ToggleGroup ruleChoosedToggleGroup;
private ToggleGroup positionChoosedToggleGroup;
private ToggleGroup labelsToggleGroup;
private int dimension;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //init the dimension radiobutton
        dimensionChoosedToggleGroup= new ToggleGroup();
        this.oneDRadioButton.setToggleGroup(dimensionChoosedToggleGroup);
        this.twoDRadioButton.setToggleGroup(dimensionChoosedToggleGroup);
        this.threeDRadioButton.setToggleGroup(dimensionChoosedToggleGroup);
        //init the position radiobutton
        ruleChoosedToggleGroup = new ToggleGroup();
        this.standardRuleRadioButton.setToggleGroup(ruleChoosedToggleGroup);
        this.alternativeRuleRadioButton.setToggleGroup(ruleChoosedToggleGroup);
        this.alternativeRule2RadioButton.setToggleGroup(ruleChoosedToggleGroup);
        //init the rule radiobutton
        positionChoosedToggleGroup= new ToggleGroup();
        this.integerNumbersRadioButton.setToggleGroup(positionChoosedToggleGroup);
        this.doubleNumbersRadioButton.setToggleGroup(positionChoosedToggleGroup);
        this.alphabetRadioButton.setToggleGroup(positionChoosedToggleGroup);
        //init labels and textfields
        this.pane2 = new Pane();
        this.firstLabel.setText("");
        this.secondLabel.setText("");
        this.thirdLabel.setText("");
        this.firstTextField.setVisible(false);
        this.secondTextField.setVisible(false);
        this.thirdTextField.setVisible(false);
        this.button1.setDisable(true);
        this.pane2.getChildren().add(firstTextField);
        this.pane2.getChildren().add(secondTextField);
        this.pane2.getChildren().add(thirdTextField);
        this.dimension=0;

    }


   @FXML public void loadLabelsAndTexts(MouseEvent mouseEvent) {
        if(oneDRadioButton.isSelected()) {
            firstLabel.setText("Inserisci la prima dimensione");
            firstTextField.setVisible(true);
            this.dimension=1;
        }
       if(twoDRadioButton.isSelected()) {
           firstLabel.setText("Inserisci la prima dimensione");
           firstTextField.setVisible(true);
           secondLabel.setText("Inserisci la seconda dimensione");
           secondTextField.setVisible(true);
           this.dimension=2;
       }
       if(threeDRadioButton.isSelected()){
           firstLabel.setText("Inserisci la prima dimensione");
           firstTextField.setVisible(true);
           secondLabel.setText("Inserisci la seconda dimensione");
           secondTextField.setVisible(true);
           thirdLabel.setText("Inserisci la terza dimensione");
           thirdTextField.setVisible(true);
           this.dimension=3;
       }
       oneDRadioButton.setDisable(true);
       twoDRadioButton.setDisable(true);
       threeDRadioButton.setDisable(true);
}

   @FXML
   public void loadGrid(MouseEvent mouseEvent) {

    }
    //controllo da fare ogni qual volta si seleziona un qualcosa per vedere se si può effettivamente continuare
    //oppure no perchè mancano ancora delle info
    private boolean nextIsPossible(){
        return positionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && ruleChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && dimensionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && pane2.getChildren().stream().filter(x->!(x.isDisable()))
                .filter(x-> {
                    TextField text = (TextField) x;
                    return text.getText().isEmpty() ;
                }).count()==this.dimension;
    }



    @FXML public void checkOnNextButton(MouseEvent mouseEvent) {
        if(nextIsPossible())
            this.button1.setDisable(false);
    }
}
