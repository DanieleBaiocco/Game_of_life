package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.Arrays;

/**
 * Questa classe fa da aggregante per rappresentare ciò che è comune tra una posizione di tipo intera e una di tipo
 * virgola mobile
 */
public abstract class PosizioneNumerica extends MyPosition {
    public PosizioneNumerica(int[] params) {
        super(params);
    }

    /**
     * Per ogni coordinata di questa istanza, faccio una conversione in valore intero tamite il metodo fornito
     * dalla classe Number intValue
     * @return
     */
    @Override
    public int[] returnToIntegerCoordinates() {
        return  Arrays.stream(getParams()).map(x->(Number)x).mapToInt(Number::intValue).toArray();
    }

    /**
     * HashCode della singola coordinata calcolato moltiplicando prime con result e sommando il risultato
     * con la rappresentazione intera della coordinata
     */
    @Override
    protected int getResultForHash(Object obj, int result, int prime) {
        Number numberToNumb = (Number) obj;
        return prime * result +numberToNumb.intValue();
    }
}
