package it.unicam.cs.pa.jlife105718.Model.Rule;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;

import java.util.Set;

public class BasicRules implements Rule<ICell> {

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
