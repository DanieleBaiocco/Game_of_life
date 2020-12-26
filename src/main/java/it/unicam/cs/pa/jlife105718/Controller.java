package it.unicam.cs.pa.jlife105718;

import java.io.File;
import java.io.FileNotFoundException;

public interface Controller {
    void nextGen();
     void colorateDecolorateACellula(int ... values);
     void loadBoardFromFile();
     ICampo<?> getCampo();
     CurrentRulesEnum getRule();
      void addAEntry( int ... values);
      Cellula getCellulaFromInteger(int ... values);
      static Controller createControllerFromFile(File file) throws FileNotFoundException {
        String fileName = file.getName();
        String[] fileSplitted = fileName.split("\\.");
        String extention = fileSplitted[fileSplitted.length-1];
        return DeserializationFactory.getInstance().getControllerFromFile(extention).deserializeFile(file);
    }

}
