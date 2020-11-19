package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Campo3D<T extends Posizione> implements Campo<Posizione>{

    @Override
    public Set<Cellula>  getIntorno(Cellula cellula) {
        return null;
    }

    @Override
    public <T> Map<T, Cellula> getMappaPosizioneCellula() {
        return null;
    }

    @Override
    public Function<List<Integer>, ? extends Posizione> getTransition() {
        return null;
    }

    @Override
    public T getPosizioneFromCellula(Cellula cellula) {
        return null;
    }
}
