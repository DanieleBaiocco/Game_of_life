package it.unicam.cs.pa.jlife105718;


import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

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
    public Controller deserializeFile(String pathName){
        JsonElement tree = JsonParser.parseReader(getReaderFromPathName(pathName));
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<Controller> deserializer = (json, typeOfT, context) -> {
            JsonObject treeAsJsonObj = json.getAsJsonObject();
            String typeOfPosition = treeAsJsonObj.get("posizione").getAsString();

            Function<List<Integer>, ? extends IPosizione> transition = Utility.switchOnPositionChoosed(typeOfPosition);
            JsonArray values = treeAsJsonObj.get("limite").getAsJsonArray();
            ICampo<?> fieldCreated = Utility.switchOnDimensionChoosed(String.valueOf(values.size()),
                    ()->new Campo1D<>(transition,values.get(0).getAsInt()),
                    ()->new Campo2D<>(transition,values.get(0).getAsInt(),values.get(1).getAsInt()),
                    ()->new Campo3D<>(transition,values.get(0).getAsInt(),
                            values.get(1).getAsInt(),
                            values.get(2).getAsInt()));
            String rule = treeAsJsonObj.get("regola").getAsString();
            CurrentRulesEnum currentRulesEnum = Utility.switchOnRuleChoosed(rule);
            JsonArray cellsInJson = treeAsJsonObj.get("colorare").getAsJsonArray();
            int[] cells = getListOfCellsToSetAlive(cellsInJson);
            //qua fai la cosa col System.getProperties
            return GameOfLifeController.getInstance(fieldCreated,currentRulesEnum, cells);
        };
        gsonBuilder.registerTypeAdapter(Controller.class, deserializer);
        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(tree, Controller.class);
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
