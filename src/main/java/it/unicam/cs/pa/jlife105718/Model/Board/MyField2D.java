package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *  Estende MyField, di conseguenza utilizza cellule di tipo MyCell, inoltre implementa IField1D, quindi è
 *  * responsabile della gestione di una griglia 2D
 */
public class MyField2D<T extends IPosition> extends MyField<T> implements IField2D<T>{

    private static final Logger logger = Logger.getGlobal();

    public MyField2D(PositionsEnum transition, int value1, int value2) {
        super(transition,2,  value1,value2);
        logger.finest("MyField2D created.");
    }

    public MyField2D(IField<T> field) {
        super(field);
        logger.finest("MyField2D copy created.");
    }

    /**
     * Viene ritornata una nuova istanza passando come parametro del costruttore questa stessa istanza
     */
    @Override
    public IField<T> deepCopyClone() {
        return new MyField2D<>(this);
    }

    /**
     * Data una cellula vengon calcolate le cellule intorno a questa scorrendo gli elementi all'interno della
     * mappa posizione-cellula e andando a vedere, per ogni elemento ,se rispetta le regole definite in isInTheIntorno o meno.
     * In caso di risposta affermativa, questo elemento viene messo all'interno del set che, al termine dell'iterazione,
     * viene ritornato
     */
    public Set<ICell> getIntorno(ICell cellula) {
        T pos =getPosizioneFromCellula(cellula);
        int[] result = pos.returnToIntegerCoordinates();
        int firstCoordinate = result[0];
        int secondCoordinate = result[1];
        return getMappaPosizioneCellula().keySet()
                .stream()
                .filter(p->!p.equals(pos))
                .filter(isInTheIntorno(firstCoordinate,secondCoordinate))
                .map(p->getMappaPosizioneCellula().get(p))
                .collect(Collectors.toSet());
    }

    /**
     * Metodo chiamato per ogni elemento all'interno della mappa posizione-cellula. Se, fissata una
     * delle due coordinate della possibile cellula nell'intorno della cellula obiettivo, l'altra oscilla tra
     * la coordinata della cellula obiettivo -1 e la coordinata della cellula obiettivo +1 allora la
     * possibile cellula nell'intorno è effettivamente nell'intorno
     */
    private Predicate<? super T> isInTheIntorno( int first, int second){
    return po->{
    int[] result = po.returnToIntegerCoordinates();
    int firstofx = result[0];
    int secondofx = result[1];
    boolean secondCondition = (secondofx==second-1 || secondofx==second || secondofx== second+1);
    return (firstofx==first-1 && secondCondition)||
            (firstofx==first && secondCondition) ||
            (firstofx==first+1 && secondCondition);
    };
   }


}
