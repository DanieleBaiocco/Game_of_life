package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.logging.Logger;

/**
 * Un'istanza di questa classe ha all'interno coordinate intere (es. {5,4}, {2,7,8},...)
 */

public class PosizioneNumericaIntera extends PosizioneNumerica {

    private static final Logger logger = Logger.getGlobal();
    public PosizioneNumericaIntera(int[] params){
        super(params);
        logger.finest("Integer Position created");
    }

    /**
     * La conversione dell'intero nella singola coordinata è un semplice ritorno dell'intero
     * senza nessun cambiamento
     */
    @Override
    public Integer changeToPos(int x) {
    return x;
    }
}
