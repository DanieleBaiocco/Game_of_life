package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

/**
 * Mia particolare implementazione dell'abstract factory responsabile della creazione di griglie 1D, 2D e 3D.
 * Per le griglie 1D viene ritornata un'istanza di MyField1D, per quelle 2D viene ritornata un'istanza di MyField2D
 * e per le griglie 3D un'istanza di MyField3D. Tutte e 3 usano come cellule istanze di MyCell
 */
public class MyFactoryField implements IFactoryField {

    /**
     * Crea una griglia 1D istanza di MyField1D
     */
    @Override
    public <T extends IPosition> IField1D<T> createField1D(PositionsEnum transitionEnum, int value1) {
        return new MyField1D<>(transitionEnum,value1);
    }

    /**
     * Crea una griglia 2D istanza di MyField2D
     */
    @Override
    public <T extends IPosition> IField2D<T> createField2D(PositionsEnum transitionEnum, int value1, int value2) {
        return new MyField2D<>(transitionEnum,value1, value2);
    }

    /**
     * Crea una griglia 3D istanza di MyField3D
     */
    @Override
    public <T extends IPosition> IField3D<T>createField3D(PositionsEnum transitionEnum, int value1, int value2, int value3) {
        return new MyField3D<>(transitionEnum,value1, value2,value3);
    }
}
