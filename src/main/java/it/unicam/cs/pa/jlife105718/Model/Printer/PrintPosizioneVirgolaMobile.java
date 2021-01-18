package it.unicam.cs.pa.jlife105718.Model.Printer;

import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneVirgolaMobile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mi dà una rappresentazione sotto forma di stringhe delle coordinate numeriche in virgola mobile
 */
public class PrintPosizioneVirgolaMobile implements IPrintPosition<PosizioneVirgolaMobile>{

    @Override
    public List<String> toStringFormat(PosizioneVirgolaMobile posizioneVirgolaMobile) {
        return Arrays.stream(posizioneVirgolaMobile.getParams()).map(Object::toString).collect(Collectors.toList());
    }
}
