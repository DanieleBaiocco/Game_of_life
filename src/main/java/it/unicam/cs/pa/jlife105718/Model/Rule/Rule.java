package it.unicam.cs.pa.jlife105718.Model.Rule;

import java.util.Set;

@FunctionalInterface
public interface Rule<ICell> {
     ICell step(ICell cellula, Set<ICell> intorno);
}
