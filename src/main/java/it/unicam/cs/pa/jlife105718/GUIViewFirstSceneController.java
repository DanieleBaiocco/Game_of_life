package it.unicam.cs.pa.jlife105718;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GUIViewFirstSceneController  {
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
@FXML private RadioButton firstKnownRadioButton;
@FXML private RadioButton secondKnownRadioButton;
@FXML private RadioButton thirdKnownRadioButton;
@FXML private RadioButton fourthKnownRadioButton;
@FXML private RadioButton fifthKnownRadioButton;
@FXML private Button loadButton;
@FXML private Label firstLabel;
@FXML private Label secondLabel;
@FXML private Label thirdLabel;
@FXML private TextField firstTextField;
@FXML private TextField secondTextField;
@FXML private TextField thirdTextField;
@FXML private Button nextButton;
@FXML private Button backButton;
@FXML private Label firstErrorRedLabel;
@FXML private Label secondErrorRedLabel;
@FXML private Label thirdErrorRedLabel;
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
@FXML private Label loadRedLabel;
private  final FileChooser fileChooser = new FileChooser();
private Desktop desktop ;
private final ToggleGroup dimensionChoosedToggleGroup = new ToggleGroup();
private final ToggleGroup ruleChoosedToggleGroup= new ToggleGroup();
private final ToggleGroup positionChoosedToggleGroup= new ToggleGroup();
private final ToggleGroup differentKnownConfigurations = new ToggleGroup();
private Function<List<Integer>, ? extends IPosizione> functionSelected;
private int firstMaxCoordinate;
private int secondMaxCoordinate;
private int thirdMaxCoordinate;
private ICampo<?> fieldSelected;
private CurrentRulesEnum ruleSelected;
private int[] cellsToSetAlive;

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
private void resetToggles(ToggleGroup ... toggleGroups){
    Arrays.stream(toggleGroups).filter(x->x.getSelectedToggle()!=null).forEach(x->x.getSelectedToggle().setSelected(false));
}
    @FXML
    public void initialize() {
        initComboBoxe(choose1,"Block", "Bee-hive", "Loaf", "Boat", "Tub");
        initComboBoxe(choose2,"Blinker","Toad", "Beacon", "Pulsar", "Penta-decathlon");;
        initComboBoxe(choose3, "Gosper-glider-gun","Simkin-glider-gun", "Simple-glider", "Light-weight-spaceship", "Middle-weight-spaceship", "Heavy-weight-spaceship");
        initComboBoxe(choose4, "Diehard");
        initComboBoxe(choose5, "Infinite-growth-1", "Infinite-growth-2", "Infinite-growth-3");
        desktop = Desktop.getDesktop();
        initToggleGroup(dimensionChoosedToggleGroup,oneDRadioButton,twoDRadioButton,threeDRadioButton);
        initToggleGroup(ruleChoosedToggleGroup,standardRuleRadioButton,alternativeRuleRadioButton,alternativeRule2RadioButton);
        initToggleGroup(positionChoosedToggleGroup, integerNumbersRadioButton, doubleNumbersRadioButton, alphabetRadioButton);
        initToggleGroup(differentKnownConfigurations, firstKnownRadioButton, secondKnownRadioButton,thirdKnownRadioButton,fourthKnownRadioButton,fifthKnownRadioButton);
        changeNodes(x->((TextField)x).setText(""), firstTextField, secondTextField, thirdTextField);
        changeNodes(x->x.setVisible(false), loadRedLabel, firstTextField, secondTextField, thirdTextField, firstLabel, secondLabel, thirdLabel, loadButton);
        changeNodes(x->x.setDisable(true),  nextButton, backButton);
        changeNodes(x->((Pane)x).getChildren().forEach(y->y.setDisable(true)),rightPane,leftPane,centralPane);
}

@FXML public void createGridFromAPreconfiguredGrid(MouseEvent mouseEvent){
    reset();
    String radioButtonClicked = ((RadioButton)mouseEvent.getSource()).getText();
    String basePath = new File("").getAbsolutePath();
    buildControllerFromRadioButton(radioButtonClicked, basePath);
    nextButton.setDisable(false);
}

private void buildControllerFromRadioButton(String radioButtonClicked, String basePath) {
    Controller controller = null;
    String pathThroughDirectories = "/src/main/resources/preconfiguredGridsInJson/";
    String halfPath = basePath.concat(pathThroughDirectories);
    try {
        switch (radioButtonClicked){
            case "STILL LIFES":
                controller = Controller.createControllerFromFile(halfPath.concat(buildEndPath(choose1)));
                break;
            case "OSCILLATORS":
                controller = Controller.createControllerFromFile(halfPath.concat(buildEndPath(choose2)));
                break;
            case "SPACESHIPS":
                controller = Controller.createControllerFromFile(halfPath.concat(buildEndPath(choose3)));
                break;
            case "METHUSELAHS" :
                controller = Controller.createControllerFromFile(halfPath.concat(buildEndPath(choose4)));
                break;
            case "INFINITE GROWTH":
                controller = Controller.createControllerFromFile(halfPath.concat(buildEndPath(choose5)));
                break;
            }
        initGRASPControllerVars(controller);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}

@FXML void createGridFromInitialization(){
    try {
        if(firstErrorRedLabel.getText().equals("") && secondErrorRedLabel.getText().equals("") && thirdErrorRedLabel.getText().equals("")){
          functionSelected = Utility.switchOnPositionChoosed(getStringFromToggle(positionChoosedToggleGroup));
          ruleSelected = Utility.switchOnRuleChoosed(getStringFromToggle(ruleChoosedToggleGroup));
          fieldSelected = Utility.switchOnDimensionChoosed(getStringFromToggle(dimensionChoosedToggleGroup),
                  ()->new Campo1D<>(functionSelected,Integer.parseInt(firstTextField.getText())),
                  ()->new Campo2D<>(functionSelected,Integer.parseInt(firstTextField.getText()),
                          Integer.parseInt(secondTextField.getText())),
                  ()->new Campo3D<>(functionSelected,Integer.parseInt(firstTextField.getText()),
                          Integer.parseInt(secondTextField.getText()),
                          Integer.parseInt(thirdTextField.getText())));
          nextButton.setDisable(false);
          loadRedLabel.setText("");
          loadButton.setVisible(false);
        } else{
            changeNodes(x->x.setVisible(true), loadRedLabel);
            changeNodes(x->((Label)x).setText("Inserisci i valori corretti"),loadRedLabel);
        }
    }
    catch (NumberFormatException e){
        changeNodes(x->x.setVisible(true),loadRedLabel);
        changeNodes(x->((Label)x).setText("TextField vuoto/i"), loadRedLabel);
    }
    //forse è giusto levare la IllegalArgumentException dal costruttore di Campo e da qua(?)
    catch (NullPointerException e){
       changeNodes(x->x.setVisible(true),loadRedLabel);
       changeNodes(x->((Label)x).setText("Completa la scelta"), loadRedLabel);
    }
}


private void initGRASPControllerVars(Controller controller){
    fieldSelected = controller.getCampo();
    ruleSelected = controller.getRule();
    cellsToSetAlive = controller.getCellsToSetAlive();
}

private String buildEndPath(ComboBox comboBox){
    return comboBox.getSelectionModel().getSelectedItem().toString().concat(".json");
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
    backButton.setDisable(true);
    resetToggles(dimensionChoosedToggleGroup,positionChoosedToggleGroup,differentKnownConfigurations,ruleChoosedToggleGroup);
    initialize();
    reset();
}

private void reset() {
    cellsToSetAlive = null;
    fieldSelected= null;
    ruleSelected = null;
    functionSelected = null;
    firstMaxCoordinate = -1;
    secondMaxCoordinate = -1;
    thirdMaxCoordinate = -1;
}

    @FXML public void createGridFromFile(MouseEvent mouseEvent) throws IOException {
        reset();
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        Controller createdController = Controller.createControllerFromFile(file.getPath());
        initGRASPControllerVars(createdController);
        nextButton.setDisable(false);
    }

    @FXML public void showDimensionLabelsAndTexts(MouseEvent mouseEvent) {
    Utility.switchOnDimensionChoosed(getStringFromToggle(dimensionChoosedToggleGroup),
            ()->{changeNodes(x->x.setVisible(true),firstLabel,firstTextField);
            changeNodes(x->x.setVisible(false), secondLabel, secondTextField, thirdLabel, thirdTextField);
            return null;},
            ()->{ changeNodes(x->x.setVisible(true),firstLabel,firstTextField,secondLabel,secondTextField);
            changeNodes(x->x.setVisible(false), thirdTextField, thirdLabel);
            return null;
            },
            ()->{changeNodes(x->x.setVisible(true),firstLabel,firstTextField,secondLabel,secondTextField,thirdLabel,thirdTextField);
            return null;
    });
    loadButton.setVisible(true);
}

    private String getStringFromToggle(ToggleGroup toggleGroup) {
    return ((RadioButton)toggleGroup.getSelectedToggle()).getText();
}

   @FXML
   public void loadGrid(MouseEvent mouseEvent)  throws IOException{
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("/GameOfLifeSecondScene.fxml"));
       AnchorPane gridViewParent = loader.load();
       GUIViewSecondSceneController secondController = loader.getController();
       secondController.initializeGRASPController(fieldSelected,ruleSelected, cellsToSetAlive);
       secondController.initGrid();
       Scene gridViewScene =  new Scene (gridViewParent);
       Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
       window.setScene(gridViewScene);
       window.show();
    }

    //le eccezioni lanciate dovrebbero esser dgenerate dalla view
    @FXML public void checkMaxCoordinate() {
      /*  if (oneDRadioButton.isSelected()) {
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
*/
        }
        //FAI IN MODO DI REFACTORARE IL CODICE PER NON AVERLO RIPETUTO PER GLI ALTRI DUE CASI
   // }

}
