package it.unicam.cs.pa.jlife105718.View.GUIView;
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
    /**
     * Utile per la gestione del logging
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Rappresenta il Radio Button relativo a una griglia in una dimensione
     */
    @FXML private RadioButton oneDRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia di due dimensioni
     */
    @FXML private RadioButton twoDRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia di tre dimensioni
     */
    @FXML private RadioButton threeDRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia che ha sugli assi come coordinate dei numeri interi
     */
    @FXML private RadioButton integerNumbersRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia che ha sugli assi come coordinate dei numeri
     * in virgola mobile
     */
    @FXML private RadioButton doubleNumbersRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia che ha sugli assi come coordinate delle lettere
     */
    @FXML private RadioButton alphabetRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia che utilizza come calcolo della Next Generation
     * la regola di base
     */
    @FXML private RadioButton standardRuleRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia che utilizza come calcolo della Next Generation
     * un primo tipo di regola alternativa
     */
    @FXML private RadioButton alternativeRuleRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia che utilizza come calcolo della Next Generation
     * un secondo tipo di regola alternativa
     */
    @FXML private RadioButton alternativeRule2RadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia con delle configurazioni del genere STILL LIFES
     */
    @FXML private RadioButton firstKnownRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia con delle configurazioni del genere OSCILLATORS
     */
    @FXML private RadioButton secondKnownRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia con delle configurazioni del genere SPACESHIPS
     */
    @FXML private RadioButton thirdKnownRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia con delle configurazioni del genere METHUSELAHS
     */
    @FXML private RadioButton fourthKnownRadioButton;

    /**
     * Rappresenta il Radio Button relativo a una griglia con delle configurazioni del genere INFINITE GROWTH
     */
    @FXML private RadioButton fifthKnownRadioButton;

    /**
     * Viene visualizzata con all'interno un messaggio di errore nel caso in cui fallisce il caricamento della
     * griglia da file
     */
    @FXML private Label loadFromFileErrorLabel;

    /**
     * Usato per caricare la griglia da file
     */
    @FXML private Button loadButton;

    /**
     * Contiene del testo che intima l'utente a inserire il valore della lunghezza della griglia
     */
    @FXML private Label firstLabel;

    /**
     * Contiene del testo che intima l'utente a inserire il valore della larghezza della griglia
     */
    @FXML private Label secondLabel;

    /**
     * Contiene del testo che intima l'utente a inserire il valore dell'altezza della griglia
     */
    @FXML private Label thirdLabel;

    /**
     * Campo in cui inserire il valore della lunghezza della griglia
     */
    @FXML private TextField firstTextField;

    /**
     * Campo in cui inserire il valore della larghezza della griglia
     */
    @FXML private TextField secondTextField;

    /**
     * Campo in cui inserire il valore dell'altezza della griglia
     */
    @FXML private TextField thirdTextField;

    /**
     * Se cliccato carica le specifiche con le quali si desidera costruire la griglia
     */
    @FXML private Button nextButton;

    /**
     * Se cliccato permette di tornare allo stato iniziale della prima scena, nel quale si può scegliere
     * tra uno dei tre pannelli
     */
    @FXML private Button backButton;

    /**
     * Visualizza un messaggio di errore, in caso di un valore non supportato nel primo Text Field indicante
     * la lunghezza della griglia
     */
    @FXML private Label firstErrorRedLabel;

    /**
     * Visualizza un messaggio di errore, in caso di un valore non supportato nel secondo Text Field indicante
     * la larghezza della griglia
     */
    @FXML private Label secondErrorRedLabel;

    /**
     * Visualizza un messaggio di errore, in caso di un valore non supportato nel primo Text Field indicante
     * l'altezza della griglia
     */
    @FXML private Label thirdErrorRedLabel;

    /**
     * Contiene tutte le configurazioni del genere STILL LIFES
     */
    @FXML private ComboBox choose1;

    /**
     * Contiene tutte le configurazioni del genere OSCILLATORS
     */
    @FXML private ComboBox choose2;

    /**
     * Contiene tutte le configurazioni del genere SPACESHIPS
     */
    @FXML private ComboBox choose3;

    /**
     * Contiene tutte le configurazioni del genere METHUSELAHS
     */
    @FXML private ComboBox choose4;

    /**
     * Contiene tutte le configurazioni del genere INFINITE GROWTH
     */
    @FXML private ComboBox choose5;

    /**
     * Rappresenta il contenitore di 5 diversi pane che lo partizionano
     */
    @FXML private BorderPane borderPane;

    /**
     * Partizione sinistra del borderPane
     */
    @FXML private Pane leftPane;

    /**
     * Partizione centrare del borderPane
     */
    @FXML private Pane centralPane;

    /**
     * Partizione destra del borderPane
     */
    @FXML private Pane rightPane;

    /**
     * Visualizza un messaggio di errore in caso di fallimento nel caricamento delle specifiche con cui l'utente
     * vuole costruire la griglia
     */
    @FXML private Label loadRedLabel;

    /**
     * Viene visualizzato per la scelta di un file dal filesystem dell'utente da cui caricare la configurazione
     * della griglia
     */
    private  final FileChooser fileChooser = new FileChooser();

    /**
     * Permette di interagire col desktop dell'utente
     */
    private Desktop desktop ;

    /**
     * Contiene i Radio Buttons riferiti alla dimensione della griglia
     */
    private final ToggleGroup dimensionChoosedToggleGroup = new ToggleGroup();

    /**
     * Contiene i Radio Buttons riferiti alla regola da adottare per calcolare la generazione successiva
     */
    private final ToggleGroup ruleChoosedToggleGroup= new ToggleGroup();

    /**
     * Contiene i Radio Buttons riferiti al tipo di coordinate da voler visualizzare
     */
    private final ToggleGroup positionChoosedToggleGroup= new ToggleGroup();

    /**
     * Contiene i Radio Buttons riferiti a configurazioni conosciute (Still Lifes, Infinite Growth, ecc...)
     */
    private final ToggleGroup differentKnownConfigurations = new ToggleGroup();

    /**
     * Viene creato o dopo aver caricato delle specifiche inserite a mano, o dopo aver scelto un tipo di
     * preconfigurazione tra quelle disponibili o dopo aver caricato una configurazione da file
     */
    private IController<?> controllerCreated;


    /**
     * Rimuove inizialmente tutti gli elementi all'interno del Combo Box, poi aggiunge gli elementi passati
     * come elementi del comboBox. Infine seleziona il primo tra quelli inseriti per renderlo visibile.
     * @param comboBox la casella usata per contenere tutti i tipi di configurazioni conosciute di un tipo
     * @param elements le stringhe corrispondenti a configurazioni di uno stesso tipo
     */
    private void initComboBoxe(ComboBox comboBox, String ... elements){
    comboBox.getItems().removeAll(comboBox.getItems());
    comboBox.getItems().addAll(elements);
    comboBox.getSelectionModel().select(elements[0]);
}

    /**
     * Inizializza un Toggle Group aggiungendo dei Radio Buttons al suo interno
     * @param toggleGroup il contenitore in cui inserire i radio buttons
     * @param radioButtons gli elementi che verranno inseriti nel toggle group
     */
    private void initToggleGroup( ToggleGroup toggleGroup, RadioButton ... radioButtons){
    Arrays.stream(radioButtons).forEach(x->x.setToggleGroup(toggleGroup));
}

    /**
     * Esegue un'azione su ogni nodo passato
     * @param consumer l'azione eseguita per ogni nodo
     * @param nodes i nodi sui quali verrà eseguita un'azione
     */
    private void changeNodes(Consumer<Node> consumer, Node... nodes){
    Arrays.stream(nodes).forEach(consumer);
}

    /**
     * Mi dice quanti panes, tra i figli del borderPane, contano tra i propri figli un numero pari a quello
     * ritornato dal predicato a loro applicato
     * @param predicate filtra i panes figli del borderPane
     * @return una lista di panes che, dopo l'applicazione del predicato, contano lo stesso numero di figli di
     * prima
     */
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

    /**
     * Dice se c'è, tra i pane della fascia centrale (dove sono i 3 pannelli con cui l'utente interagisce)
     * almeno un pane attivo/selezionato
     * @return Se non c'è un pane selezionato ritorna true, altrimenti false
     */
    private boolean hasNotOneActivatedPane(){
    return getFilteredPanes(Node::isDisable).size()!=2;
}

    /**
     * Per ogni Toggle Group passato, leva il pallino di selezione dal toggle che era stato precedentemente
     * selezionato all'interno di quel Toggle Group
     * @param toggleGroups i vari Toggle Groups passati
     */
    private void resetToggles(ToggleGroup ... toggleGroups){
    Arrays.stream(toggleGroups).filter(x->x.getSelectedToggle()!=null).forEach(x->x.getSelectedToggle().setSelected(false));
}

    /**
     * Inizializza la prima scena che si presenta con 3 zone nella fascia centrale, che hanno all'interno tutti
     * gli elementi disabilitati. La prima zona a sinistra è divisa in tre sezioni dall'alto verso il basso in cui
     * poter personalizzare la griglia che si vuole andar a costruire:
     * - Selezione della dimensione
     * - Selezione delle coordinate da visualizzare
     * - Selezione della regola da adottare per calcolare la next gen
     * La seconda zona al centro presenta 5 caselle con apertura a cascata che permettono di scegliere un tipo
     * specifico di configurazione conosciuta. Affianco di ognuna casella è presente un Radio Button che ne conferma
     * la scelta
     * La Terza zona a destra permette di caricare una configurazione da un file preso dal filesystem dell'utente
     */
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

    /**
     * Cliccando su uno tra i Radio Button della zona centrale, viene inizializzato il controller con le
     * informazioni deseriallizate dal file .json corrispondente al contenuto attuale della casella a cascata
     * affianco al Radio Button
     * @param mouseEvent serve a rilevare qual è stato il Radio Button che è stato selezionato (dal quale è
     * partito l'evento)
     */
    @FXML public void createGridFromAPreconfiguredGrid(MouseEvent mouseEvent){
    reset();
    String radioButtonClicked = ((RadioButton)mouseEvent.getSource()).getText();
    String basePath = new File("").getAbsolutePath();
    buildControllerFromRadioButton(radioButtonClicked, basePath);
    nextButton.setDisable(false);
    logger.info("Grid selected from preconfigured grids");
}

    /**
     * Costruisce il pathname per raggiungere il file json e inizializza il controller con le informazioni
     * contenute al suo interno. L'intero pathname sarà dato dal pathname dalla root alla cartella
     * preconfiguredGridsInJson concatenato alla stringa corrispondente al valore nella casella a cascata
     * alla destra del Radio Button. Questo quindi dipenderà sia dal Radio Button cliccato, sia dal valore
     * selezionato nella casella a cascata prima del click.
     * @param radioButtonClicked il nome del Radio Button cliccato
     * @param basePath il pathname dalla root dell'utente fino alla cartella del progetto
     */
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

    /**
     * Mi da' un valore dell'enumerazione dei vari tipi di coordinate prendendo il valore della stringa
     * corrispondente al Radio Button selezionato nel Toggle Group riferito alle coordinate della prima zona
     * del border pane (quella di sinistra)
     * @return uno tra i valori dell'enumerazione dei tipi di coordinate
     */
    private PositionsEnum getPositionFromManualInit(){
    logger.info("Creation from manual initalization of Position done.");
    return Utility.switchOnPositionChoosed(getStringFromToggle(positionChoosedToggleGroup));
}

    /**
     * Mi da' un valore dell'enumerazione dei vari tipi di regole prendendo il valore della stringa
     * corrispondente al Radio Button selezionato nel Toggle Group riferito alle regole della prima zona
     * del border pane (quella di sinistra)
     * @return uno tra i valori dell'enumerazione dei tipi di regole
     */
    private RulesEnum getRuleFromManualInit(){
    logger.info("Creation from manual initalization of Rule done.");
    return Utility.switchOnRuleChoosed(getStringFromToggle(ruleChoosedToggleGroup));

}

    /**
     * Mi da' un'istanza di un tipo di griglia prendendo il valore della stringa
     * corrispondente alla dimensione della griglia dal toggleGroup delle dimensioni
     * della prima zona del border pane (quella di sinistra).
     * @param position indica come voglio visualizzare le coordinate nella griglia
     * @param factoryField viene utilizzata per creare uno dei tre tipi di griglia (1D, 2D o 3D)
     * @return una griglia 1D, 2D o 3D a seconda di ciò che è stato cliccato nella scena
     */
    private <T extends IPosition> IField<T> getFieldFromManualInit(PositionsEnum position, IFactoryField factoryField){
    return Utility.switchOnDimensionChoosed(getStringFromToggle(dimensionChoosedToggleGroup),
            ()->factoryField.createField1D(position,Integer.parseInt(firstTextField.getText())),
            ()->factoryField.createField2D(position,Integer.parseInt(firstTextField.getText()),
                    Integer.parseInt(secondTextField.getText())),
            ()->factoryField.createField3D(position,Integer.parseInt(firstTextField.getText()),
                    Integer.parseInt(secondTextField.getText()),
                    Integer.parseInt(thirdTextField.getText())));
}

    /**
     * In risposta a un successo nell'inserimento dei parametri con i quali si vuole creare una griglia, nella sezione manuale,
     *  rende possibile l'interazione col bottone next, mentre nasconde il bottone di load della griglia in questa sezione
     */
    private void changeLayoutAfterSuccessOnManualInit(){
    nextButton.setDisable(false);
    loadRedLabel.setText("");
    loadButton.setVisible(false);
}

    /**
     * In risposta a un mancato inserimento dei paramtri con i quali si vuole creare una griglia nella sezione manuale,
     * visualizza un messaggio di errore
     */
    private void changeLayoutAfterFailure1OnManualInit(){
    changeNodes(x->x.setVisible(true),loadRedLabel);
    changeNodes(x->((Label)x).setText("TextField vuoto/i"), loadRedLabel);

}

    /**
     * In risposta a un inserimento errato dei paramtri con i quali si vuole creare una griglia nella sezione manuale,
     * visualizza un messaggio di errore
     */
    private void changeLayoutAfterFailure2OnManualInit(){
    changeNodes(x->x.setVisible(true),loadRedLabel);
    changeNodes(x->((Label)x).setText("Completa la scelta"), loadRedLabel);
}

    /**
     * Viene eseguito sul click del mouse sul bottone di load della sezione di inserimento manuale dei parametri con cui creare la griglia.
     * Il bottone next, che è quello che permette di passare alla scena successiva, viene reso interagibile solo se tutte e tre le ErrorRedLabel
     * sono contemporaneamente vuote (quindi solo se non ci sono errori nei parametri inseriti). Questo metodo permette di creare il controller
     * che verrà usato poi nella seconda scena, da inserimento manuale dei parametri.
     */
    @FXML void createGridFromInitialization(){
    IFactoryField factoryField =new MyFactoryField();
    PositionsEnum functionSelected;
    try {
        if(firstErrorRedLabel.getText().equals("") && secondErrorRedLabel.getText().equals("") && thirdErrorRedLabel.getText().equals("")){
          functionSelected = getPositionFromManualInit();
          controllerCreated = new MyGameOfLifeController<>(getFieldFromManualInit(functionSelected, factoryField),getRuleFromManualInit(), null);
          changeLayoutAfterSuccessOnManualInit();
          logger.info("Creation from manual initalization of Controller done.");
        } else{
            changeNodes(x->x.setVisible(true), loadRedLabel);
            changeNodes(x->((Label)x).setText("Inserisci i valori corretti"),loadRedLabel);
            logger.warning("Failed to initialize Grid because of problems still not solved! ");
        }
    }
    catch (NumberFormatException e){
        changeLayoutAfterFailure1OnManualInit();
        logger.severe("Failed to initialize Grid for not entering values inside fields! ");
    }
    catch (NullPointerException e){
      changeLayoutAfterFailure2OnManualInit();
      logger.severe("Failed to initialize Grid because is provided an incomplete configuration! ");
    }
}

    /**
     * Crea la stringa finale del pathname che verrà usato per caricare il file .json corrispondente alla configurazione conosciuta scelta,
     * che verrà deserializzato per andare a creare il controller. La stringa viene costruita prendendo la stringa contenuta nella casella a cascata
     * nel momento in cui si clicca sul Radio Button affianco a questa, e concatenandola con l'estensione ".json".
     * @param comboBox la casella a cascata affianco al Radio Button selezionato
     * @return la stringa rappresentante la parte finale del pathname per il caricamento da file di preconfigurazioni della griglia
     */
    private String buildEndPath(ComboBox comboBox){
    return comboBox.getSelectionModel().getSelectedItem().toString().concat(".json");
}

    /**
     * Fa cambiare il cursore del mouse da Default a una mano quando si passa sopra a uno dei pane (di sinistra, centrale o di destra,
     * quando questi sono ancora tutti bloccati), sopra a un bottone al loro interno, quando sono sbloccati, o sopra a un bottone nel
     * pane inferiore.
     * @param mouseEvent mi permette di capire qual è il node da cui è scaturito l'evento
     */
    @FXML public void changeToHandCursor(MouseEvent mouseEvent){
    ((Node)mouseEvent.getSource()).getScene().setCursor(Cursor.HAND);
    logger.info("Cursor is a hand");
}

    /**
     * Fa cambiare il cursore del mouse da una mano a Default quando si esce da uno dei pane (di sinistra, centrale o di destra,
     * quando questi sono ancora tutti bloccati) , quando si esce da un bottone al loro interno, quando sono sbloccati, o quando si esce
     * da un bottone nel pane inferiore.
     * @param mouseEvent mi permette di capire qual è il node da cui è scaturito l'evento
     */
    @FXML public void changeToDefaultCursor(MouseEvent mouseEvent){
    ((Node)mouseEvent.getSource()).getScene().setCursor(Cursor.DEFAULT);
    logger.info("Cursor is set to default");
}

    /**
     * Quando si passa col mouse sopra un pane e tutti i pane sono bloccati, quel pane si colora di rosa e il cursore del mouse cambia forma
     * in mano
     * @param mouseEvent mi permette di capire qual è il node da cui è scaturito l'evento
     */
    @FXML public void overThePane(MouseEvent mouseEvent){
     if(hasNotOneActivatedPane()){
       changeToHandCursor(mouseEvent);
       ((Node)mouseEvent.getSource()).setStyle("-fx-background-color: pink");
       logger.info("Mouse entered a pane");
    }
}

    /**
     * Quando si esce col mouse da un pane e tutti i pane sono bloccati, quel pane cambia da rosa a grigio e il cursore del mouse cambia forma
     * in Default
     * @param mouseEvent mi permette di capire qual è il node da cui è scaturito l'evento
     */
    @FXML public void exitThePane(MouseEvent mouseEvent){
    if(hasNotOneActivatedPane()) {
        changeToDefaultCursor(mouseEvent);
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: #f4f4f4");
        ((Node) mouseEvent.getSource()).setStyle("-fx-border-color: black");
        logger.info("Mouse exited a pane.");
    }
}

    /**
     * Viene eseguito quando si clicca su un pane quando tutti i pane sono bloccati. Permette di sbloccare tutti gli elementi all'interno
     * del pane selezionato e di sbloccare il bottone di back nel caso si volesse riscegliere un altro modo per inizializzare la griglia
     * @param mouseEvent
     */
    @FXML public void unlockThisPane(MouseEvent mouseEvent){
   if(hasNotOneActivatedPane()) {
        ((Pane) mouseEvent.getSource()).getChildren().forEach(x -> x.setDisable(false));
        changeToDefaultCursor(mouseEvent);
        backButton.setDisable(false);
        logger.info("Pane is selected and enabled.");
   }
}

    /**
     * Viene eseguito quando si clicca sopra il bottone di back. Ripristina la scena al suo layout iniziale, settando la variabile controller a null.
     * Permette quindi di resettare tutto e ricominciare a configurare la griglia in un altro modo, o con lo stesso
     * ma con diverse informazioni
     */
    @FXML public void goBackToNoPaneChoosed(){
    backButton.setDisable(true);
    resetToggles(dimensionChoosedToggleGroup,positionChoosedToggleGroup,differentKnownConfigurations,ruleChoosedToggleGroup);
    initialize();
    reset();
    logger.info("All panes are disabled.");
}

/**
 * serve per settare il controller a null. Viene eseguito quando si vuole ripristinare la scena allo stato iniziale
 */
private void reset() {
    controllerCreated = null;
    logger.info("System variables are resetted.");
}

    /**
     * Viene eseguito quando si clicca sul bottone "Carica file" nella terza sezione (quella di destra) sul caricamento della griglia
     * da file. Utilizza il mouseEvent per ricavare lo stage nel quale si è. Questo serve per visualizzare, tramite un
     * File Chooser, il filesystem dell'utente secondo l'interfaccia grafica dell'SO in cui opera. Navigandolo e selezionando un file
     * (con estensione .json, .txt, ecc...), se tutto va a buon fine, verrà creato un controller.
     * @param mouseEvent
     */
    @FXML public void createGridFromFile(MouseEvent mouseEvent) {
        loadFromFileErrorLabel.setText("");
        reset();
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
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

    /**
     * Quando viene cliccato su un Radio Button riferito alle dimensioni della griglia, nella sezione sinistra nella quale si crea
     * la griglia manualmente, in modo responsive vengono visualizzate delle Label e dei TextField per l'inserimento della coordinata
     * massima per ogni asse
     */
    @FXML public void showDimensionLabelsAndTexts() {
    Utility.switchOnDimensionChoosed(getStringFromToggle(dimensionChoosedToggleGroup),
            ()->{ changeNodes(x->x.setVisible(true),firstLabel,firstTextField);
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

    /**
     * Serve per prendere il valore in Stringa del Toggle selezionato all'interno di un Toggle Group
     * @param toggleGroup serve per prendere il valore del Toggle selezionato all'interno di questo
     */
    private String getStringFromToggle(ToggleGroup toggleGroup) {
    return ((RadioButton)toggleGroup.getSelectedToggle()).getText();
}

    /**
     * Carica la nuova scena, passando il controllerCreated al GUIViewSecondSceneController. Inoltre chiama il metodo
     * initGrid() del GUIViewSecondSceneController che crea e visualizza una griglia nella nuova scena secondo le informazioni
     * contenute nel controllerCreated
     * @param mouseEvent serve per ricavare lo stage da cui parte questo evento per poi poter settare in questo stage
     * la nuova scena
     */
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

    /**
     * Permette, in caso d'errore nell'inserimentento del valore della massima coordinata per la prima dimensione, di visualizzare
     * un messaggio di errore sopra il TextField della massima coordinata.
     */
    @FXML public void checkInputPosError1(){
    checkInputPosError(firstTextField,firstErrorRedLabel);
    }

    /**
     * Permette, in caso d'errore nell'inserimentento del valore della massima coordinata per la seconda dimensione, di visualizzare
     * un messaggio di errore sopra il TextField della massima coordinata.
     */
    @FXML public void checkInputPosError2(){
    checkInputPosError(secondTextField, secondErrorRedLabel);
    }

    /**
     * Permette, in caso d'errore nell'inserimentento del valore della massima coordinata per la terza dimensione, di visualizzare
     * un messaggio di errore sopra il TextField della massima coordinata.
     */
    @FXML public void checkInputPosError3(){
    checkInputPosError(thirdTextField, thirdErrorRedLabel);
    }

    /**
     * Metodo di utilità che serve per vedere se un dato valore passato, questo è valido o meno. Nel caso in cui non lo è
     * la label sopra il textField da cui viene preso il valore viene mostrata con un messaggio di errore
     */
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
