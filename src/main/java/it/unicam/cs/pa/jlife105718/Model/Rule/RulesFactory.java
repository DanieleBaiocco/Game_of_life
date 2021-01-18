package it.unicam.cs.pa.jlife105718.Model.Rule;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;

/**
 * Classe con la responsabilità creare un tipo di regola passato un elemento dell'enumerazione
 * che rappresenta quel tipo di regola. Implementa il pattern singleton per permettere di esser chiamata
 * in qualsiasi punto del programma e il pattern factory per levare alle classi che implementano Rule<ICell>
 * la responsabilità di esser create.
 */
public class RulesFactory {
private static RulesFactory instance;

    /**
     * Ritorna sempre la stessa istanza della classe RulesFactory
     */
    public static RulesFactory getInstance() {
        if(instance==null)
            instance= new RulesFactory();
        return instance;
    }

    /**
     * Mi ritorna una classe che implementa l'interfaccia Rule<ICell> e che quindi che implementa una regola
     * con la quale calcolare la Next Generation, a partire da un elemento dell'enumerazione corrispondente
     * alla specifica regola.
     */
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


