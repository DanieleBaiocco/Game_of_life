package it.unicam.cs.pa.jlife105718.Model.Deserializator;

import it.unicam.cs.pa.jlife105718.Controller.IController;
import it.unicam.cs.pa.jlife105718.Model.Board.IFactoryField;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;

import java.io.FileNotFoundException;

/**
 * Deserializza il contenuto di un file con una particolare estensione in un IController
 */
public interface FileDeserialization {
    /**
     * E' responsabile della creazione di un controller da un pathname che indica il percorso da seguire nel filesystem per
     * raggiungere il file da deserializzare. Viene anche usata una classe che implementa l'abstract factory IFactoryField
     * in quanto mi fornisce i metodi per la creazione della griglia con cui creo poi il controller.
     * @param pathName il percorso dalla root dell'utente al file da deserializzare
     * @param factoryField mi fornisce i metodi per formare o una griglia 1D o una 2D o una 3D
     * @throws FileNotFoundException nel caso in cui il pathname non corrisponda a nessun file
     */
    <T extends IPosition> IController<?> deserializeFile(String pathName ,IFactoryField factoryField) throws FileNotFoundException;

}
