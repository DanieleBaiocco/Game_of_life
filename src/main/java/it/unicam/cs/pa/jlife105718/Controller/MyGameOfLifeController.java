package it.unicam.cs.pa.jlife105718.Controller;


import it.unicam.cs.pa.jlife105718.Model.Board.IField;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionFactory;
import it.unicam.cs.pa.jlife105718.Model.PropertyListener;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesFactory;

import java.util.logging.Logger;

/**
 * Implementazione di un IController. Responsabile di seguire lo sviluppo della griglia in tutto il suo
 * percorso dall'inizio alla fine. Definisce infatti come calcolare la Next Generation e come cambiare lo stato di
 * una cellula. E' il punto di entrata allo strato del dominio
 */
public class MyGameOfLifeController<T extends IPosition> implements IController<T>, PropertyListener {
private final IField<T> campo;
private final RulesEnum rule;
private int[] cellsToSetAlive;
private static final Logger logger = Logger.getGlobal();

public MyGameOfLifeController(IField<T> campo, RulesEnum rule, int[] cellsToSetAlive){
    this.campo = campo;
    this.rule = rule;
    this.cellsToSetAlive = cellsToSetAlive;
    logger.finest("MyGameOfLifeController created.");
    }

    /**
     * Ritorna le celle il cui stato deve esser messo vivo in fase di set up
     */
    public int[] getCellsToSetAlive() {
        return cellsToSetAlive;
}

    /**
     * Ritorna la rappresentazione in stringa di una coordinata della posizione di una cellula
     */
    @Override
public String getRepresentation(int i, int... values) {
    T pos =campo.getPosizioneFromIntegers(values);
    return  PositionFactory.getInstance().getPrinter(campo.getTransitionEnum()).toStringFormat(pos).get(i);
}


    /**
     *  Calcola la prossima generazione andando a prendere dalla RulesFactory la regola associata al valore
     *  dell'enumerazione della RulesEnum e applicandola a ogni singola cellula nella griglia
     */
    @Override
public void nextGen() {
    IField<T> campoCopy =  this.campo.deepCopyClone();
    campoCopy.getMappaPosizioneCellula().values().forEach(x->x.addPropertyListener(this));
    campoCopy.getMappaPosizioneCellula().values()
            .forEach(x-> RulesFactory.getInstance().getRule(rule).step(x, campoCopy.getIntorno(x)));
}

    /**
     * Cambia lo stato di una cellula passando i valori che rappresentano la posizione in array di interi
     */
    @Override
    public void colorateDecolorateACellula(int ... posInInt) {
      campo.changeStateOfACellula(posInInt);
      String str = "";
      for(int value : posInInt){
          str = str.concat(String.valueOf(value));
      }
      System.out.println(str);
    }

    /**
     * Ritorna il campo legato a questo controller
     */
    @Override
    public IField<T> getCampo() {
        return this.campo;
    }

    /**
     * Permette di aggiungere una nuova cellula all'interno della mappa del campo posizione-cellula
     */
    @Override
    public void addAEntry(int... values) {
    this.campo.addAEntry(values);
    }

    /**
     * Ritorna una cellula dalla rappresentazione della sua posizione in array di interi
     */
    @Override
    public ICell getCellulaFromInteger(int... values) {
        return campo.getCellulaFromInteger(values);
    }

    /**
     * Ritorna la regola legata a questo controller
     */
    public RulesEnum getRule(){
        return this.rule;
    }

    /**
     * Reagisce al cambiamento dello stato di una cellula andando a resettare lo stato di quella cellula a quello
     * precedente, prima del cambiamento, e poi andando a cambiare effettivamente lo stato della cellula corrispondente
     * alla cellula rimasta invariata nel campo IField attributo della classe
     * @param source la cella da cui proviene il cambiamento
     * @param name nome del cambiamento
     * @param state il nuovo stato a cui è passata la cella
     */
    @Override
    public void onPropertyEvent(ICell source, String name, Stato state) {
       if(state==Stato.VIVO)
           source.setStato(Stato.MORTO);
       else source.setStato(Stato.VIVO);
       this.campo.changeCellula(source);
    }

}


