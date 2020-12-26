package it.unicam.cs.pa.jlife105718;

import java.util.List;

public class PosizioneNumericaIntera extends PosizioneNumber {

    public PosizioneNumericaIntera(List<Integer> params){
        super(params);
    }

    @Override
    public Integer changeToPos(int x) {
    return x;
    }
}
