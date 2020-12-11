package it.unicam.cs.pa.jlife105718;

import java.util.Set;

public class RulesFactory {
    static private RulesFactory instance;
    private final Regole<Cellula> basicRules;
    private final Regole<Cellula> alternativeRules;
    private final ICampo<?> campo;
    static public RulesFactory getRulesFactory(ICampo<?> campo) {
        if(instance == null) {
            instance= new RulesFactory(campo);
        }
        return instance;
    }
    private RulesFactory(ICampo<?> campo){
        this.campo = campo;
        basicRules= basicRulesCreation();
        alternativeRules=alternativeRulesCreation();
        }

    private Regole<Cellula> alternativeRulesCreation() {
        return null;
    }

    private  Regole<Cellula> basicRulesCreation() {
        synchronized (campo) {
            return x -> {
                Set<Cellula> vicini = campo.getIntorno(x);
                if (x.isAlive()) {
                    long count = vicini.
                            stream().
                            sequential().
                            filter(Cellula::isAlive)
                            .count();
                    if (count < 2 || count > 3) {
                        x.changeStato();
                        wait();
                        return x;
                    }
                } else if (!x.isAlive()) {
                    if (vicini.
                            stream().
                            sequential().
                            filter(Cellula::isAlive)
                            .count() == 3) {

                        x.changeStato();
                        return x;
                    }
                }
                return x;
            };
        }
    }

    public Regole<Cellula> getBasicRules() {
        return basicRules;
    }
    public Regole<Cellula> getAlternativeRules() {
        return alternativeRules;
    }

}


