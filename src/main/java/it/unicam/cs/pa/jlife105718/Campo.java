package it.unicam.cs.pa.jlife105718;

import java.util.Map;
import java.util.Set;

public interface Campo<T extends Posizione> {
  Set<Cellula> getIntorno(Cellula cellula);
   T getPosizioneFromCellula (Cellula cellula);

}
