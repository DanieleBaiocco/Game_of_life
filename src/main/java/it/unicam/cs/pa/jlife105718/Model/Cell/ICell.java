package it.unicam.cs.pa.jlife105718.Model.Cell;

import it.unicam.cs.pa.jlife105718.Model.PropertyListener;

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
