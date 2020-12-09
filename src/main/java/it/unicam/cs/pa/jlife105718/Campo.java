package it.unicam.cs.pa.jlife105718;

import java.util.*;
import java.util.function.Function;

public abstract class Campo<T extends IPosizione> implements ICampo<T>{
    private static int count =-1;
    private final int dim;
    private final Map<T, Cellula> mappaPosizioneCellula;
    private final Function<List<Integer>, ? extends T> transition;
    public Campo( Function<List<Integer>,? extends T> transition, int dim) {
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        this.dim = dim;
    }

    @Override
    public abstract Set<Cellula> getIntorno(Cellula cellula);

    @Override
    public T getPosizioneFromCellula(Cellula cellula) {
        Optional<T> posToReturn = mappaPosizioneCellula.entrySet()
                .stream()
                .filter(entry -> cellula.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
        if (posToReturn.isPresent())
            return posToReturn.get();
        else throw new NullPointerException();
    }

    @Override
    public Map<T, Cellula> getMappaPosizioneCellula() {
        return this.mappaPosizioneCellula;
    }

    @Override
    public Function<List<Integer>, ? extends T> getTransition() {
        return this.transition;
    }

    @Override
    public void addAEntry( int ... values) {
        if(values.length == this.dim && !isIntoMap(values)) {
            T pos  = getPosizioneFromInteger(values);
            Cellula cell = new Cellula(Stato.MORTO,iterateAndReturnCount());
            mappaPosizioneCellula.put(pos,cell);
        }
        else throw new IllegalArgumentException();
    }

    private int iterateAndReturnCount(){
        count++;
        return count;
    }

    //non so se mettere questo metodo
    @Override
    public boolean isIntoMap(int ... coordinate){
        T pos = getPosizioneFromInteger(coordinate);
        return this.mappaPosizioneCellula.containsKey(pos);
    }

    //non so se mettere questo metodo
    public Cellula getCellulaFromInteger(int ... values){
        if(isIntoMap(values)){
            T pos= getPosizioneFromInteger(values);
            return this.mappaPosizioneCellula.get(pos);
        }else
            throw new IllegalArgumentException();
    }

    //non so se mettere questo metodo pubblico o privato
    private T getPosizioneFromInteger( int ... values){
        List<Integer> list = new ArrayList<>();
        for ( int x: values){
            list.add(x);
        }
        return transition.apply(list);
    }

    @Override
    public void changeStateOfACellula(int ... values) {
            T pos = getPosizioneFromInteger(values);
            getMappaPosizioneCellula().get(pos).changeStato();
    }
}
