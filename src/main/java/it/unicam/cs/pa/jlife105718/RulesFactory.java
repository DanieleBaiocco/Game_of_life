package it.unicam.cs.pa.jlife105718;

import java.util.Map;

public class RulesFactory {
    static private RulesFactory instance;
    private final Regole<Cellula> basicRules;
    private final Regole<Cellula> alternativeRules;
    private final Field<Posizione> field;
    static public RulesFactory getRulesFactory(Field<Posizione> field) {
        if(instance == null) {
            return new RulesFactory(field);
        }
        return instance;
    }
    private RulesFactory(Field<Posizione> field){
        this.field=field;
        basicRules= basicRulesCreation();
        alternativeRules=alternativeRulesCreation();
        }

    private Regole<Cellula> alternativeRulesCreation() {
        return null;
    }

    private Regole<Cellula> basicRulesCreation() {
        return  x -> {
            Map<Posizione, Cellula> vicini= field.getIntorno(x);
            if(x.isAlive()) {
                long count = vicini.values().
                        stream().
                        sequential().
                        filter(Cellula::isAlive)
                        .count();
                if(count<2||count>3){
                    x.changeStato();
                    return x;}
            } else if(!x.isAlive()){
                if ( vicini.values().
                        stream().
                        sequential().
                        filter(Cellula::isAlive)
                        .count() ==3){
                    x.changeStato();
                    return x;
                }
            } return x;
        };
    }

    public Regole<Cellula> getBasicRules() {
        return basicRules;
    }

    public Regole<Cellula> getAlternativeRules() {
        return alternativeRules;
    }
}


