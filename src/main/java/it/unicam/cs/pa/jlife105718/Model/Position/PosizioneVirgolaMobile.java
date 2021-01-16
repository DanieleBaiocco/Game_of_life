package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.logging.Logger;

public class PosizioneVirgolaMobile extends PosizioneNumerica {

    private static final Logger logger = Logger.getGlobal();

    public PosizioneVirgolaMobile(int[] params){
        super(params);
        logger.finest("Floating-Point Position created.");
    }

    @Override
     public Double changeToPos(int x) {
        return (double) x;
    }

}
