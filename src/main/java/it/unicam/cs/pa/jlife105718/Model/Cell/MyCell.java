/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.cs.pa.jlife105718.Model.Cell;

import it.unicam.cs.pa.jlife105718.Model.PropertyListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MyCell implements ICell {
    private static final Logger logger = Logger.getGlobal();
    private int id;
    private List<PropertyListener> listeners;
    private Stato stato;
    public MyCell(Stato stato, int id){
        this.stato=stato;
        listeners= new ArrayList<>();
        this.id = id;
        logger.finest("Cell with id: +"+id+" created");
    }

    public void setListeners(List<PropertyListener> listeners) {
        this.listeners = listeners;
    }

    public Stato getStato() {
        return this.stato;
    }

    public int getId(){
        return this.id;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public void changeStato(){
        if(stato == Stato.VIVO)
            setStato(Stato.MORTO);
        else setStato(Stato.VIVO);
        publishPropertyEvent("cellula.state",getStato());
    }
    public boolean isAlive(){
        if(this.getStato()==Stato.VIVO)
            return true;
        else return false;
    }
    public void addPropertyListener (PropertyListener lis){
        listeners.add(lis);
    }

    private void publishPropertyEvent(String name, Stato state){
        this.listeners.forEach(prop->prop.onPropertyEvent(this,name,state));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCell cellula = (MyCell) o;
        return this.id == cellula.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
