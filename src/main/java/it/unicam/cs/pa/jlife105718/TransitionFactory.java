package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.function.Function;

public class TransitionFactory {
    static private TransitionFactory instance;
    private  final Function<List<Integer>,PosizioneAlfabetica> transitionToCharacter;
    private  final Function<List<Integer>,PosizioneNumerica> transitionToInteger;

    static public TransitionFactory getInstance(){
        if(instance == null) {
            instance =new TransitionFactory();
        }
        return instance;
    }
private TransitionFactory(){
        transitionToCharacter = PosizioneAlfabetica::new;
        transitionToInteger= PosizioneNumerica::new;
    }

    public Function<List<Integer>, PosizioneAlfabetica> getTransitionToChar() {
        return transitionToCharacter;
    }

    public Function<List<Integer>, PosizioneNumerica> getTransitionToInteger() {
        return transitionToInteger;
    }

}



