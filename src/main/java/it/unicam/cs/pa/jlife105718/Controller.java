package it.unicam.cs.pa.jlife105718;

import java.io.FileNotFoundException;

public interface Controller {
    void nextGen();
     void colorateDecolorateACellula(int ... values);
     void loadBoardFromFile();
     ICampo<?> getCampo();
     CurrentRulesEnum getRule();
      void addAEntry( int ... values);
      Cellula getCellulaFromInteger(int ... values);
      static Controller createControllerFromFile(String pathName) throws FileNotFoundException {
        String[] fileSplitted =pathName.split("\\.");
        String extention = fileSplitted[fileSplitted.length-1];
        return DeserializationFactory.getInstance().getControllerFromFile(extention).deserializeFile(pathName);
    }
    static int[] getListOfCellsToColorate(String pathName){
        String[] fileSplitted = pathName.split("\\.");
        String extention = fileSplitted[fileSplitted.length-1];
        return DeserializationFactory.getInstance().getControllerFromFile(extention).listOfCellsToColorate(pathName);
    }
}
