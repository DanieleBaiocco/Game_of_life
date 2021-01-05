package it.unicam.cs.pa.jlife105718;

public class PosizioneVirgolaMobile extends PosizioneNumber{

    public PosizioneVirgolaMobile(int[] params){
        super(params);
    }

    @Override
     public Double changeToPos(int x) {
        return (double) x;
    }

}
