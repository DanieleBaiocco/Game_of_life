package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Estende MyField, di conseguenza utilizza cellule di tipo MyCell, inoltre implementa IField1D, quindi è
 * responsabile della gestione di una griglia 1D
 */
public class MyField1D<T extends IPosition> extends MyField<T> implements IField1D<T>{
    private static final Logger logger = Logger.getGlobal();

    public MyField1D(PositionsEnum currentTransitionEnum , int value1) {
        super(currentTransitionEnum,1, value1);
        logger.finest("MyField1D created.");
    }

    public MyField1D(IField<T> field) {
        super(field);
        logger.finest("MyField1D copy created.");
    }

    /**
     * Implementazione omessa. La classe è stata messa solo per rendere più completa la gerarchia delle classi
     */
    @Override
    public Set<ICell> getIntorno(ICell cellula) {
        //implementazione omessa
        return null;
    }

    /**
     * Viene ritornata una nuova istanza passando come parametro del costruttore questa stessa istanza
     */
    @Override
    public IField<T> deepCopyClone() {
        return new MyField1D<>(this);
    }


}
