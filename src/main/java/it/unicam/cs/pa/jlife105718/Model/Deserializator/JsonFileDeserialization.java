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

public class JsonFileDeserialization implements FileDeserialization {

    private static final Logger logger = Logger.getGlobal();

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

    private RulesEnum getRuleFromDeserialization(JsonObject treeAsJsonObj){
        String rule = treeAsJsonObj.get("regola").getAsString();
        logger.info("Json deserialization of Rule done.");
         return Utility.switchOnRuleChoosed(rule);

    }

    private PositionsEnum getPositionFromDeserialization(JsonObject treeAsJsonObj){
        String typeOfPosition = treeAsJsonObj.get("posizione").getAsString();
        logger.info("Json deserialization of Position done.");
        return Utility.switchOnPositionChoosed(typeOfPosition);
    }

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

    private int [] getCellsFromDeserialization(JsonObject treeAsJsonObj){
        JsonArray cellsInJson = treeAsJsonObj.get("colorare").getAsJsonArray();
        logger.info("Json deserialization of Cells to set alive done.");
        return getListOfCellsToSetAlive(cellsInJson);
    }

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

    private <T extends IPosition> Gson createGson(JsonDeserializer<IController<T>> deserializer){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(IController.class, deserializer);
        return gsonBuilder.create();
    }

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
