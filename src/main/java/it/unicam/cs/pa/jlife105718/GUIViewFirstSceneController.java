package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIViewFirstSceneController implements Initializable {
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
private ToggleGroup dimensionChoosedToggleGroup;
private ToggleGroup ruleChoosedToggleGroup;
private ToggleGroup positionChoosedToggleGroup;

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
        this.firstLabel.setText("");
        this.secondLabel.setText("");
        this.thirdLabel.setText("");
        this.firstTextField.setVisible(false);
        this.secondTextField.setVisible(false);
        this.thirdTextField.setVisible(false);
    }


   @FXML public void loadLabelsAndTexts(MouseEvent mouseEvent) {
        if(oneDRadioButton.isSelected()) {
            firstLabel.setText("Inserisci la prima dimensione");
            firstTextField.setVisible(true);
        }
       if(twoDRadioButton.isSelected()) {
           firstLabel.setText("Inserisci la prima dimensione");
           firstTextField.setVisible(true);
           secondLabel.setText("Inserisci la seconda dimensione");
           secondTextField.setVisible(true);
       }
       if(threeDRadioButton.isSelected()){
           firstLabel.setText("Inserisci la prima dimensione");
           firstTextField.setVisible(true);
           secondLabel.setText("Inserisci la seconda dimensione");
           secondTextField.setVisible(true);
           thirdLabel.setText("Inserisci la terza dimensione");
           thirdTextField.setVisible(true);
       }
       oneDRadioButton.setDisable(true);
       twoDRadioButton.setDisable(true);
       threeDRadioButton.setDisable(true);
}

}
