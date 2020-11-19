package it.unicam.cs.pa.jlife105718;

import java.util.*;
import java.util.function.Function;

public class Campo1D<T extends Posizione> implements Campo<Posizione> {
    private final int a;
    private Map<Posizione, Cellula> mappaPosizioneCellula;
    private final Function<List<Integer>, Posizione> transition;
    public Campo1D(int a, Function<List<Integer>, Posizione> transition) {
        this.a = a;
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        initializeMap(this.a, transition);
    }

    private void initializeMap(int firstInt, Function<List<Integer>, Posizione> transition) {
            for (int j = 0; j < firstInt; j++){
                initializeMapKeysAndValues(j);
            }
        }

    private void initializeMapKeysAndValues(int j){
        Cellula cellula = new Cellula(Stato.MORTO);
        Posizione posizione=transition.apply(getArrayList(j));
        this.mappaPosizioneCellula.put(posizione,cellula);
    }

    private ArrayList<Integer> getArrayList(int j){
        ArrayList<Integer> arr= new ArrayList<>();
        arr.add(j);
        return arr;
    }

    @Override
    public Posizione getPosizioneFromCellula(Cellula cellula) {
        return null;
    }

    @Override
    public Set<Cellula> getIntorno(Cellula cellula) {
        return null;
    }

    @Override
    public <T> Map<T, Cellula> getMappaPosizioneCellula() {
        return null;
    }


    public Integer getA(){
        return this.a;
    }
}
