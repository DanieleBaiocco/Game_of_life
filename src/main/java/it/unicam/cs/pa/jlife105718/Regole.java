package it.unicam.cs.pa.jlife105718;

import java.util.Set;

@FunctionalInterface
public interface Regole<Cellula> {
     Cellula step(Cellula cellula, Set<Cellula> intorno);
}
