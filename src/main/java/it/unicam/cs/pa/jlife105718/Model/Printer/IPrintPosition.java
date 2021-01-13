package it.unicam.cs.pa.jlife105718.Model.Printer;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;

@FunctionalInterface
public interface IPrintPosition<T extends IPosition> {
    Object[] toStringFormat(T posizione);
}
