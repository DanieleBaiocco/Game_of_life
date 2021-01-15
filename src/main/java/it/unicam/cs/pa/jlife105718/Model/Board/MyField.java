package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.MyCell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

public abstract class MyField<T extends IPosition> extends GenericField<T>{
    public MyField(PositionsEnum transitionEnum, int dim, int... values) {
        super(transitionEnum, dim, values);
    }

    public MyField(IField<T> field) {
        super(field);
    }

    public ICell getCell(Stato stato, int id){
        return new MyCell(stato, id);
    };
}
