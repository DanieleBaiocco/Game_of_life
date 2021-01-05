package it.unicam.cs.pa.jlife105718;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class Campo<T extends IPosizione> implements ICampo<T> {
    private static int count =-1;
    private int[] values;
    private  Map<T, Cellula> mappaPosizioneCellula;
    private CurrentTransitionEnum transitionEnum;
    private int dim;
    public Campo(CurrentTransitionEnum transitionEnum, int dim, int... values) {
        if(values.length != dim)
            throw new IllegalArgumentException();
        this.transitionEnum=transitionEnum;
        this.mappaPosizioneCellula= new HashMap<>();
        this.values = values;
        //TransitionFactory.getInstance().getTransition(transitionEnum).apply(values);
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
    public CurrentTransitionEnum getTransition() {
        return this.transitionEnum;
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
        return (T) TransitionFactory.getInstance().getTransition(transitionEnum).apply(values);
    }
@Override
    public CurrentTransitionEnum getTransitionEnum(){
        return this.transitionEnum;
}

    @Override
    public T getPosizioneFromIntegers(int ... values){
        return (T) TransitionFactory.getInstance().getTransition(transitionEnum).apply(values);
    }
@Override
    public int[] getIntegerFromCellula(Cellula cellula){
        T pos = getPosizioneFromCellula(cellula);
        return pos.getCoordinateI();
    }

    @Override
    public void changeStateOfACellula(int ... values) {
            T pos = getPosizioneFromInteger(values);
            getMappaPosizioneCellula().get(pos).changeStato();
    }
}
