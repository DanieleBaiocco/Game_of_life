package it.unicam.cs.pa.jlife105718;

import java.util.List;

public abstract class PosizioneNumber extends Posizione{
    public PosizioneNumber(List<Integer> params) {
        super(params);
    }

    @Override
    public Integer getCoordinateI(int i) {
        return (Integer) getParams().get(i);
    }

    @Override
    protected int getResultForHash(Object obj, int result, int prime) {
        Integer numberToInt = (Integer) obj;
        return prime * result +numberToInt;
    }
}
