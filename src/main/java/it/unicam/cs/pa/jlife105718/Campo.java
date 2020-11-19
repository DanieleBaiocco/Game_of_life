package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface Campo<T extends Posizione> {
  Set<Cellula> getIntorno(Cellula cellula);
  <T> T getPosizioneFromCellula (Cellula cellula);
   <T> Map<T,Cellula> getMappaPosizioneCellula();
     Function<List<Integer>, ? extends T> getTransition();
}
