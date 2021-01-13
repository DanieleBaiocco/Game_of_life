package it.unicam.cs.pa.jlife105718.Model.Position;

public class PosizioneVirgolaMobile extends PosizioneNumerica {

    public PosizioneVirgolaMobile(int[] params){
        super(params);
    }

    @Override
     public Double changeToPos(int x) {
        return (double) x;
    }

}
