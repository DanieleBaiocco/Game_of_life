package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.function.Function;

public class TransitionFactory  {
    static private TransitionFactory instance;
    private  final Function<List<Integer>,PosizioneAlfabetica> transitionToCharacter;
    private  final Function<List<Integer>, PosizioneNumericaIntera> transitionToInteger;
    private  final Function<List<Integer>, PosizioneVirgolaMobile> transitionToDouble;

    static public TransitionFactory getInstance(){
        if(instance == null) {
            instance =new TransitionFactory();
        }
        return instance;
    }

private TransitionFactory(){
        transitionToCharacter = PosizioneAlfabetica::new;
        transitionToInteger= PosizioneNumericaIntera::new;
        transitionToDouble= PosizioneVirgolaMobile::new;
    }

    public Function<List<Integer>, PosizioneAlfabetica> getTransitionToChar() {
        return transitionToCharacter;
    }

    public Function<List<Integer>, PosizioneNumericaIntera> getTransitionToInteger() {
        return transitionToInteger;
    }

    public Function<List<Integer>, PosizioneVirgolaMobile> getTransitionToDouble() {
        return transitionToDouble;
    }
}



