package it.unicam.cs.pa.jlife105718;

import java.util.function.Function;

public class TransitionFactory  {
    static private TransitionFactory instance;

    static public TransitionFactory getInstance(){
        if(instance == null) {
            instance =new TransitionFactory();
        }
        return instance;
    }

    public <T extends IPosizione> Function<int[], ? extends T> getTransition(CurrentTransitionEnum transitionEnum){
        Function<int[], ? extends T> transitionToReturn = null;
        switch (transitionEnum){
            case Alfabetico:
                transitionToReturn = x-> (T) new PosizioneAlfabetica(x);
                break;
            case Interno:
                transitionToReturn = x-> (T) new PosizioneNumericaIntera(x);
                break;
            case VirgolaMobile:
                transitionToReturn = x->(T)new PosizioneVirgolaMobile(x);
                break;
        }
        return transitionToReturn;
    }

    public <T extends IPosizione> IPrintPosition<T> getPrinter(CurrentTransitionEnum transitionEnum){
        IPrintPosition<T> printer = null;
        switch (transitionEnum){
            case Alfabetico:
                printer = (IPrintPosition<T>) new PrintPosizioneAlfabetica();
                break;
            case Interno:
                printer = (IPrintPosition<T>) new PrintPosizioneIntera();
                break;
            case VirgolaMobile:
                printer = (IPrintPosition<T>) new PrintPosizioneVirgolaMobile();
                break;
        }
        return printer;
    }

}



