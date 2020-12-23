package it.unicam.cs.pa.jlife105718;

public class RulesFactory {

    public Regole<Cellula> getRule(CurrentRulesEnum rule){
        Regole<Cellula> ruleToReturn = null;
        switch (rule){
            case BasicRules:
                ruleToReturn = new BasicRules();
                break;
            case AlternativeRules:
                ruleToReturn= new AlternativeRules();
                break;
        }
        return ruleToReturn;
    }


}


