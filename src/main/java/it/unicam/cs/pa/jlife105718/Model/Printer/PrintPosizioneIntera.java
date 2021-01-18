package it.unicam.cs.pa.jlife105718.Model.Printer;

import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneNumericaIntera;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mi dà una rappresentazione sotto forma di stringhe delle coordinate numeriche intere
 */
public class PrintPosizioneIntera implements IPrintPosition<PosizioneNumericaIntera>{
    @Override
    public List<String> toStringFormat(PosizioneNumericaIntera posizioneNumericaIntera) {
        return  Arrays.stream(posizioneNumericaIntera.getParams()).map(Object::toString).collect(Collectors.toList());
    }
}
