package it.unicam.cs.pa.jlife105718.Model.Printer;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;

import java.util.List;

/**
 * Interfaccia funzionale responsabile della stampa di un insieme di coordinate dello stesso tipo.
 * La creazione di una classe che implementa questa interfaccia è delegata alla PositionFactory.
 */
@FunctionalInterface
public interface IPrintPosition<T extends IPosition> {

    /**
     * metodo responsabile della visualizzazione sotto forma di stringa di un insieme di coordinate che rappresentano
     * la posizione di una cella nella griglia
     * @param posizione contiene le coordinate che identificano questa posizione
     * @return le coordinate rappresentate come stringhe
     */
    List<String> toStringFormat(T posizione);
}
