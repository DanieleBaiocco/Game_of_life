package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.function.Function;

public class TransitionFactory {
    static private TransitionFactory instance;
    private  final Function<List<Integer>,Posizione> transitionToCharacter;
    private  final Function<List<Integer>,Posizione> transitionToInteger;

    static public TransitionFactory getInstance(){
        if(instance == null) {
            return new TransitionFactory();
        }
        return instance;
    }
private TransitionFactory(){
        transitionToCharacter = PosizioneAlfabetica::new;
        transitionToInteger= PosizioneNumerica::new;
    }

    public Function<List<Integer>, Posizione> getTransitionToChar() {
        return transitionToCharacter;
    }

    public Function<List<Integer>, Posizione> getTransitionToInteger() {
        return transitionToInteger;
    }

}


