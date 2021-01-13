package it.unicam.cs.pa.jlife105718.Model.Position;

import it.unicam.cs.pa.jlife105718.Model.Printer.IPrintPosition;
import it.unicam.cs.pa.jlife105718.Model.Printer.PrintPosizioneAlfabetica;
import it.unicam.cs.pa.jlife105718.Model.Printer.PrintPosizioneIntera;
import it.unicam.cs.pa.jlife105718.Model.Printer.PrintPosizioneVirgolaMobile;

import java.util.function.Function;

public class PositionFactory {
    static private PositionFactory instance;

    static public PositionFactory getInstance(){
        if(instance == null) {
            instance =new PositionFactory();
        }
        return instance;
    }

    public <T extends IPosition> Function<int[], ? extends T> getTransition(PositionsEnum transitionEnum){
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

    public <T extends IPosition> IPrintPosition<T> getPrinter(PositionsEnum transitionEnum){
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



