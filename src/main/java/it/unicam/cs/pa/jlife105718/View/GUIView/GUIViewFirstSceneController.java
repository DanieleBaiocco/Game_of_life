package it.unicam.cs.pa.jlife105718.View.GUIView;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unicam.cs.pa.jlife105718.Controller.IController;
import it.unicam.cs.pa.jlife105718.Controller.MyGameOfLifeController;
import it.unicam.cs.pa.jlife105718.Model.Board.*;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;
import it.unicam.cs.pa.jlife105718.Model.Utility;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GUIViewFirstSceneController  {
private static final Logger logger = Logger.getGlobal();
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
@FXML private Label loadFromFileErrorLabel;
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
private int firstMaxCoordinate;
private int secondMaxCoordinate;
private int thirdMaxCoordinate;
private IController<?> controllerCreated;

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
        changeNodes(x->((Label)x).setText(""),firstErrorRedLabel, secondErrorRedLabel, thirdErrorRedLabel);
        changeNodes(x->x.setVisible(false), loadRedLabel, firstTextField, secondTextField, thirdTextField, firstLabel, secondLabel, thirdLabel, loadButton, firstErrorRedLabel, secondErrorRedLabel, thirdErrorRedLabel, loadFromFileErrorLabel);
        changeNodes(x->x.setDisable(true),  nextButton, backButton);
        changeNodes(x->((Pane)x).getChildren().forEach(y->y.setDisable(true)),rightPane,leftPane,centralPane);
        logger.info("GUIViewFirstSceneController initialized");
}

@FXML public void createGridFromAPreconfiguredGrid(MouseEvent mouseEvent){
    reset();
    String radioButtonClicked = ((RadioButton)mouseEvent.getSource()).getText();
    String basePath = new File("").getAbsolutePath();
    buildControllerFromRadioButton(radioButtonClicked, basePath);
    nextButton.setDisable(false);
    logger.info("Grid selected from preconfigured grids");
}

private void buildControllerFromRadioButton(String radioButtonClicked, String basePath) {
    String pathThroughDirectories = "/src/main/resources/preconfiguredGridsInJson/";
    String halfPath = basePath.concat(pathThroughDirectories);
    try {
        switch (radioButtonClicked){
            case "STILL LIFES":
                controllerCreated = IController.createControllerFromFile(halfPath.concat(buildEndPath(choose1)));
                break;
            case "OSCILLATORS":
                controllerCreated = IController.createControllerFromFile(halfPath.concat(buildEndPath(choose2)));
                break;
            case "SPACESHIPS":
                controllerCreated = IController.createControllerFromFile(halfPath.concat(buildEndPath(choose3)));
                break;
            case "METHUSELAHS" :
                controllerCreated = IController.createControllerFromFile(halfPath.concat(buildEndPath(choose4)));
                break;
            case "INFINITE GROWTH":
                controllerCreated = IController.createControllerFromFile(halfPath.concat(buildEndPath(choose5)));
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.severe("File not found!");
        }
}

@FXML <T extends IPosition> void createGridFromInitialization(){
    IFactoryField factoryField =new MyFactoryField();
    PositionsEnum functionSelected;
    IField<T> fieldSelected;
    RulesEnum ruleSelected;
    try {
        if(firstErrorRedLabel.getText().equals("") && secondErrorRedLabel.getText().equals("") && thirdErrorRedLabel.getText().equals("")){
          functionSelected = Utility.switchOnPositionChoosed(getStringFromToggle(positionChoosedToggleGroup));
          logger.info("Creation from manual initalization of Position done.");
          ruleSelected = Utility.switchOnRuleChoosed(getStringFromToggle(ruleChoosedToggleGroup));
          logger.finest("Creation from manual initalization of Rule done.");
          fieldSelected = Utility.switchOnDimensionChoosed(getStringFromToggle(dimensionChoosedToggleGroup),
                  ()->factoryField.createField1D(functionSelected,Integer.parseInt(firstTextField.getText())),
                  ()->factoryField.createField2D(functionSelected,Integer.parseInt(firstTextField.getText()),
                          Integer.parseInt(secondTextField.getText())),
                  ()->factoryField.createField3D(functionSelected,Integer.parseInt(firstTextField.getText()),
                          Integer.parseInt(secondTextField.getText()),
                          Integer.parseInt(thirdTextField.getText())));
          logger.info("Creation from manual initalization of Position done.");
          controllerCreated = new MyGameOfLifeController<>(fieldSelected,ruleSelected, null);
          logger.info("Creation from manual initalization of Controller done.");
          nextButton.setDisable(false);
          loadRedLabel.setText("");
          loadButton.setVisible(false);
          logger.info("Grid correctly initialized from the manual initialization section. ");
        } else{
            changeNodes(x->x.setVisible(true), loadRedLabel);
            changeNodes(x->((Label)x).setText("Inserisci i valori corretti"),loadRedLabel);
            logger.warning("Failed to initialize Grid because of problems still not solved! ");
        }
    }
    catch (NumberFormatException e){
        changeNodes(x->x.setVisible(true),loadRedLabel);
        changeNodes(x->((Label)x).setText("TextField vuoto/i"), loadRedLabel);
        logger.severe("Failed to initialize Grid for not entering values inside fields! ");
    }
    catch (NullPointerException e){
       changeNodes(x->x.setVisible(true),loadRedLabel);
       changeNodes(x->((Label)x).setText("Completa la scelta"), loadRedLabel);
       logger.severe("Failed to initialize Grid because is provided an incomplete configuration! ");
    }
}

private String buildEndPath(ComboBox comboBox){
    return comboBox.getSelectionModel().getSelectedItem().toString().concat(".json");
}

@FXML public void changeToHandCursor(MouseEvent mouseEvent){
    ((Node)mouseEvent.getSource()).getScene().setCursor(Cursor.HAND);
    logger.info("Cursor is a hand");
}

@FXML public void changeToDefaultCursor(MouseEvent mouseEvent){
    ((Node)mouseEvent.getSource()).getScene().setCursor(Cursor.DEFAULT);
    logger.info("Cursor is set to default");
}

@FXML public void overThePane(MouseEvent mouseEvent){
     if(hasNotOneActivatedPane()){
       changeToHandCursor(mouseEvent);
       ((Node)mouseEvent.getSource()).setStyle("-fx-background-color: pink");
       logger.info("Mouse entered a pane");
    }
}

@FXML public void exitThePane(MouseEvent mouseEvent){
    if(hasNotOneActivatedPane()) {
        changeToDefaultCursor(mouseEvent);
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: #f4f4f4");
        ((Node) mouseEvent.getSource()).setStyle("-fx-border-color: black");
        logger.info("Mouse exited a pane.");
    }
}

@FXML public void unlockThisPane(MouseEvent mouseEvent){
   if(hasNotOneActivatedPane()) {
        ((Pane) mouseEvent.getSource()).getChildren().forEach(x -> x.setDisable(false));
        changeToDefaultCursor(mouseEvent);
        backButton.setDisable(false);
        logger.info("Pane is selected and enabled.");
   }
}

@FXML public void goBackToNoPaneChoosed(){
    backButton.setDisable(true);
    resetToggles(dimensionChoosedToggleGroup,positionChoosedToggleGroup,differentKnownConfigurations,ruleChoosedToggleGroup);
    initialize();
    reset();
    logger.info("All panes are disabled.");
}

private void reset() {
    controllerCreated = null;
    firstMaxCoordinate = -1;
    secondMaxCoordinate = -1;
    thirdMaxCoordinate = -1;
    logger.info("System variables are resetted.");
}

    @FXML public void createGridFromFile(MouseEvent mouseEvent) {
        loadFromFileErrorLabel.setText("");
        reset();
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        System.out.println(file.getPath());
        try{
        controllerCreated = IController.createControllerFromFile(file.getPath());
        }catch (IOException e){
            loadFromFileErrorLabel.setText("Fallimento nel caricare da file!");
            loadFromFileErrorLabel.setVisible(true);
            logger.severe("Failed to load grid from file! ");
        }
        nextButton.setDisable(false);
        logger.info("Grid is correctly loaded from file and initialized. ");
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
    logger.info("Dimension buttons are shown.");
}

    private String getStringFromToggle(ToggleGroup toggleGroup) {
    return ((RadioButton)toggleGroup.getSelectedToggle()).getText();
}

   @FXML
   public void loadGrid(MouseEvent mouseEvent) {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("/GameOfLifeSecondScene.fxml"));
       AnchorPane gridViewParent = null;
       try{
       gridViewParent = loader.load();}
       catch (IOException e){
           e.printStackTrace();
           logger.severe("Failed to load next scene!");
       }
       GUIViewSecondSceneController secondController = loader.getController();
       secondController.initializeGRASPController(controllerCreated);
       secondController.initGrid();
       Scene gridViewScene =  new Scene (gridViewParent);
       Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
       window.setScene(gridViewScene);
       window.show();
       logger.info("Grid has been initialized correctly. Grid is shown in next scene.");
    }

    @FXML public void checkInputPosError1(){
    checkInputPosError(firstTextField,firstErrorRedLabel);
    }

    @FXML public void checkInputPosError2(){
    checkInputPosError(secondTextField, secondErrorRedLabel);
    }

    @FXML public void checkInputPosError3(){
    checkInputPosError(thirdTextField, thirdErrorRedLabel);
    }

    private void checkInputPosError(TextField textField, Label label){
        String valueString = textField.getText();
        int value = 0;
        try{
            label.setText("");
            value = Integer.parseInt(valueString);
            if (value <= 0) {

                throw new IllegalArgumentException("Il numero inserito e' <= 0!");
            }
        }catch (NumberFormatException e){
            changeNodes(x->x.setVisible(true), label);
            label.setText("Il valore inserito non e' un numero!");
            logger.severe("Value entered ["+valueString+"] is not an integer");
        }
        catch (IllegalArgumentException e){
            label.setText(e.getMessage());
            logger.severe("Integer entered [" + value + "] is less than or equals 0!");
        }
    }

}
