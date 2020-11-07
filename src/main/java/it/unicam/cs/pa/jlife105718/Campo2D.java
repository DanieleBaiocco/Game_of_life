package it.unicam.cs.pa.jlife105718;

import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Campo2D<Posizione> implements Field  {
    private final int ascissa;
    private final int ordinata;
    private Map<Posizione, Cellula> mappaPosizioneCellula;
    private BiFunction<Integer,Integer, Posizione> transition;
    public Campo2D(int ascissa, int ordinata, BiFunction<Integer, Integer, Posizione> transition) {
        this.ascissa=ascissa;
        this.ordinata=ordinata;
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        initializeMap(this.ascissa,this.ordinata, transition);
    }
    public boolean findCellula(Cellula cellula){
        return this.mappaPosizioneCellula.containsValue(cellula);
    }
    public Cellula searchCellula(Cellula cellula){
        if (findCellula(cellula))
                return cellula;
       return null;
            }

    private void initializeMap(int asc, int ord, BiFunction<Integer, Integer, Posizione> transition) {
        for(int i=0; i<ord;i++) {
            for (int j = 0; j < asc; j++){
                Cellula cellula = new Cellula(Stato.MORTO);
                Posizione posizione=transition.apply(j,i);
                this.mappaPosizioneCellula.put(posizione,cellula);
            }
        }
    }

    public Map<Posizione,Cellula> getIntorno(Cellula cellula){
        if(this.mappaPosizioneCellula.containsValue(cellula)) {
            return null;
        }return null;
        }

    public Map<Posizione,Cellula> getMappaPosizioneCellula() {
        return this.mappaPosizioneCellula;
    }

    public int getAscissa() {
        return ascissa;
    }

    public int getOrdinata() {return ordinata;}
}
