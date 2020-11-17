package it.unicam.cs.pa.jlife105718;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Campo2D<Posizione> implements Campo<Posizione> {
    private final int a;
    private final int b;
    private Map<Posizione, Cellula> mappaPosizioneCellula;
    private final Function<List<Integer>, Posizione> transition;

    public Campo2D(int a, int b, Function<List<Integer>,Posizione> transition) {
        this.a = a;
        this.b = b;
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        initializeMap(this.a, this.b, transition);

    }

    protected void initializeMap(int asc, int ord, Function<List<Integer>, Posizione> transition) {
        for(int i=0; i<ord;i++) {
            for (int j = 0; j < asc; j++){
                initializeMapKeysAndValues(j,i);
            }
        }
    }
    private void initializeMapKeysAndValues(int j, int i){
        Cellula cellula = new Cellula(Stato.MORTO);
        Posizione posizione=transition.apply(getArrayList(j,i));
        this.mappaPosizioneCellula.put(posizione,cellula);
    }


    protected ArrayList<Integer> getArrayList(int j, int i){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(j);
        arr.add(i);
        return arr;
    }

    public boolean findCellula(Posizione posizione){
        return this.mappaPosizioneCellula.containsKey(posizione);
    }

    public Cellula searchCellula(Posizione posizione){
        return this.mappaPosizioneCellula.get(posizione);
    }

    public Integer getA(){
        return this.a;
    }
    public Integer getB() {
        return this.b;
    }

    @Override
    public Map getIntorno(Cellula cellula) {
        return null;
    }

    @Override
    public Map getMappaPosizioneCellula() {
        return this.mappaPosizioneCellula;
    }
}