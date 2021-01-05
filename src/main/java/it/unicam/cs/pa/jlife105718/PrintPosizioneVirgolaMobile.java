package it.unicam.cs.pa.jlife105718;

import java.util.Arrays;

public class PrintPosizioneVirgolaMobile implements IPrintPosition<PosizioneVirgolaMobile>{
    @Override
    public Object[] toStringFormat(PosizioneVirgolaMobile posizioneVirgolaMobile) {
        return Arrays.stream(posizioneVirgolaMobile.getParams()).map(Object::toString).toArray();
    }
}
