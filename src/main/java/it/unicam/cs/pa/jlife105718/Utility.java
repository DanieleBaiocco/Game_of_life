package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Utility {

    public static Function<List<Integer>, ? extends IPosizione> switchOnPositionChoosed(String positionChoosed){
        Function<List<Integer>, ? extends IPosizione> functionToReturn;
        switch (positionChoosed){
            case "Alfabetico":
                functionToReturn = TransitionFactory.getInstance().getTransitionToChar();
                break;
            case "Numerico":
                functionToReturn = TransitionFactory.getInstance().getTransitionToInteger();
                break;
            case "Virgola Mobile" :
                functionToReturn = TransitionFactory.getInstance().getTransitionToDouble();
                break;
            default:
                functionToReturn = null;
        }
        return functionToReturn;
    }

    public static CurrentRulesEnum switchOnRuleChoosed(String ruleChoosed) {
        CurrentRulesEnum ruleToReturn;
        switch (ruleChoosed){
            case "Standard":
                ruleToReturn =  CurrentRulesEnum.BasicRules;
                break;
            case "Alternativa1":
                ruleToReturn = CurrentRulesEnum.AlternativeRules1;
                break;
            case "Alternativa2":
                ruleToReturn = CurrentRulesEnum.AlternativeRules2;
                break;
            default:
                ruleToReturn = null;
        }
        return ruleToReturn;
    }

    public static ICampo<?> switchOnDimensionChoosed(String dimensionChoosed, Supplier<ICampo<?>> firstFunc,
                                                     Supplier<ICampo<?>> secondFunc, Supplier<ICampo<?>> thirdFunc){
        ICampo<?> fieldToReturn;
        switch(dimensionChoosed){
            case "1": {
               fieldToReturn = firstFunc.get();
                break;
            }
            case "2": {
                fieldToReturn = secondFunc.get();
                break;
            }
            case "3": {
                fieldToReturn = thirdFunc.get();
                break;
            }
            default:
                fieldToReturn=null;
        }
        return fieldToReturn;
    }
}
