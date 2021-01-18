package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.logging.Logger;

/**
 * Un'istanza di questa classe ha all'interno coordinate alfabetiche (es. {4.0,2.0}, {6.0,4.0},...)
 */
public class PosizioneVirgolaMobile extends PosizioneNumerica {

    private static final Logger logger = Logger.getGlobal();

    public PosizioneVirgolaMobile(int[] params){
        super(params);
        logger.finest("Floating-Point Position created.");
    }

    /**
     * La conversione dell'intero nella singola coordinata è un cast esplicito da integer a double con un conseguente
     * troncamento (tutte le coordinate di ogni istanza di PosizioneVirgolaMobile termineranno difatti tutte con la parte
     * decimale con sempre e solo lo 0)
     * @param x la coordinata intera
     */
    @Override
     public Double changeToPos(int x) {
        return (double) x;
    }

}
