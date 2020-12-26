package it.unicam.cs.pa.jlife105718;

import java.util.List;

public class PosizioneVirgolaMobile extends PosizioneNumber{

    public PosizioneVirgolaMobile(List<Integer> params){
        super(params);
    }

    @Override
     public Double changeToPos(int x) {
        return (double) x;
    }

}
