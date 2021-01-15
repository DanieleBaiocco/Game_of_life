package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;

import java.util.Map;
import java.util.Set;

public interface IField<T extends IPosition> extends DeepCopy<T> {
    Set<ICell> getIntorno(ICell cellula);
    Map<T, ICell> getMappaPosizioneCellula();
    PositionsEnum getTransition();
    void addAEntry(int ... values) ;
    ICell getCellulaFromInteger(int ... values);
    void changeStateOfACellula(int ... values);
    int[] getValues();
    int[] getIntegerFromCellula(ICell cellula);
    void changeCellula(ICell cellula);
    void setMappaPosizioneCellula(Map<T, ICell> map);
    T getPosizioneFromIntegers(int ... values);
    PositionsEnum getTransitionEnum();
    ICell getCell(Stato stato, int id);
}
