package it.unicam.cs.pa.jlife105718.Model;

import java.util.logging.Logger;

/**
 * Ha la responsabilità di generare in maniera sequenziare degli id. Realizza il pattern Singleton per poter
 * esser chiamata in ogni punto del programma, mantenendo sempre la stessa istanza. Il contatore viene ritornato
 * ogni volta che viene chiamato l'unico metodo della classe, e poi  incrementato
 */
public class MyIdGenerator {
    private static final Logger logger = Logger.getGlobal();
    private int count = -1;
    private static MyIdGenerator instance;

    /**
     * Ritorna sempre una stessa istanza di MyIdGenerator (pattern Singleton), in modo da mantenere il valore
     * di count.
     * @return
     */
    public static MyIdGenerator getInstance(){
        if(instance ==null)
            instance=new MyIdGenerator();
        return instance;
    }

    /**
     * Ritorna il count e lo incrementa
     * @return il valore di count
     */
    public int getIdAndIncrement(){
        logger.finest("ID generated.");
        return ++count;
    }
}
