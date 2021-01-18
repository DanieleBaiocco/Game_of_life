package it.unicam.cs.pa.jlife105718.Model;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;

/**
 * Interfaccia che forza le classi che la implementano a scrivere il metodo OnPropertyEvent. Le classi che la
 * implementano, sottoscrivendosi precedentemente a una cella (mettendosi in ascolto in caso di un cambiamento
 * interno alla cella), reagiscono al cambiamento eseguendo il metodo OnPropertyEvent che permette ad esempio
 * di visualizzare il cambiamento della cella a livello di view. Questa interfaccia è stata introdotta per
 * implementare il pattern Observer
 */
public interface PropertyListener {

    /**
     * Codice che va in esecuzione a seguito di un cambiamento di una cella su cui la classe che implementa
     * PropertyListener è in ascolto. La cella notifica a tutte le classi in ascolto il cambiamento mandando
     * in esecuzione questo metodo su ogni classe. Una diversa implementazione permette di avere una diversa
     * reazione al cambiamento della cella
     * @param source la cella da cui proviene il cambiamento
     * @param name nome del cambiamento
     * @param state il nuovo stato a cui è passata la cella
     */
    void onPropertyEvent(ICell source, String name, Stato state);
}
