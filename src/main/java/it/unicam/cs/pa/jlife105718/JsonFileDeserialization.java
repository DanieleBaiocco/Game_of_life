package it.unicam.cs.pa.jlife105718;


import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonFileDeserialization implements FileDeserialization {

    @Override
    public Controller deserializeFile(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        Gson gson = new Gson();
        Controller controller = gson.fromJson(fileReader,Controller.class);
        return null;
    }

}
