package it.unicam.cs.pa.jlife105718;

import java.util.*;
import java.util.function.Function;

public abstract class Campo<T extends IPosizione> implements ICampo<T> {
    private static int count =-1;
    private int[] values;
    private  Map<T, Cellula> mappaPosizioneCellula;
    private Function<List<Integer>, ? extends T> transition;
    public Campo( Function<List<Integer>,? extends T> transition, int ... values) {
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        this.values = values;
    }

    @Override
    public ICampo<T> deepCopyOfThis() {
        ICampo<T> campo = getInstance();
        Map<T, Cellula> copy = new HashMap<T, Cellula>();
        for (Map.Entry<T, Cellula> entry : getMappaPosizioneCellula().entrySet())
        {
            copy.put(entry.getKey(),
                    new Cellula(entry.getValue().getStato(),entry.getValue().getId()));
        }
        campo.setMappaPosizioneCellula(copy);
        return campo;
    }

    protected abstract ICampo<T> getInstance();

    @Override
    public void changeCellula(Cellula cellula){
        T pos = getPosizioneFromCellula(cellula);
        this.getMappaPosizioneCellula().get(pos).changeStato();
    }

    public int[] getValues() {
        return values;
    }

    @Override
    public abstract Set<Cellula> getIntorno(Cellula cellula);

    @Override
    public T getPosizioneFromCellula(Cellula cellula) {
        Optional<T> posToReturn = mappaPosizioneCellula.entrySet()
                .stream()
                .filter(entry -> cellula.getId() ==(entry.getValue().getId()))
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

    public void setMappaPosizioneCellula(Map<T,Cellula> mappaPosizioneCellula){
        this.mappaPosizioneCellula= mappaPosizioneCellula;
    }
    @Override
    public Function<List<Integer>, ? extends T> getTransition() {
        return this.transition;
    }

    @Override
    public void addAEntry( int ... values) {
        if(values.length == this.values.length && !isIntoMap(values)) {
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
    public Integer getIntegerFromCellula(Cellula cellula, int i){
        T pos = getPosizioneFromCellula(cellula);
        return pos.getCoordinateI(i);
    }

    @Override
    public void changeStateOfACellula(int ... values) {
            T pos = getPosizioneFromInteger(values);
            getMappaPosizioneCellula().get(pos).changeStato();
    }
}
