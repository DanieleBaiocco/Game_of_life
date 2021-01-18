package it.unicam.cs.pa.jlife105718.Model;

import it.unicam.cs.pa.jlife105718.Model.Board.IField;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;

import java.util.function.Supplier;

/**
 * Classe responsabile di fornire dei metodi di utilità per la creazione degli elementi che compongono
 * la griglia
 */
public class Utility {

    /**
     * Ritorna un elemento dell'enumerazione che rappresenta il tipo di coordinate che verrà usato nella griglia
     * @param positionChoosed la stringa rappresentante il tipo di coordinate richiesto
     * @return il tipo di cooridinate corrispondente alla stringa in input
     */
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

    /**
     * Ritorna un elemento dell'enumerazione che rappresenta il tipo di regola che verrà usato nela griglia
     * @param ruleChoosed la stringa rappresentante il tipo di regola richiesto
     * @return il tipo di regola corrispondente alla stringa in input
     */
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

    /**
     * Ritorna un tipo di campo (1D, 2D o 3D) da una stringa che rappresenta la dimensione con la quale si vuole creare
     * il campo e dei Supplier che ritornano il campo da creare
     * @param dimensionChoosed la stringa rappresentante la dimensione con la quale si vuole creare il campo
     * @param firstFunc serve a creare un campo 1D
     * @param secondFunc serve a creare un campo 2D
     * @param thirdFunc serve a creare un campo 3D
     * @return un tipo di campo che rappresenta la griglia
     */
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
