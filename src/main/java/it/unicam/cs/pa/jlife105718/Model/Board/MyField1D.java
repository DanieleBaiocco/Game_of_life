package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.util.Set;

public class MyField1D<T extends IPosition> extends MyField<T> {
    public MyField1D(PositionsEnum currentTransitionEnum , int value1) {
        super(currentTransitionEnum,1, value1);
    }

    @Override
    public Set<ICell> getIntorno(ICell cellula) {
        //implementazione omessa
        return null;
    }

    @Override
    protected IField<T> getInstance() {
        return new MyField1D<>(getTransition(),getValues()[0]);
    }


}
