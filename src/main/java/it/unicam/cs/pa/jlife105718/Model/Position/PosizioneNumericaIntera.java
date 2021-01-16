package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.logging.Logger;

public class PosizioneNumericaIntera extends PosizioneNumerica {

    private static final Logger logger = Logger.getGlobal();
    public PosizioneNumericaIntera(int[] params){
        super(params);
        logger.finest("Integer Position created");
    }

    @Override
    public Integer changeToPos(int x) {
    return x;
    }
}
