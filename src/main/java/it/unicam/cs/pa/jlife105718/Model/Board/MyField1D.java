package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.util.Set;

public class MyField1D<T extends IPosition> extends MyField<T> implements IField1D<T>{
    public MyField1D(PositionsEnum currentTransitionEnum , int value1) {
        super(currentTransitionEnum,1, value1);
    }

    public MyField1D(IField<T> field) {
        super(field);
    }

    @Override
    public Set<ICell> getIntorno(ICell cellula) {
        //implementazione omessa
        return null;
    }

    @Override
    public IField<T> deepCopyClone() {
        return new MyField1D<>(this);
    }


}
