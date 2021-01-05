package it.unicam.cs.pa.jlife105718;

import java.util.Map;
import java.util.Set;

public interface ICampo<T extends IPosizione> {
    Set<Cellula> getIntorno(Cellula cellula);
    T getPosizioneFromCellula(Cellula cellula);
    Map<T,Cellula> getMappaPosizioneCellula();
    CurrentTransitionEnum getTransition();
    ICampo<T> deepCopyOfThis();
    void addAEntry(int ... values) ;
    boolean isIntoMap (int ... coordinate);
    Cellula getCellulaFromInteger(int ... values);
    void changeStateOfACellula(int ... values);
    int[] getValues();
    int[] getIntegerFromCellula(Cellula cellula);
    void changeCellula(Cellula cellula);
    void setMappaPosizioneCellula(Map<T,Cellula> map);
    T getPosizioneFromIntegers(int ... values);
    CurrentTransitionEnum getTransitionEnum();

}
