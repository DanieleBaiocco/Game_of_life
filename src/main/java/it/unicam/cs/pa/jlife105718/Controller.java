package it.unicam.cs.pa.jlife105718;

import java.io.FileNotFoundException;

public interface Controller<T extends IPosizione> {
    void nextGen();
    void colorateDecolorateACellula(int ... values);
    ICampo<T> getCampo();
    CurrentRulesEnum getRule();
    void addAEntry( int ... values);
    Cellula getCellulaFromInteger(int ... values);
    static <T extends IPosizione> Controller<?> createControllerFromFile(String pathName) throws FileNotFoundException {
        String[] fileSplitted =pathName.split("\\.");String extention = fileSplitted[fileSplitted.length-1];
        return DeserializationFactory.getInstance().getControllerFromFile(extention).deserializeFile(pathName);
    }
    int[] getCellsToSetAlive();
    String getRepresentation(int i, int ... values);

}
