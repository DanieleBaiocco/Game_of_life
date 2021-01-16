package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.util.Set;
import java.util.logging.Logger;

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

    public Set<ICell>  getIntorno(ICell cellula) {
        //implementazione omessa
        return null;
    }

    @Override
    public IField<T> deepCopyClone() {
        return new MyField3D<>(this);
    }

}
