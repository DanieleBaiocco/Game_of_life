package it.unicam.cs.pa.jlife105718;

public class PosizioneNumericaIntera extends PosizioneNumber {

    public PosizioneNumericaIntera(int[] params){
        super(params);
    }

    @Override
    public Integer changeToPos(int x) {
    return x;
    }
}
