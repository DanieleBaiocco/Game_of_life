package it.unicam.cs.pa.jlife105718.Model.Rule;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;

import java.util.Set;

/**
 * Ha la responsabilità di fornire un'implementazione del metodo step di Rule<ICell>
 * che calcola lo stato della nuova cellula secondo una variante alternativa del Game Of Life.
 * L'implementazione è omessa: questa classe è stata introdotta solamente per rendere più variegato l'elenco
 * delle possibili regole
 */
public class AlternativeRules1 implements Rule<ICell> {

    /**
     * @param cellula cellula di cui voglio calcolare il nuovo stato
     * @param intorno cellule intorno a questa cellula che condizionano il calcolo del nuovo stato
     * @return
     */
    @Override
    public ICell step(ICell cellula, Set<ICell> intorno) {
        return null;
    }
}
