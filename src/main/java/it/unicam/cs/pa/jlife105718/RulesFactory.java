package it.unicam.cs.pa.jlife105718;

public class RulesFactory {

    public Regole<Cellula> getRule(CurrentRulesEnum rule, Campo<?> campo){
        Regole<Cellula> ruleToReturn = null;
        switch (rule){
            case BasicRules:
                ruleToReturn = new BasicRules(campo);
                break;
            case AlternativeRules:
                ruleToReturn= new AlternativeRules(campo);
                break;
        }
        return ruleToReturn;
    }


}


