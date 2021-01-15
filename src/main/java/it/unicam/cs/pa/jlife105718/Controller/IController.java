package it.unicam.cs.pa.jlife105718.Controller;

import it.unicam.cs.pa.jlife105718.Model.Board.MyFactoryField;
import it.unicam.cs.pa.jlife105718.Model.Board.IFactoryField;
import it.unicam.cs.pa.jlife105718.Model.Board.IField;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Deserializator.DeserializationFactory;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;

import java.io.FileNotFoundException;

public interface IController<T extends IPosition> {
    void nextGen();
    void colorateDecolorateACellula(int ... values);
    IField<T> getCampo();
    RulesEnum getRule();
    void addAEntry( int ... values);
    ICell getCellulaFromInteger(int ... values);
    static <T extends IPosition> IController<?> createControllerFromFile(String pathName) throws FileNotFoundException {
        IFactoryField factoryField = new MyFactoryField();
        String[] fileSplitted =pathName.split("\\.");String extention = fileSplitted[fileSplitted.length-1];
        return DeserializationFactory.getInstance().getControllerFromFile(extention).deserializeFile(pathName, factoryField);
    }
    int[] getCellsToSetAlive();
    String getRepresentation(int i, int ... values);

}
