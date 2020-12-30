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

    @Override
    public Controller deserializeFile(String pathName){
        Path relative = Paths.get(pathName);

        Reader reader= null;
        try{
            reader= Files.newBufferedReader(relative, StandardCharsets.UTF_8);
        }catch (IOException e){
            System.out.println("Non ciè file");
        }
        JsonElement tree = JsonParser.parseReader(reader);
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Controller> deserializer = (json, typeOfT, context) -> {
            JsonObject treeasObj = json.getAsJsonObject();
            JsonArray values = treeasObj.get("limite").getAsJsonArray();
            String posizione = treeasObj.get("posizione").getAsString();
            Campo<?> campo = null;
            Function<List<Integer>, ? extends IPosizione> func = null;
            CurrentRulesEnum currentRulesEnum = null;
            switch (posizione){
                case "alfabetica":
                    func = TransitionFactory.getInstance().getTransitionToChar();
                    break;
                case "numerica":
                    func = TransitionFactory.getInstance().getTransitionToInteger();
                    break;
                case "virgolamobile":
                    func = TransitionFactory.getInstance().getTransitionToDouble();
                    break;
            }
            switch (values.size()){
                case 1:{
                    campo = new Campo1D<>(func,values.get(0).getAsInt());
                    break;}
                case 2:{
                    campo = new Campo2D<>(func,values.get(0).getAsInt(),
                            values.get(1).getAsInt());
                    break;}
                case 3:{
                    campo = new Campo3D<>(func,values.get(0).getAsInt(),
                            values.get(1).getAsInt(),
                            values.get(2).getAsInt());
                    break;
                }
            }
            currentRulesEnum= CurrentRulesEnum.valueOf(treeasObj.get("regola").getAsString());

            return GameOfLifeController.getInstance(campo,currentRulesEnum);
        };
        gsonBuilder.registerTypeAdapter(Controller.class, deserializer);
        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(tree, Controller.class);
    }


    @Override
        public int[] listOfCellsToColorate(String pathName) {
         Path relative = Paths.get(pathName);

        Reader reader= null;
        try{
            reader= Files.newBufferedReader(relative, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        JsonElement tree = JsonParser.parseReader(reader);
       JsonObject treeAsObj = tree.getAsJsonObject();
       JsonArray cellsToColorate = treeAsObj.get("colorare").getAsJsonArray();
       int[] cells = new int[cellsToColorate.size()];
        int i=0;
       for (JsonElement element : cellsToColorate){
           cells[i] = element.getAsInt();
           i++;
       }
       return cells;
    }

}
