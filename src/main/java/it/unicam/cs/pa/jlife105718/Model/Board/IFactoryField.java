package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

/**
 * Interfaccia che implementa il pattern AbstractFactory. Chi implementa questa interfaccia deve fornire dei modi
 * per costruire una griglia a una dimensione, una a due dimensioni e una a tre dimensioni. Quando si invocano i metodi che richiedono l'uso di questa abstract factory ,
 * può esser passata un'implementazione specifica di questa interfaccia. Se uno volesse cambiare il modo di implementare la creazione dei 3 tipi di griglie è libero di farlo:
 * basta che definisce un'altra calsse che implementa questa interfaccia, definisce per ognuno dei tre metodi un meccanismo di creazione
 * di un'istanza di un IField1D, di un IField2D e di un IField3D e passa, nei punti del codice nei quali è richiesta l'abstract factory,
 * un'istanza di quella particolare implementazione dell'abstract factory da lui realizzata. Ciò rispetta il principio Open closed,
 * per cui il codice è aperto alle estensioni e chiuso alle modifiche.L'unica cosa che c'è da cambiare del codice è invocare
 * nei metodi in cui è richiesta un'abstract factory la nuova factory concreta definita.
 */
public interface IFactoryField {

    /**
     * Ritorna un IField1D parametrizzato rispetto a un tipo di cooridinate. Questo deve mantenere l'informazione riferita
     * alla max coordinata dell'unico asse che ha
     */
    <T extends IPosition> IField1D<T> createField1D(PositionsEnum transitionEnum, int value1);

    /**
     * Ritorna un IField2D parametrizzato rispetto a un tipo di cooridinate. Questo deve mantenere l'informazione riferita
     * alla max coordinata del primo e del secondo asse
     */
    <T extends IPosition> IField2D<T> createField2D(PositionsEnum transitionEnum, int value1, int value2);

    /**
     * Ritorna un IField3D parametrizzato rispetto a un tipo di cooridinate. Questo deve mantenere l'informazione riferita
       alla max coordinata del primo e del secondo asse
     */
    <T extends IPosition> IField3D<T> createField3D(PositionsEnum transitionEnum, int value1, int value2, int value3);
}
