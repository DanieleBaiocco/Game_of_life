package it.unicam.cs.pa.jlife105718.Model.Rule;

import java.util.Set;

/**
 * Interfaccia funzionale che ha un solo metodo step. Chi la implementa definisce come calcolare il nuovo stato
 * di una cellula a seconda dello stato delle cellule intorno a lei
 * @param <ICell>
 */
@FunctionalInterface
public interface Rule<ICell> {

     /**
      * Unico metodo che, a seconda dello stato della cellula in input e delle cellule intorno a lei, ritorna
      * la cellula o con lo stesso o con un nuovo stato
      * @param cellula cellula di cui voglio calcolare il nuovo stato
      * @param intorno cellule intorno a questa cellula che condizionano il calcolo del nuovo stato
      * @return la cellula con il nuovo stato
      */
     ICell step(ICell cellula, Set<ICell> intorno);
}
