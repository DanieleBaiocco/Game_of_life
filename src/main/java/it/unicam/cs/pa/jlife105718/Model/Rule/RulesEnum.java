package it.unicam.cs.pa.jlife105718.Model.Rule;

/**
 * Enumerazione che rappresenta i vari tipi di regole nel programma. Se una classe ha come attributo un elemento
 * di questa enumerazione, può accedere all'effettiva regola chiamando il metodo getRule(RulesEnum enum) della
 * RulesFactory
 */
public enum RulesEnum {
    BasicRules, AlternativeRules1, AlternativeRules2;
}
