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
    ICampo<T> deepCopyOfThis();
    void addAEntry(int ... values) ;
    boolean isIntoMap (int ... coordinate);
    Cellula getCellulaFromInteger(int ... values);
    void changeStateOfACellula(int ... values);
    int[] getValues();
    Integer getIntegerFromCellula(Cellula cellula,int i);
    void changeCellula(Cellula cellula);
    void setMappaPosizioneCellula(Map<T,Cellula> map);

}
