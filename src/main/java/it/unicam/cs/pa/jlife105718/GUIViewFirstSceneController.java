package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
@FXML private RadioButton firstKnownRadioButton;
@FXML private RadioButton secondKnownRadioButton;
@FXML private RadioButton thirdKnownRadioButton;
@FXML private RadioButton fourthKnownRadioButton;
@FXML private RadioButton fifthKnownRadioButton;
@FXML private Button confirmDimensionButton;
@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
@FXML private TextField firstTextField;
@FXML private TextField secondTextField;
@FXML private TextField thirdTextField;
@FXML private Button nextButton;
@FXML private Button backButton;
@FXML private Label confirmDimensionRedLabel;
@FXML private Label errorRedLabel;
@FXML private Button loadFromFileButton;
@FXML private ComboBox choose1;
@FXML private ComboBox choose2;
@FXML private ComboBox choose3;
@FXML private ComboBox choose4;
@FXML private ComboBox choose5;
@FXML private BorderPane borderPane;
@FXML private Pane leftPane;
@FXML private Pane centralPane;
@FXML private Pane rightPane;
private  final FileChooser fileChooser = new FileChooser();
private Desktop desktop ;
private final ToggleGroup dimensionChoosedToggleGroup = new ToggleGroup();
private final ToggleGroup ruleChoosedToggleGroup= new ToggleGroup();
private final ToggleGroup positionChoosedToggleGroup= new ToggleGroup();
private final ToggleGroup differentKnownConfigurations = new ToggleGroup();
private Function<List<Integer>, ? extends IPosizione> functionSelected;
private ICampo<?> campo;
private CurrentRulesEnum ruleSelected;
private boolean flag;
private int[] cellsToColorate;

private void initComboBoxe(ComboBox comboBox, String ... elements){
    comboBox.getItems().removeAll(comboBox.getItems());
    comboBox.getItems().addAll(elements);
    comboBox.getSelectionModel().select(elements[0]);
}

private void initToggleGroup( ToggleGroup toggleGroup, RadioButton ... radioButtons){
    Arrays.stream(radioButtons).forEach(x->x.setToggleGroup(toggleGroup));
}

private void changeNodes(Consumer<Node> consumer, Node... nodes){
    Arrays.stream(nodes).forEach(consumer);
}

private List<Pane> getFilteredPanes(Predicate<Node> predicate){
    return  borderPane.getChildren()
            .stream()
            .map(x->(Pane)x)
            .filter(x->(x.getChildren()
                    .stream()
                    .filter(predicate))
                    .count()==x.getChildren().size())
            .collect(Collectors.toList());
}

private boolean hasNotOneActivatedPane(){
    return getFilteredPanes(Node::isDisable).size()!=2;
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
        initToggleGroup(differentKnownConfigurations, firstKnownRadioButton, secondKnownRadioButton,thirdKnownRadioButton,fourthKnownRadioButton,fifthKnownRadioButton);
        changeNodes(x->x.setVisible(false), confirmDimensionRedLabel, errorRedLabel, firstTextField, secondTextField, thirdTextField, firstLabel, secondLabel, thirdLabel);
        changeNodes(x->x.setDisable(true),  nextButton, backButton);
        changeNodes(x->((Pane)x).getChildren().forEach(y->y.setDisable(true)),rightPane,leftPane,centralPane);

}

@FXML public void changeToHandCursor(MouseEvent mouseEvent){
    ((Node)mouseEvent.getSource()).getScene().setCursor(Cursor.HAND);
}

@FXML public void changeToDefaultCursor(MouseEvent mouseEvent){
    ((Node)mouseEvent.getSource()).getScene().setCursor(Cursor.DEFAULT);
}

@FXML public void overThePane(MouseEvent mouseEvent){
     if(hasNotOneActivatedPane()){
       changeToHandCursor(mouseEvent);
       ((Node)mouseEvent.getSource()).setStyle("-fx-background-color: pink");
    }
}

@FXML public void exitThePane(MouseEvent mouseEvent){
    if(hasNotOneActivatedPane()) {
        changeToDefaultCursor(mouseEvent);
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: #f4f4f4");
        ((Node) mouseEvent.getSource()).setStyle("-fx-border-color: black");
    }
}

@FXML public void unlockThisPane(MouseEvent mouseEvent){
   if(hasNotOneActivatedPane()) {
        ((Pane) mouseEvent.getSource()).getChildren().forEach(x -> x.setDisable(false));
        changeToDefaultCursor(mouseEvent);
        backButton.setDisable(false);
   }
}

@FXML public void goBackToNoPaneChoosed(){
    List<Pane> panes= getFilteredPanes(y->!(y.isDisable()));
    Pane previouslySelectedPane = panes.get(1);
    previouslySelectedPane.getChildren().forEach(x->x.setDisable(true));
    backButton.setDisable(true);
}

@FXML public void loadConfigFromFile(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        String pathName = file.getPath();
        Controller controller =Controller.createControllerFromFile(pathName);
        this.campo = controller.getCampo();
        this.ruleSelected = controller.getRule();
        this.flag = false;
        this.cellsToColorate =Controller.getListOfCellsToColorate(pathName);
    }

    @FXML public void showDimensionLabelsAndTexts(MouseEvent mouseEvent) {
     if (positionChoosedToggleGroup.getSelectedToggle()!=null
     && dimensionChoosedToggleGroup.getSelectedToggle()!=null
     && ruleChoosedToggleGroup.getSelectedToggle()!=null) {
           switchOnDimensionChoosed(getStringFromToggle(dimensionChoosedToggleGroup),
                   ()->changeNodes(x->x.setVisible(true),firstLabel,firstTextField),
                   ()->changeNodes(x->x.setVisible(true),firstLabel,firstTextField,secondLabel,secondTextField),
                   ()->changeNodes(x->x.setVisible(true),firstLabel,firstTextField,secondLabel,secondTextField,thirdLabel,thirdTextField ));
           switchOnPositionChoosed(getStringFromToggle(positionChoosedToggleGroup));
           switchOnRuleChoosed(getStringFromToggle(ruleChoosedToggleGroup));
           if(!confirmDimensionRedLabel.getText().equals(""))
               confirmDimensionRedLabel.setText("");
         dimensionChoosedToggleGroup.getToggles().stream().map(x->(RadioButton)x).forEach(x->x.setDisable(true));
         positionChoosedToggleGroup.getToggles().stream().map(x->(RadioButton)x).forEach(x->x.setDisable(true));
         ruleChoosedToggleGroup.getToggles().stream().map(x->(RadioButton)x).forEach(x->x.setDisable(true));
     }  else{
         changeNodes(x->x.setVisible(true),confirmDimensionRedLabel);
     }
}

    private String getStringFromToggle(ToggleGroup toggleGroup) {
    return ((RadioButton)toggleGroup.getSelectedToggle()).getText();
}

    private void switchOnDimensionChoosed(String dimensionChoosed, Runnable runnable1, Runnable runnable2, Runnable runnable3){
    switch(dimensionChoosed){
        case "1D": {
            runnable1.run();
            break;
        }
        case "2D": {
            runnable2.run();
            break;
        }
        case "3D": {
            runnable3.run();
            break;
        }
    }
}

private void switchOnPositionChoosed(String positionChoosed){
    switch (positionChoosed){
        case "Alfabetico":
            functionSelected = TransitionFactory.getInstance().getTransitionToChar();
            break;
        case "Numerico":
            functionSelected = TransitionFactory.getInstance().getTransitionToInteger();
            break;
        case "Virgola Mobile" :
            functionSelected = TransitionFactory.getInstance().getTransitionToDouble();
            break;
    }
}

private void switchOnRuleChoosed(String ruleChoosed){
    switch (ruleChoosed){
        case "Standard":
            ruleSelected =  CurrentRulesEnum.BasicRules;
            break;
        case "Alternativa1":
            ruleSelected = CurrentRulesEnum.AlternativeRules1;
            break;
        case "Alternativa2":
            ruleSelected = CurrentRulesEnum.AlternativeRules2;
            break;
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
               this.campo = new Campo1D<>(this.functionSelected, value1);
               firstLabel.setText("Inserisci la prima dimensione");
               firstTextField.setVisible(true);
           } else if (twoDRadioButton.isSelected()) {
               this.campo = new Campo2D<>(this.functionSelected, value1, value2);
               firstLabel.setText("Inserisci la prima dimensione");
               firstTextField.setVisible(true);
               secondLabel.setText("Inserisci la seconda dimensione");
               secondTextField.setVisible(true);
           } else {
               this.campo = new Campo3D<>(this.functionSelected, value1, value2, value3);
               firstLabel.setText("Inserisci la prima dimensione");
               firstTextField.setVisible(true);
               secondLabel.setText("Inserisci la seconda dimensione");
               secondTextField.setVisible(true);
               thirdLabel.setText("Inserisci la terza dimensione");
               thirdTextField.setVisible(true);
           }
       }
        secondController.initializeGRASPController(this.campo,this.ruleSelected);
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
                    functionSelected.apply(list);
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
                    functionSelected.apply(list);
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
                    functionSelected.apply(list);
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
