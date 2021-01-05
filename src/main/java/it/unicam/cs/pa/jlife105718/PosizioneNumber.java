package it.unicam.cs.pa.jlife105718;

import java.util.Arrays;

public abstract class PosizioneNumber extends Posizione{
    public PosizioneNumber(int[] params) {
        super(params);
    }

    @Override
    public int[] getCoordinateI() {
        return  Arrays.stream(getParams()).map(x->(Number)x).mapToInt(Number::intValue).toArray();
        /*Number number=  (Number) getParams()[i];
        return number.intValue();*/
    }

    @Override
    protected int getResultForHash(Object obj, int result, int prime) {
        Number numberToNumb = (Number) obj;
        return prime * result +numberToNumb.intValue();
    }
}
