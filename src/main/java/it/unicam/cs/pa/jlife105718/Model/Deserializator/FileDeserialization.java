package it.unicam.cs.pa.jlife105718.Model.Deserializator;

import it.unicam.cs.pa.jlife105718.Controller.IController;
import it.unicam.cs.pa.jlife105718.Model.Board.IFactoryField;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;

import java.io.FileNotFoundException;

public interface FileDeserialization {
    <T extends IPosition> IController<?> deserializeFile(String pathName ,IFactoryField factoryField) throws FileNotFoundException;

}
