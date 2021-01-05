package it.unicam.cs.pa.jlife105718;

@FunctionalInterface
public interface IPrintPosition<T extends IPosizione> {
    Object[] toStringFormat(T posizione);
}
