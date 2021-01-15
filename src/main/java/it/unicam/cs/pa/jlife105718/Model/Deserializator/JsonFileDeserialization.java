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

public class JsonFileDeserialization implements FileDeserialization {

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

    @Override
    public <T extends IPosition> IController<?> deserializeFile(String pathName, IFactoryField factoryField){
        JsonElement tree = JsonParser.parseReader(getReaderFromPathName(pathName));
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<IController<?>> deserializer = (json, typeOfT, context) -> {
            JsonObject treeAsJsonObj = json.getAsJsonObject();
            String typeOfPosition = treeAsJsonObj.get("posizione").getAsString();
            PositionsEnum transition = Utility.switchOnPositionChoosed(typeOfPosition);
            JsonArray values = treeAsJsonObj.get("limite").getAsJsonArray();
            IField<T> fieldCreated = Utility.switchOnDimensionChoosed(String.valueOf(values.size()),
                    ()->factoryField.createField1D(transition,values.get(0).getAsInt()),
                    ()->factoryField.createField2D(transition,values.get(0).getAsInt(),values.get(1).getAsInt()),
                    ()->factoryField.createField3D(transition,values.get(0).getAsInt(),
                            values.get(1).getAsInt(),
                            values.get(2).getAsInt()));
            String rule = treeAsJsonObj.get("regola").getAsString();
            RulesEnum currentRulesEnum = Utility.switchOnRuleChoosed(rule);
            JsonArray cellsInJson = treeAsJsonObj.get("colorare").getAsJsonArray();
            int[] cells = getListOfCellsToSetAlive(cellsInJson);

            return new MyGameOfLifeController<>(fieldCreated,currentRulesEnum, cells);
        };
        gsonBuilder.registerTypeAdapter(IController.class, deserializer);
        Gson customGson = gsonBuilder.create();
        Type typeOfController = new TypeToken<IController<?>>() {}.getType();
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
