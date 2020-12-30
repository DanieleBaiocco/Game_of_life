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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class GUIViewFirstSceneController implements Initializable { @FXML private AnchorPane mainAnchorPane;
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
@FXML private Button nextButton;
@FXML private Label confirmRedLabel;
@FXML private Label errorRedLabel;
@FXML private Button loadFromFileButton;
@FXML private ComboBox choose1;
@FXML private ComboBox choose2;
@FXML private ComboBox choose3;
@FXML private ComboBox choose4;
@FXML private ComboBox choose5;
private  final FileChooser fileChooser = new FileChooser();
private Desktop desktop ;
private ToggleGroup dimensionChoosedToggleGroup;
private ToggleGroup ruleChoosedToggleGroup;
private ToggleGroup positionChoosedToggleGroup;
private Function<List<Integer>, ? extends IPosizione> function;
private ICampo<?> campo;
private CurrentRulesEnum rule;
private boolean flag;
private int[] cellsToColorate;


private void initComboBoxe(ComboBox comboBox, String ... elements){
    comboBox.getItems().removeAll(comboBox.getItems());
    comboBox.getItems().addAll(elements);
    comboBox.getSelectionModel().select(elements[0]);
}

private void initToggleGroup( ToggleGroup toggleGroup, RadioButton ... radioButtons){
    toggleGroup = new ToggleGroup();
    ToggleGroup finalToggleGroup = toggleGroup;
    Arrays.stream(radioButtons).forEach(x->x.setToggleGroup(finalToggleGroup));
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxe(choose1,"Block", "Bee-hive", "Loaf", "Boat", "Tub");
        initComboBoxe(choose2,"Blinker","Toad", "Beacon", "Pulsar", "Penta-decathlon");;
        initComboBoxe(choose3, "Gosper glider gun","Simkin glider gun", "simple glider", "Light weight spaceship", "Middle weight spaceship", "Heavy weight spaceship");
        initComboBoxe(choose4,"The R-pentomino", "Diehard", "Acorn");
        initComboBoxe(choose5, "Infinite growth 1", "Infinite growth 2", "Infinite growth 3");

        flag= true;
        desktop = Desktop.getDesktop();

        initToggleGroup(dimensionChoosedToggleGroup,oneDRadioButton,twoDRadioButton,threeDRadioButton);
        initToggleGroup(ruleChoosedToggleGroup,standardRuleRadioButton,alternativeRuleRadioButton,alternativeRule2RadioButton);
        initToggleGroup(positionChoosedToggleGroup, integerNumbersRadioButton, doubleNumbersRadioButton, alphabetRadioButton);

        this.firstLabel.setText("");
        this.secondLabel.setText("");
        this.thirdLabel.setText("");

        this.firstTextField.setVisible(false);
        this.secondTextField.setVisible(false);
        this.thirdTextField.setVisible(false);
        this.nextButton.setDisable(true);
        //redlabels
        errorRedLabel.setText("");
        confirmRedLabel.setText("");
    }

    @FXML public void loadConfigFromFile(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        String pathName = file.getPath();
        Controller controller =Controller.createControllerFromFile(pathName);
        this.campo = controller.getCampo();
        this.rule = controller.getRule();
        this.flag = false;
        this.cellsToColorate =Controller.getListOfCellsToColorate(pathName);
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

         if (standardRuleRadioButton.isSelected()) {
             this.rule = CurrentRulesEnum.BasicRules;
         } else if (alternativeRuleRadioButton.isSelected()) {
             this.rule = CurrentRulesEnum.AlternativeRules;
         } else {
             this.rule = CurrentRulesEnum.AlternativeRules;
         }
         if(!confirmRedLabel.getText().equals(""))
             confirmRedLabel.setText("");
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
         confirmRedLabel.setText("Completa la scelta");
     }
}

   @FXML
   public void loadGrid(MouseEvent mouseEvent)  throws IOException{
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("/GameOfLifeSecondScene.fxml"));
       AnchorPane gridViewParent = loader.load();
       GUIViewSecondSceneController secondController = loader.getController();
       if(this.flag) {
           int value1 = Integer.parseInt(firstTextField.getText());
           int value2 = Integer.parseInt(secondTextField.getText());
           int value3 = Integer.parseInt(thirdTextField.getText());
           if (oneDRadioButton.isSelected()) {
               this.campo = new Campo1D<>(this.function, value1);
               firstLabel.setText("Inserisci la prima dimensione");
               firstTextField.setVisible(true);
           } else if (twoDRadioButton.isSelected()) {
               this.campo = new Campo2D<>(this.function, value1, value2);
               firstLabel.setText("Inserisci la prima dimensione");
               firstTextField.setVisible(true);
               secondLabel.setText("Inserisci la seconda dimensione");
               secondTextField.setVisible(true);
           } else {
               this.campo = new Campo3D<>(this.function, value1, value2, value3);
               firstLabel.setText("Inserisci la prima dimensione");
               firstTextField.setVisible(true);
               secondLabel.setText("Inserisci la seconda dimensione");
               secondTextField.setVisible(true);
               thirdLabel.setText("Inserisci la terza dimensione");
               thirdTextField.setVisible(true);
           }
       }
        secondController.initializeGRASPController(this.campo,this.rule);
        if(!flag){
            secondController.setFlag(false);
            secondController.setCellsToColorate(cellsToColorate);
        }
        secondController.initGrid();
        Scene gridViewScene =  new Scene (gridViewParent);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(gridViewScene);
        window.show();
    }


    //controllo da fare ogni qual volta si seleziona un qualcosa per vedere se si può effettivamente continuare
    //oppure no perchè mancano ancora delle info
    private boolean nextIsPossible(){
        //solo x ora
        return true;
                /*positionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && ruleChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && dimensionChoosedToggleGroup.getToggles().stream().anyMatch(Toggle::isSelected)
                && (oneDRadioButton.isDisable() && (!firstTextField.isVisible () || !firstTextField.getText().isEmpty()))
                && (oneDRadioButton.isDisable() && (!secondTextField.isVisible () || !secondTextField.getText().isEmpty()))
                && (oneDRadioButton.isDisable() && (!thirdTextField.isVisible () || !thirdTextField.getText().isEmpty()))
                && firstRedLabel.getText().equals("");*/
    }



    @FXML public void checkOnNextButton() {
        if (nextIsPossible()) {
          this.nextButton.setDisable(false);
        }

    }


    //le eccezioni lanciate dovrebbero esser dgenerate dalla view
    @FXML public void checkMaxCoordinate() {
        if (oneDRadioButton.isSelected()) {
            int x = 0;
            try {
                x = Integer.parseInt(firstTextField.getText());
                errorRedLabel.setText("");
                if (alphabetRadioButton.isSelected()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    function.apply(list);
                }
            } catch (NumberFormatException e) {
                errorRedLabel.setText("Inserire un NUMERO");
            } catch (IllegalArgumentException e) {
                errorRedLabel.setText(e.getMessage());
            }

        } else if (twoDRadioButton.isSelected()) {
            int x = 0;
            int y=0;
            try {
                x = Integer.parseInt(firstTextField.getText());
                y = Integer.parseInt(secondTextField.getText());
                errorRedLabel.setText("");
                if (alphabetRadioButton.isSelected()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    function.apply(list);
                }
            } catch (NumberFormatException e) {
                errorRedLabel.setText("Inserire un NUMERO");
            } catch (IllegalArgumentException e) {
                errorRedLabel.setText(e.getMessage());
            }

        } else {
            int x = 0;
            int y=0;
            int z=0;
            try {
                x = Integer.parseInt(firstTextField.getText());
                y = Integer.parseInt(secondTextField.getText());
                z = Integer.parseInt(thirdTextField.getText());
                errorRedLabel.setText("");
                if (alphabetRadioButton.isSelected()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    list.add(z);
                    function.apply(list);
                }
            } catch (NumberFormatException e) {
                errorRedLabel.setText("Inserire un NUMERO");
            } catch (IllegalArgumentException e) {
                errorRedLabel.setText(e.getMessage());
            }

        }
        //FAI IN MODO DI REFACTORARE IL CODICE PER NON AVERLO RIPETUTO PER GLI ALTRI DUE CASI
    }

}
