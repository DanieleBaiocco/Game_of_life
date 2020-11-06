package it.unicam.cs.pa.jlife105718;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Campo2d<Posizione> implements Campo {
    private int ascissa;
    private int ordinata;
    BiFunction<Integer, Integer,Posizione> transition;
    private Map<Posizione, Cellula > mappaPosizioneCellula;
    public Campo2d(int ascissa, int ordinata, BiFunction<Integer, Integer, Posizione> transition) {
        this.ascissa=ascissa;
        this.ordinata=ordinata;
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        initializeMap(this.ascissa,this.ordinata, transition);
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
    public boolean findCellula(int CoordinateX, int CoordinateY){
       return this.mappaPosizioneCellula.containsKey(this.transition.apply(CoordinateX,CoordinateY));
    }

    public Cellula searchCellula(int CoordinateX, int CoordinateY){
       return this.mappaPosizioneCellula.get(this.transition.apply(CoordinateX,CoordinateY));
    }

    public Map<Posizione, Cellula> getMappaPosizioneCellula() {
        return mappaPosizioneCellula;
    }

    public void setMappaPosizioneCellula(Map<Posizione, Cellula> mappaPosizioneCellula) {
        this.mappaPosizioneCellula = mappaPosizioneCellula;
    }

    public Map<Posizione,Cellula> getIntorno(Cellula cellula){
        if(this.mappaPosizioneCellula.containsValue(cellula)) {
            return null;
        }return null;
        };

    public int getAscissa() {
        return ascissa;
    }

    public void setAscissa(int ascissa) {
        this.ascissa = ascissa;
    }

    public int getOrdinata() {return ordinata;}

    public void setOrdinata(int ordinata) {
        this.ordinata = ordinata;
    }
}
