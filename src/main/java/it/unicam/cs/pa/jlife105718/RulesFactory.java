package it.unicam.cs.pa.jlife105718;

public class RulesFactory {
private static RulesFactory instance;

    public static RulesFactory getInstance() {
        if(instance==null)
            instance= new RulesFactory();
        return instance;
    }

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


