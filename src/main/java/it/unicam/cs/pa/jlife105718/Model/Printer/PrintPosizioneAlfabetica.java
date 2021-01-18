package it.unicam.cs.pa.jlife105718.Model.Printer;

import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneAlfabetica;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mi dà una rappresentazione sotto forma di stringhe delle coordinate alfabetiche
 */
public class PrintPosizioneAlfabetica implements IPrintPosition<PosizioneAlfabetica>{

    @Override
    public List<String> toStringFormat(PosizioneAlfabetica posizioneAlfabetica) {
       return  Arrays.stream(posizioneAlfabetica.getParams()).map(x->x.toString()).collect(Collectors.toList());
    }
}
