package it.unicam.cs.pa.jlife105718.Model.Position;

public class PosizioneNumericaIntera extends PosizioneNumerica {

    public PosizioneNumericaIntera(int[] params){
        super(params);
    }

    @Override
    public Integer changeToPos(int x) {
    return x;
    }
}
