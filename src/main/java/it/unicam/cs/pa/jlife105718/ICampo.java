package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface ICampo<T extends IPosizione> {
    Set<Cellula> getIntorno(Cellula cellula);
    T getPosizioneFromCellula(Cellula cellula);
    Map<T,Cellula> getMappaPosizioneCellula();
    Function<List<Integer>, ? extends T> getTransition();
    void addAEntry(List<Integer> position, int dim);

}