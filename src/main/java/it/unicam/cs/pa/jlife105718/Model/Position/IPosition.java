package it.unicam.cs.pa.jlife105718.Model.Position;

/**
 * Ogni classe che la implementa deve forire i metodi per passare da una coordinata intera a una coordinata
 * del tipo voluto da quella classe. Inoltre deve fornire anche il meccanismo per tornare da una coordinata
 * di quel tipo a una coordinata intera
 */
public interface IPosition {

    /**
     * Applica una conversione di tutte le coordinate  di un determinato tipo che rappresentano la posizione
     * di una cellula in interi usabili per lavorare su quella specifica posizione
     * @return
     */
    int[] returnToIntegerCoordinates() ;

    /**
     * Applica una conversione da una coordinata singola intera a una coordinata singola del tipo voluto dalla classe
     * @param x la coordinata intera
     * @return
     */
    Object changeToPos(int x);

    /**
     * Tutte le coordinate che insieme identificano la specifica posizione
     * @return
     */
    Object[] getParams();

}
