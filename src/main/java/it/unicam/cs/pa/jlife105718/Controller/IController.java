package it.unicam.cs.pa.jlife105718.Controller;

import it.unicam.cs.pa.jlife105718.Model.Board.MyFactoryField;
import it.unicam.cs.pa.jlife105718.Model.Board.IFactoryField;
import it.unicam.cs.pa.jlife105718.Model.Board.IField;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Deserializator.DeserializationFactory;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.PropertyListener;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;

import java.io.FileNotFoundException;

/**
 * Responsabile di seguire lo sviluppo della griglia in tutto il suo percorso dall'inizio alla fine.
 *  Una classe che implementa questa interfaccia definisce infatti come calcolare la Next Generation
 *  e come cambiare lo stato di una cellula. E' il punto di entrata più esterno, nonchè l'unico,
 *  allo strato del dominio. Viene fornita un'implementazione di come creare un'istanza di un IController
 *  da file demandando la parte della deserializzazione e della creazione rispettivamente al FileDeserialization
 *  e all' IFactoryField
 */
public interface IController<T extends IPosition> extends PropertyListener {
    /**
     * Calcola la prossima generazione
     */
    void nextGen();

    /**
     * Cambia lo stato di una cellula passando i valori che rappresentano la posizione in array di interi
     */
    void colorateDecolorateACellula(int ... values);

    /**
     * Ritorna il campo legato a questo controller
     */
    IField<T> getCampo();

    /**
     * Ritorna la regola legata a questo controller
     */
    RulesEnum getRule();

    /**
     * Permette di aggiungere una nuova cellula all'interno della mappa del campo posizione-cellula
     */
    void addAEntry( int ... values);

    /**
     * Ritorna una cellula dalla rappresentazione della sua posizione in array di interi
     */
    ICell getCellulaFromInteger(int ... values);

    /**
     * Ritorna un'istanza di IController. Chiama la DeserializationFactory per capire quale tipo di deserializzatore
     * costruire, in base all'estensione del file da cui si vuole creare il controller. Una volta ottenuto il riferimento
     * a un'istanza di FileDeserializator, chiama il metodo deserializeFile, passando la factory concreta MyFactoryField.
     * Nel caso in cui si volesse fornire un'altra implementazione della del set di griglie e della loro relativa creazione,
     * una delle pochissime modifiche che c'è da apportare al codice è creare un'istanza della factory che crea questo nuovo
     * set, a posto di istanziare una MyFactoryField.
     */
    static <T extends IPosition> IController<?> createControllerFromFile(String pathName) throws FileNotFoundException {
        IFactoryField factoryField = new MyFactoryField();
        String[] fileSplitted =pathName.split("\\.");String extention = fileSplitted[fileSplitted.length-1];
        return DeserializationFactory.getInstance().getControllerFromFile(extention).deserializeFile(pathName, factoryField);
    }

    /**
     * Ritorna le celle il cui stato deve esser messo vivo in fase di set up
     */
    int[] getCellsToSetAlive();

    /**
     * Ritorna la rappresentazione in stringa di una coordinata della posizione di una cellula
     */
    String getRepresentation(int i, int ... values);

}
