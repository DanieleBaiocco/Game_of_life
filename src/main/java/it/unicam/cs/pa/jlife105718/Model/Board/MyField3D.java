package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.util.Set;
import java.util.logging.Logger;

/**
 *  Estende MyField, di conseguenza utilizza cellule di tipo MyCell, inoltre implementa IField1D, quindi è
 *  responsabile della gestione di una griglia 3D
 */
public class MyField3D<T extends IPosition> extends MyField<T> implements IField3D<T>{
    private static final Logger logger = Logger.getGlobal();

    public MyField3D(PositionsEnum transitionEnum, int value1, int value2, int value3) {
        super(transitionEnum,3,  value1,value2,value3);
        logger.finest("MyField3D created.");
    }

    public MyField3D(IField<T> field) {
        super(field);
        logger.finest("MyField3D copy created.");
    }

    /**
     * Implementazione omessa. La classe è stata messa solo per rendere più completa la gerarchia delle classi
     */
    public Set<ICell>  getIntorno(ICell cellula) {
        //implementazione omessa
        return null;
    }

    /**
     * Viene ritornata una nuova istanza passando come parametro del costruttore questa stessa istanza
     */
    @Override
    public IField<T> deepCopyClone() {
        return new MyField3D<>(this);
    }

}
