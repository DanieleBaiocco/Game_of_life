package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;

/**
 * Le classi che implementano questa interfaccia devono esser in grado di fornire un meccanismo per il quale,
 * data una loro istanza, tramite una copia profonda, viene ritornata una copia di quell'istanza.
 * @param <T>
 */
public interface DeepCopy<T extends IPosition> {
    /**
     * Viene fatta una copia in profondità dell'istanza che viene ritornata. La classe a cui appartiene l'istanza
     * deve, per ovvi motivi, implementare l'interfaccia IField
     * @return
     */
    IField<T> deepCopyClone();
}
