package it.unicam.cs.pa.jlife105718.Model.Rule;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;

import java.util.Set;

/**
 * Ha la responsabilità di fornire un'implementazione del metodo step di Rule<ICell>
 * che calcola lo stato della nuova cellula secondo le regole standar classiche del Game Of Life
 */
public class BasicRules implements Rule<ICell> {


    /**
     * Se una cellula è viva e intorno ci sono o meno di 2 o più di 3 cellule vive allora la cellula muore,
     * se una cellula è morta e intorno ci sono esattamente tre cellule vive allora torna in vita
     * @param cellula cellula di cui voglio calcolare il nuovo stato
     * @param intorno cellule intorno a questa cellula che condizionano il calcolo del nuovo stato
     * @return
     */
    @Override
    public ICell step(ICell cellula, Set<ICell> intorno) {
        if (cellula.isAlive()) {
            long count = intorno.
                    stream().
                    sequential().
                    filter(ICell::isAlive)
                    .count();
            if (count < 2 || count > 3) {
                cellula.changeStato();
                return cellula;
            }
        } else if (!cellula.isAlive()) {
            if (intorno.
                    stream().
                    sequential().
                    filter(ICell::isAlive)
                    .count() == 3) {
                cellula.changeStato();
                return cellula;
            }
        }
        return cellula;
    }
}
