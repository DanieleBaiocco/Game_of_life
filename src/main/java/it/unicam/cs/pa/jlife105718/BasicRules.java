package it.unicam.cs.pa.jlife105718;

import java.util.Set;

public class BasicRules implements Regole<Cellula>{
    private final ICampo<?> campo;

    public BasicRules(ICampo<?> campo) {
        this.campo= campo;
    }

    @Override
    public Cellula step(Cellula cellula) {
        Set<Cellula> vicini = campo.getIntorno(cellula);
        if (cellula.isAlive()) {
            long count = vicini.
                    stream().
                    sequential().
                    filter(Cellula::isAlive)
                    .count();
            if (count < 2 || count > 3) {
                cellula.changeStato();
                return cellula;
            }
        } else if (!cellula.isAlive()) {
            if (vicini.
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
