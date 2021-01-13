package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.Arrays;

public abstract class PosizioneNumerica extends MyPosition {
    public PosizioneNumerica(int[] params) {
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
