package it.unicam.cs.pa.jlife105718.Model.Position;

import it.unicam.cs.pa.jlife105718.Model.Printer.IPrintPosition;
import it.unicam.cs.pa.jlife105718.Model.Printer.PrintPosizioneAlfabetica;
import it.unicam.cs.pa.jlife105718.Model.Printer.PrintPosizioneIntera;
import it.unicam.cs.pa.jlife105718.Model.Printer.PrintPosizioneVirgolaMobile;

import java.util.function.Function;

/**
 * Classe che realizza il pattern factory: ha la responsabilità di creare una funzione
 * che permette di passare da un array di interi a una particolare posizione per ogni tipo di posizione (intera, alfabetica, virgola mobile)
 * e di creare un printer (serve a trasformare le coordinate di una specifica posizione in stringhe) per ogni tipo di posizione.
 */
public class PositionFactory {
    static private PositionFactory instance;

    /**
     * Ritorna sempre la stessa istanza di PositionFactory
     */
    static public PositionFactory getInstance(){
        if(instance == null) {
            instance =new PositionFactory();
        }
        return instance;
    }

    /**
     * Da un elemento dell'enumerazione dei diversi tipi di posizioni, viene creata la funzione che mi creerà
     * una specifica posizione del tipo specificato dall'elemento dell'enumerazione.
     */
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

    /**
     * Da un elemento dell'enumerazione dei diversi tipi di posizioni, viene creato il Printer corrispondente per
     * quel tipo di posizione
     */
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



