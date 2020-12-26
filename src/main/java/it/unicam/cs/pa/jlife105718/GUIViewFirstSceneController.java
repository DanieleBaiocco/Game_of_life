package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

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
@FXML private Label redLabel;
@FXML private Label firstRedLabel;
@FXML private Label secondRedLabel;
@FXML private Label thirdRedLabel;
@FXML private Button loadFromFileButton;
private  final FileChooser fileChooser = new FileChooser();
private Desktop desktop ;
private ToggleGroup dimensionChoosedToggleGroup;
private ToggleGroup ruleChoosedToggleGroup;
private ToggleGroup positionChoosedToggleGroup;
private Function<List<Integer>, ? extends IPosizione> function;
private Campo<?> campo;
private CurrentRulesEnum rule;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        desktop = Desktop.getDesktop();
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
        //redlabels
        firstRedLabel.setText("");
        secondRedLabel.setText("");
        thirdRedLabel.setText("");
        redLabel.setText("");
    }

    @FXML public void loadConfigFromFile(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        String[] fileSplitted = file.getName().split("\\.");
        System.out.println( fileSplitted[fileSplitted.length-1]);
        Controller.createControllerFromFile(file);
    }

   @FXML public void loadLabelsAndTexts(MouseEvent mouseEvent) {
     if (positionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
     && dimensionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
     && ruleChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)) {
         if (integerNumbersRadioButton.isSelected()) {
             this.function = TransitionFactory.getInstance().getTransitionToInteger();
         } else if (doubleNumbersRadioButton.isSelected()) {
             this.function = TransitionFactory.getInstance().getTransitionToDouble();
         } else {
             this.function = TransitionFactory.getInstance().getTransitionToChar();
         }
         if(oneDRadioButton.isSelected()) {
             this.campo = new Campo1D<>(this.function);
             firstLabel.setText("Inserisci la prima dimensione");
             firstTextField.setVisible(true);
         }else if(twoDRadioButton.isSelected()) {
             this.campo = new Campo2D<>(this.function);
             firstLabel.setText("Inserisci la prima dimensione");
             firstTextField.setVisible(true);
             secondLabel.setText("Inserisci la seconda dimensione");
             secondTextField.setVisible(true);
         }else {
             this.campo = new Campo3D<>(this.function);
             firstLabel.setText("Inserisci la prima dimensione");
             firstTextField.setVisible(true);
             secondLabel.setText("Inserisci la seconda dimensione");
             secondTextField.setVisible(true);
             thirdLabel.setText("Inserisci la terza dimensione");
             thirdTextField.setVisible(true);
         }
         if (standardRuleRadioButton.isSelected()) {
             this.rule = CurrentRulesEnum.BasicRules;
         } else if (alternativeRuleRadioButton.isSelected()) {
             this.rule = CurrentRulesEnum.AlternativeRules;
         } else {
             this.rule = CurrentRulesEnum.AlternativeRules;
         }
         if(!redLabel.getText().equals(""))
             redLabel.setText("");
         oneDRadioButton.setDisable(true);
         twoDRadioButton.setDisable(true);
         threeDRadioButton.setDisable(true);
         integerNumbersRadioButton.setDisable(true);
         doubleNumbersRadioButton.setDisable(true);
         alphabetRadioButton.setDisable(true);
         standardRuleRadioButton.setDisable(true);
         alternativeRuleRadioButton.setDisable(true);
         alternativeRule2RadioButton.setDisable(true);
     }  else{
         redLabel.setText("Completa la scelta");
     }
}

   @FXML
   public void loadGrid(MouseEvent mouseEvent)  throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GameOfLifeSecondScene.fxml"));
        AnchorPane gridViewParent = loader.load();
        GUIViewSecondSceneController secondController = loader.getController();
        secondController.initializeGRASPController(this.campo,this.rule);
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(firstTextField.getText()));
        if(twoDRadioButton.isSelected())
            list.add(Integer.parseInt(secondTextField.getText()));
        else if (threeDRadioButton.isSelected()){
            list.add(Integer.parseInt(secondTextField.getText()));
            list.add(Integer.parseInt(thirdTextField.getText()));
        }
        secondController.initGrid(list);
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
                && (oneDRadioButton.isDisable() && (!thirdTextField.isVisible () || !thirdTextField.getText().isEmpty()))
                && firstRedLabel.getText().equals("");
    }



    @FXML public void checkOnNextButton() {
        if (nextIsPossible()) {
          this.button1.setDisable(false);
        }

    }


    //le eccezioni lanciate dovrebbero esser dgenerate dalla view
    @FXML public void checkMaxCoordinate() {
        if (oneDRadioButton.isSelected()) {
            int x = 0;
            try {
                x = Integer.parseInt(firstTextField.getText());
                firstRedLabel.setText("");
                if (alphabetRadioButton.isSelected()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    function.apply(list);
                }
            } catch (NumberFormatException e) {
                firstRedLabel.setText("Inserire un NUMERO");
            } catch (IllegalArgumentException e) {
                firstRedLabel.setText(e.getMessage());
            }

        } else if (twoDRadioButton.isSelected()) {
            int x = 0;
            int y=0;
            try {
                x = Integer.parseInt(firstTextField.getText());
                y = Integer.parseInt(secondTextField.getText());
                firstRedLabel.setText("");
                if (alphabetRadioButton.isSelected()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    function.apply(list);
                }
            } catch (NumberFormatException e) {
                firstRedLabel.setText("Inserire un NUMERO");
            } catch (IllegalArgumentException e) {
                firstRedLabel.setText(e.getMessage());
            }

        } else {
            int x = 0;
            int y=0;
            int z=0;
            try {
                x = Integer.parseInt(firstTextField.getText());
                y = Integer.parseInt(secondTextField.getText());
                z = Integer.parseInt(thirdTextField.getText());
                firstRedLabel.setText("");
                if (alphabetRadioButton.isSelected()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    list.add(z);
                    function.apply(list);
                }
            } catch (NumberFormatException e) {
                firstRedLabel.setText("Inserire un NUMERO");
            } catch (IllegalArgumentException e) {
                firstRedLabel.setText(e.getMessage());
            }

        }
        //FAI IN MODO DI REFACTORARE IL CODICE PER NON AVERLO RIPETUTO PER GLI ALTRI DUE CASI
    }

}
