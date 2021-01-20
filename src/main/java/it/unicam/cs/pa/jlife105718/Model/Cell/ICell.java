package it.unicam.cs.pa.jlife105718.Model.Cell;

import it.unicam.cs.pa.jlife105718.Model.PropertyListener;

/**
 * Interfaccia responsabile di mantenere lo stato di una cellula all'interno della griglia. La classe che implementa
 * questa interfaccia deve implementare un meccanismo di aggiunta di ascoltatori ai cambiamenti della cellula, e,
 * in caso di cambiamenti, di notifica
 */
public interface ICell {
     Stato getStato();
     int getId();
     void setStato(Stato stato);
     void changeStato();
     boolean isAlive();
     void addPropertyListener (PropertyListener lis);
     boolean equals(Object o);
     int hashCode();
     void setId(int id);

}
