package it.unicam.cs.pa.jlife105718.Model;

import it.unicam.cs.pa.jlife105718.Model.Board.IField;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;

import java.util.function.Supplier;

public class Utility {

    public static PositionsEnum switchOnPositionChoosed(String positionChoosed){
        PositionsEnum transitionEnum;
        switch (positionChoosed){
            case "Alfabetico":
                transitionEnum = PositionsEnum.Alfabetico;
                break;
            case "Numerico":
                transitionEnum = PositionsEnum.Interno;
                break;
            case "Virgola Mobile" :
                transitionEnum = PositionsEnum.VirgolaMobile;
                break;
            default:
                transitionEnum = null;
        }
        return transitionEnum;
    }

    public static RulesEnum switchOnRuleChoosed(String ruleChoosed) {
        RulesEnum ruleToReturn;
        switch (ruleChoosed){
            case "Standard":
                ruleToReturn =  RulesEnum.BasicRules;
                break;
            case "Alternativa1":
                ruleToReturn = RulesEnum.AlternativeRules1;
                break;
            case "Alternativa2":
                ruleToReturn = RulesEnum.AlternativeRules2;
                break;
            default:
                ruleToReturn = null;
        }
        return ruleToReturn;
    }

    public static <T extends IPosition> IField<T> switchOnDimensionChoosed(String dimensionChoosed, Supplier<IField<T>> firstFunc,
                                                                           Supplier<IField<T>> secondFunc, Supplier<IField<T>> thirdFunc){
        IField<T> fieldToReturn;
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
