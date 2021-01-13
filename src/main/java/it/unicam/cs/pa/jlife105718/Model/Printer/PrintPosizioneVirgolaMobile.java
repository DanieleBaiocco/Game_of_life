package it.unicam.cs.pa.jlife105718.Model.Printer;

import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneVirgolaMobile;

import java.util.Arrays;

public class PrintPosizioneVirgolaMobile implements IPrintPosition<PosizioneVirgolaMobile>{
    @Override
    public Object[] toStringFormat(PosizioneVirgolaMobile posizioneVirgolaMobile) {
        return Arrays.stream(posizioneVirgolaMobile.getParams()).map(Object::toString).toArray();
    }
}
