package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import java.io.IOException;
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
private ToggleGroup dimensionChoosedToggleGroup;
private ToggleGroup ruleChoosedToggleGroup;
private ToggleGroup positionChoosedToggleGroup;
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
        this.firstLabel.setText("");
        this.secondLabel.setText("");
        this.thirdLabel.setText("");
        this.firstTextField.setVisible(false);
        this.secondTextField.setVisible(false);
        this.thirdTextField.setVisible(false);
        this.button1.setDisable(true);
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
   public void loadGrid(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GameOfLifeSecondScene.fxml"));
        AnchorPane gridViewParent = loader.load();
        GUIViewSecondSceneController secondController = loader.getController();
        RadioButton dimensionRadioButton = (RadioButton) dimensionChoosedToggleGroup.getSelectedToggle();
        Integer dimension = Integer.parseInt(dimensionRadioButton.getText());
        RadioButton positionRadioButton =  (RadioButton)positionChoosedToggleGroup.getSelectedToggle();
        String position = positionRadioButton.getText();
        RadioButton ruleRadioButton = (RadioButton) ruleChoosedToggleGroup.getSelectedToggle();
        String rule = ruleRadioButton.getText();
        secondController.initializeGRASPController(position,dimension,rule);
        GridPane grid = secondController.initGrid();
        gridViewParent.getChildren().add(grid);
        grid.setLayoutX(280);
        grid.setLayoutY(155);
        Scene gridViewScene =  new Scene (gridViewParent);
    Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    window.setScene(gridViewScene);
    window.show();
    }


    //controllo da fare ogni qual volta si seleziona un qualcosa per vedere se si può effettivamente continuare
    //oppure no perchè mancano ancora delle info
    private boolean nextIsPossible(){
        return positionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && ruleChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && dimensionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && (oneDRadioButton.isDisable() && (!firstTextField.isVisible () || !firstTextField.getText().isEmpty()))
                && (oneDRadioButton.isDisable() && (!secondTextField.isVisible () || !secondTextField.getText().isEmpty()))
                && (oneDRadioButton.isDisable() && (!thirdTextField.isVisible () || !thirdTextField.getText().isEmpty()));
    }



    @FXML public void checkOnNextButton(MouseEvent mouseEvent) {
       if(nextIsPossible())
            this.button1.setDisable(false);
    }
}
