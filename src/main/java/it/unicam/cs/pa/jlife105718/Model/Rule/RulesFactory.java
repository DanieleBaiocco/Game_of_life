package it.unicam.cs.pa.jlife105718.Model.Rule;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;

public class RulesFactory {
private static RulesFactory instance;

    public static RulesFactory getInstance() {
        if(instance==null)
            instance= new RulesFactory();
        return instance;
    }

    public Rule<ICell> getRule(RulesEnum rule){
        Rule<ICell> ruleToReturn = null;
        switch (rule){
            case BasicRules:
                ruleToReturn = new BasicRules();
                break;
            case AlternativeRules1:
                ruleToReturn= new AlternativeRules1();
                break;
            case AlternativeRules2:
                ruleToReturn = new AlternativeRules2();
        }
        return ruleToReturn;
    }


}


