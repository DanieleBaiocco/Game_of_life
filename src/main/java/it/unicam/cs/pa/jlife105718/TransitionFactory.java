package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.function.Function;

public class TransitionFactory  {
    static private TransitionFactory instance;

    static public TransitionFactory getInstance(){
        if(instance == null) {
            instance =new TransitionFactory();
        }
        return instance;
    }

    public Function<List<Integer>, PosizioneAlfabetica> getTransitionToChar() {
        return PosizioneAlfabetica::new;
    }

    public Function<List<Integer>, PosizioneNumericaIntera> getTransitionToInteger() {
        return PosizioneNumericaIntera::new;
    }

    public Function<List<Integer>, PosizioneVirgolaMobile> getTransitionToDouble() {
        return PosizioneVirgolaMobile::new;
    }
}



