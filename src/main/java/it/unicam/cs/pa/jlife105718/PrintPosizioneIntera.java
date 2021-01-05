package it.unicam.cs.pa.jlife105718;

import java.util.Arrays;

public class PrintPosizioneIntera implements IPrintPosition<PosizioneNumericaIntera>{

    @Override
    public Object[] toStringFormat(PosizioneNumericaIntera posizioneNumericaIntera) {
        return Arrays.stream(posizioneNumericaIntera.getParams()).map(Object::toString).toArray();
    }
}
