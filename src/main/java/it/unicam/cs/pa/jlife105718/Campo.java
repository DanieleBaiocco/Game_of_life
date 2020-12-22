package it.unicam.cs.pa.jlife105718;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

public abstract class Campo<T extends IPosizione> implements ICampo<T>{
    private static int count =-1;
    private  int dim;
    private  Map<T, Cellula> mappaPosizioneCellula;
    private  Function<List<Integer>, ? extends T> transition;
    public Campo( Function<List<Integer>,? extends T> transition, int dim) {
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
        this.dim = dim;
    }

    /*public Campo (Campo<T> that){
        this.transition = that.getTransition();
        this.dim = that.getDim();
        Gson gson = new Gson();
        String jsonString = gson.toJson(that.getMappaPosizioneCellula());
        Type type = new TypeToken<HashMap<T, Cellula>>(){}.getType();
        HashMap<T, Cellula> clonedMap = gson.fromJson(jsonString, type);
        this.setMappaPosizioneCellula(clonedMap);
    }*/

    @Override
    public void changeCellula(Cellula cellula){
        T pos = getPosizioneFromCellula(cellula);
        this.mappaPosizioneCellula.get(pos).changeStato();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Campo<T> campocopy = (Campo<T>) super.clone();
        Gson gson = new Gson();
        Map<T,Cellula> mapcopy = new HashMap<>();
        this.mappaPosizioneCellula.forEach((key, value) -> {
            String jsonString1 = gson.toJson(key);
            T posizione = gson.fromJson(jsonString1, (Type) key.getClass());
            String jsonString2 = gson.toJson(value);
            Type type2 = new TypeToken<Cellula>() {
            }.getType();
            Cellula cellula = gson.fromJson(jsonString2, type2);
            mapcopy.put(posizione, cellula);
        });
        campocopy.setMappaPosizioneCellula(mapcopy);
        return campocopy;
    }

    private void setMappaPosizioneCellula(Map<T, Cellula> mappaPosizioneCellula) {
        this.mappaPosizioneCellula = mappaPosizioneCellula;
    }

    public int getDim(){
        return this.dim;
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
    public Integer getIntegerFromCellula(Cellula cellula, int i){
        T pos = getPosizioneFromCellula(cellula);
        return pos.getCoordinateI(i);
    }

    @Override
    public void changeStateOfACellula(int ... values) {
            T pos = getPosizioneFromInteger(values);
            Cellula c = getMappaPosizioneCellula().get(pos);
            c.changeStato();
    }
}
