package it.unicam.cs.pa.jlife105718;

import java.util.*;
import java.util.function.Function;

public class Campo2D<T extends Posizione> implements Campo<Posizione> {
    private final int a;
    private final int b;
    private Map<T, Cellula> mappaPosizioneCellula;
    private final Function<List<Integer>, ? extends T> transition;

    public Campo2D(int a, int b, Function<List<Integer>,? extends T> transition) {
        this.a = a;
        this.b = b;
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        initializeMap(this.a, this.b, transition);

    }

    protected void initializeMap(int asc, int ord, Function<List<Integer>, ? extends T> transition) {
        for(int i=0; i<ord;i++) {
            for (int j = 0; j < asc; j++){
                initializeMapKeysAndValues(j,i);
            }
        }
    }
    private void initializeMapKeysAndValues(int j, int i){
        Cellula cellula = new Cellula(Stato.MORTO);
        T posizione=transition.apply(getArrayList(j,i));
        this.mappaPosizioneCellula.put(posizione,cellula);
    }


    protected ArrayList<Integer> getArrayList(int j, int i){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(j);
        arr.add(i);
        return arr;
    }


    public Integer getA(){
        return this.a;
    }
    public Integer getB() {
        return this.b;
    }

    public T getPosizioneFromCellula (Cellula cellula){
        return mappaPosizioneCellula.entrySet()
                .stream()
                .filter(entry -> cellula.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    @Override
    public Set<Cellula> getIntorno(Cellula cellula) {
        T pos =getPosizioneFromCellula(cellula);
        int firstCoordinate = pos.getCoordinateI(0);
        int secondCoordinate = pos.getCoordinateI(1);


        return null;
    }


}
