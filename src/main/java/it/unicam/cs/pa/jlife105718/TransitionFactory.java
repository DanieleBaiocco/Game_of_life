package it.unicam.cs.pa.jlife105718;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class TransitionFactory {
    static private TransitionFactory instance;
    private  final BiFunction<Integer, Integer,Posizione> transitionToChar;
    private  final BiFunction<Integer, Integer,Posizione> transitionToInteger;
    private  final  BiFunction<Integer, Integer,Posizione> transitionToDouble;

    static public TransitionFactory getInstance(){
        if(instance == null) {
            return new TransitionFactory();
        }
        return instance;
    }
private TransitionFactory(){
        transitionToChar = (x,y)->
            new PosizioneAlfabetica(getList(x,y),2);
        transitionToDouble = (x,y)->
            new PosizioneNumerica(getList(x,y),2);
        transitionToInteger = (x,y)->
                new PosizioneNumerica(getList(x,y),2);
    }
private ArrayList<Integer> getList(int x, int y){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(x);
        arr.add(y);
        return arr;
}
    public BiFunction<Integer, Integer, Posizione> getTransitionToChar() {
        return transitionToChar;
    }

    public BiFunction<Integer, Integer, Posizione> getTransitionToInteger() {
        return transitionToInteger;
    }

    public BiFunction<Integer, Integer, Posizione> getTransitionToDouble() {
        return transitionToDouble;
    }
}



