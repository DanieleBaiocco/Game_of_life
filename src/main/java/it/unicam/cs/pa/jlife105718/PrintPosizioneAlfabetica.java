package it.unicam.cs.pa.jlife105718;

import java.util.Arrays;

public class PrintPosizioneAlfabetica implements IPrintPosition<PosizioneAlfabetica>{

    @Override
    public Object[] toStringFormat(PosizioneAlfabetica posizioneAlfabetica) {
       return Arrays.stream(posizioneAlfabetica.getParams()).map(x->x.toString()).toArray();
    }
}
