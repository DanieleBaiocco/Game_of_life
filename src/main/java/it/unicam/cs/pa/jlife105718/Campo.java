package it.unicam.cs.pa.jlife105718;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public abstract class Campo<T extends IPosizione> implements ICampo<T>{
    private Map<T, Cellula> mappaPosizioneCellula;
    private final Function<List<Integer>, ? extends T> transition;
    public Campo( Function<List<Integer>,? extends T> transition) {
        this.transition=transition;
        this.mappaPosizioneCellula= new HashMap<>();
    }

    @Override
    public abstract Set<Cellula> getIntorno(Cellula cellula);

    @Override
    public T getPosizioneFromCellula(Cellula cellula) {
        return mappaPosizioneCellula.entrySet()
                .stream()
                .filter(entry -> cellula.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
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
    public void addAEntry(List<Integer> position, int dim) {
        if(position.size() ==dim) {
        T pos = transition.apply(position);
        Cellula cell = new Cellula(Stato.MORTO);
        mappaPosizioneCellula.put(pos,cell);
        }
        else throw new IllegalArgumentException();
    }
}
