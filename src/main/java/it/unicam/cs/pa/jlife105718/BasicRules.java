package it.unicam.cs.pa.jlife105718;

import java.util.Set;

public class BasicRules implements Regole<Cellula>{

    @Override
    public Cellula step(Cellula cellula, Set<Cellula> intorno) {
        if (cellula.isAlive()) {
            long count = intorno.
                    stream().
                    sequential().
                    filter(Cellula::isAlive)
                    .count();
            if (count < 2 || count > 3) {
                cellula.changeStato();
                return cellula;
            }
        } else if (!cellula.isAlive()) {
            if (intorno.
                    stream().
                    sequential().
                    filter(Cellula::isAlive)
                    .count() == 3) {
                cellula.changeStato();
                return cellula;
            }
        }
        return cellula;
    }
}
