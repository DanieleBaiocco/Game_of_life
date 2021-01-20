package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;

import java.util.Map;
import java.util.Set;

/**
 * Interfaccia che introduce una griglia di qualsiasi tipo, parametrizzata rispetto a un tipo di coordinate.
 * Le classi che la implementano devono descrivere come avviene il calcolo delle celle intorno a una data cella passata,
 * come avviene l'inserimento di una nuova cellula tra le cellule della griglia.
 * La responsabilità primaria della classe che implementa questa interfaccia è quella di gestire una griglia formata da un
 * insieme di cellule
 * @param <T> l'interfaccia è parametrizzata rispetto a un tipo di coordinata
 */
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
