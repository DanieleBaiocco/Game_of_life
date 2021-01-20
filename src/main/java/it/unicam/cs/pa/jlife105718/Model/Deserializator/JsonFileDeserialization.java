package it.unicam.cs.pa.jlife105718.Model.Deserializator;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.pa.jlife105718.Controller.IController;
import it.unicam.cs.pa.jlife105718.Controller.MyGameOfLifeController;
import it.unicam.cs.pa.jlife105718.Model.Board.*;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;
import it.unicam.cs.pa.jlife105718.Model.Utility;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Deserializza il contenuto di un file scritto con estensione json in un IController. Il contenuto del .json deve seguire un protocollo:
 * - con "regola" indico la regola che si vuole utilizzare
 * - con "posizione" indico il tipo di coordinate che si vuole utilizzare
 * - con "limite" specifico la dimensione della griglia (se l'array contiene due numeri allora la griglia sarà 2D) e
 * il valore della coordinata massima per ogni asse della griglia
 * - con "colorare" indico le coordinate delle celle che hanno lo stato a VIVO prima dell'avvio della simulazione
 */
public class JsonFileDeserialization implements FileDeserialization {

    private static final Logger logger = Logger.getGlobal();

    /**
     * Crea un reader
     */
    private Reader getReaderFromPathName(String pathName){
        Reader readerToReturn = null;
        Path relative = Paths.get(pathName);
        try{
            readerToReturn = Files.newBufferedReader(relative, StandardCharsets.UTF_8);
        }catch(IOException e){
            e.printStackTrace();
        }
        return readerToReturn;
    }

    /**
     * Prende il valore della proprietà "regola" dal JsonObject e, a seconda della stringa all'interno
     * della proprietà, viene restituito un valore di RulesEnum diverso
     */
    private RulesEnum getRuleFromDeserialization(JsonObject treeAsJsonObj){
        String rule = treeAsJsonObj.get("regola").getAsString();
        logger.info("Json deserialization of Rule done.");
         return Utility.switchOnRuleChoosed(rule);

    }

    /**
     * Prende il valore della proprietà "posizione" dal JsonObject e, a seconda della stringa all'interno
     * della proprietà, viene restituito un valore di PositionsEnum diverso
     */
    private PositionsEnum getPositionFromDeserialization(JsonObject treeAsJsonObj){
        String typeOfPosition = treeAsJsonObj.get("posizione").getAsString();
        logger.info("Json deserialization of Position done.");
        return Utility.switchOnPositionChoosed(typeOfPosition);
    }

    /**
     * Prende il valore della proprietà "limite" dal JsonObject e, a seconda della stringa all'interno
     * della proprietà, viene restituito un IField, costruito grazie all'ausilio della factory, che mi fornisce
     * i metodi per costruire o un campo 1D, o uno 2D o uno 3D, e un valore di PositionsEnum passato
     */
    private <T extends IPosition> IField<T> getFieldFromDeserialization(JsonObject treeAsJsonObj, PositionsEnum pos, IFactoryField factoryField){
        JsonArray values = treeAsJsonObj.get("limite").getAsJsonArray();
        logger.info("Json deserialization of Field done.");
        return Utility.switchOnDimensionChoosed(String.valueOf(values.size()),
                ()->factoryField.createField1D(pos,values.get(0).getAsInt()),
                ()->factoryField.createField2D(pos,values.get(0).getAsInt(),values.get(1).getAsInt()),
                ()->factoryField.createField3D(pos,values.get(0).getAsInt(),
                        values.get(1).getAsInt(),
                        values.get(2).getAsInt()));

    }

    /**
     * Prende il valore della proprietà "colorare" dal JsonObject e lo mette all'interno di un array di interi
     * che ritorna
     */
    private int [] getCellsFromDeserialization(JsonObject treeAsJsonObj){
        JsonArray cellsInJson = treeAsJsonObj.get("colorare").getAsJsonArray();
        logger.info("Json deserialization of Cells to set alive done.");
        return getListOfCellsToSetAlive(cellsInJson);
    }

    /**
     * Crea un deserializzatore personalizzato fatto su misura per deserializzare il contenuto di un file json,
     * che rispetta un certo formato, in un IController. Un esempio di json nel formato corrente è il seguente:
     * {
       "posizione": "Numerico",
       "regola" : "Standard",
       "colorare": [2,1,3,1,1,2,4,2,2,3,3,3],
       "limite": [5,6]
     * }
     * Viene ritornato un MyGameOfLifeController. Se si vuol ritornare un altro tipo di controller basta definirne un altro
     * e creare un'istanza della nuova classe
     */
    private <T extends IPosition> JsonDeserializer<IController<T>> createDeserializer(IFactoryField factoryField){
       return  (json, typeOfT, context) -> {
            JsonObject treeAsJsonObj = json.getAsJsonObject();
            PositionsEnum transition = getPositionFromDeserialization(treeAsJsonObj);
            IField<T> fieldCreated = getFieldFromDeserialization(treeAsJsonObj, transition, factoryField);
            RulesEnum rule = getRuleFromDeserialization(treeAsJsonObj);
            int[] cells = getCellsFromDeserialization(treeAsJsonObj);
            return new MyGameOfLifeController<>(fieldCreated,rule, cells);
        };
    }

    /**
     * Viene creato un GsonBuilder. Il GsonBuilder è stato implementato da chi ha scritto la libreria seguendo
     * il pattern Builder. A questo GsonBuilder lego il deserializzatore personalizzato passato come argomento
     */
    private <T extends IPosition> Gson createGson(JsonDeserializer<IController<T>> deserializer){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(IController.class, deserializer);
        return gsonBuilder.create();
    }

    /**
     * Viene creato il deserializzatore personalizzato tramite il metodo createDeserializer. Il contenuto del file,
     * che vien reso una stringa JSON dal Reader, viene parsato in un elemento  JSON strutturato a albero dal
     * metodo parseReader. Viene creato il Gson che andrà a far la deserializzazione usando il deserializzatore
     * personalizzato da me definito. Viene creato un oggetto Type per mantenere il generico. Si usa il metodo
     * fromJson per convertire l'intero albero in un IController
     * @param pathName il percorso dalla root dell'utente al file da deserializzare
     * @param factoryField mi fornisce i metodi per formare o una griglia 1D o una 2D o una 3D
     */
    @Override
    public <T extends IPosition> IController<?> deserializeFile(String pathName, IFactoryField factoryField){
        JsonDeserializer<IController<T>> deserializer = createDeserializer(factoryField);
        JsonElement tree = JsonParser.parseReader(getReaderFromPathName(pathName));
        Gson customGson = createGson(deserializer);
        Type typeOfController = new TypeToken<IController<T>>() {}.getType();
        logger.info("Json Deserialization done.");
        return customGson.fromJson(tree,typeOfController);
    }


    //mettere un controllo: vedere se il numero dell'array è giusto per la dimensione della griglia
    //es: coppie: 2,3 4,5 7,8 4 ? (senza nient altro dopo il quattro BEEEP errore)

    /**
     * Prende ogni elemento all'interno del JsonArray, lo converte in intero e lo aggiunge a un array di interi.
     * Viene poi ritornato l'array di interi
     */
    private int[] getListOfCellsToSetAlive(JsonArray cells){
        int[] cellsToReturn = new int[cells.size()];
        int i =0;
        for (JsonElement element : cells){
            cellsToReturn[i] = element.getAsInt();
            i++;
        }
        return cellsToReturn;
    }
}
