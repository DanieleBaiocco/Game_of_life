package it.unicam.cs.pa.jlife105718.Model;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;

public interface PropertyListener {
    void onPropertyEvent(ICell source, String name, Stato state);
}
