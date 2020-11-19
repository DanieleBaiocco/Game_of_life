package it.unicam.cs.pa.jlife105718;

import java.util.Map;
import java.util.Set;

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
    public T getPosizioneFromCellula(Cellula cellula) {
        return null;
    }
}
