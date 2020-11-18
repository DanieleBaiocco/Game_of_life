package it.unicam.cs.pa.jlife105718;

import java.util.Collection;
import java.util.Map;

@FunctionalInterface
public interface Regole<Cellula> {
     Cellula step(Cellula cellula) throws InterruptedException;
}
