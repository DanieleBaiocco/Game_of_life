package it.unicam.cs.pa.jlife105718;

import java.util.function.Supplier;

public class Utility {

    public static CurrentTransitionEnum switchOnPositionChoosed(String positionChoosed){
        CurrentTransitionEnum transitionEnum;
        switch (positionChoosed){
            case "Alfabetico":
                transitionEnum = CurrentTransitionEnum.Alfabetico;
                break;
            case "Numerico":
                transitionEnum = CurrentTransitionEnum.Interno;
                break;
            case "Virgola Mobile" :
                transitionEnum = CurrentTransitionEnum.VirgolaMobile;
                break;
            default:
                transitionEnum = null;
        }
        return transitionEnum;
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

    public static <T extends IPosizione> ICampo<T> switchOnDimensionChoosed(String dimensionChoosed, Supplier<ICampo<T>> firstFunc,
                                                     Supplier<ICampo<T>> secondFunc, Supplier<ICampo<T>> thirdFunc){
        ICampo<T> fieldToReturn;
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
