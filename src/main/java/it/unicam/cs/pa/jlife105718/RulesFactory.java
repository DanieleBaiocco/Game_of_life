package it.unicam.cs.pa.jlife105718;

import java.util.Set;

public class RulesFactory {
    static private RulesFactory instance;
    private final Regole<Cellula> basicRules;
    private final Regole<Cellula> alternativeRules;
    private final ICampo<?> ICampo;
    static public RulesFactory getRulesFactory(ICampo<?> ICampo) {
        if(instance == null) {
            instance= new RulesFactory(ICampo);
        }
        return instance;
    }
    private RulesFactory(ICampo<?> ICampo){
        this.ICampo = ICampo;
        basicRules= basicRulesCreation();
        alternativeRules=alternativeRulesCreation();
        }

    private Regole<Cellula> alternativeRulesCreation() {
        return null;
    }

    private  Regole<Cellula> basicRulesCreation() {
        synchronized (this.getBasicRules()){
        return  x -> {
            Set<Cellula> vicini= ICampo.getIntorno(x);
            if(x.isAlive()) {
                long count = vicini.
                        stream().
                        sequential().
                        filter(Cellula::isAlive)
                        .count();
                if(count<2||count>3){
                    this.getBasicRules().wait();
                    x.changeStato();
                    return x;}
            } else if(!x.isAlive()){
                if ( vicini.
                        stream().
                        sequential().
                        filter(Cellula::isAlive)
                        .count() ==3)
                {
                    this.getBasicRules().wait();
                    x.changeStato();
                    return x;
                }
            } return x;
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


