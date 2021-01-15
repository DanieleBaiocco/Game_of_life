package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;

public interface DeepCopy<T extends IPosition> {
    IField<T> deepCopyClone();
}
