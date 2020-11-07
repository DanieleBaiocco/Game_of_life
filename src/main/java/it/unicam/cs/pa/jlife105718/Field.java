package it.unicam.cs.pa.jlife105718;

import java.util.Map;

public interface Field<Posizione> {
  Map<Posizione,Cellula> getIntorno(Cellula cellula);
  Map<Posizione,Cellula> getMappaPosizioneCellula();

}
