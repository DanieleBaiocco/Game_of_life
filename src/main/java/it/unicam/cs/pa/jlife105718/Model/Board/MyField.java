package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.MyCell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

/**
 * Classe responsabile di definire il tipo di cellula che le classi che la estendono utilizzeranno. Tutte le
 * classi che estendono questa classe useranno come cella la MyCell, una particolare implementazione di ICell.
 * Si può fare un altro set creazionale di campi 1D, 2D e 3D che utilizzi un altro tipo di implementazione di cellula
 * senza troppi problemi: basta creare un'altra classe astratta simile a questa che implementi IField e che fornisca nel metodo
 * getCell un altro tipo di implementazione di cellula. A quel punto basta creare tre classi, tutte e tre che estendono quella nuova
 * classe e che implementano ognuna l'interfaccia riferita alla loro dimensione (la prima implementerà IField1D, la seconda IField2D,
 * la terza IField3D). Infine creando una classe che implementa l'abstract factory in cui, all'interno si creano campi riferiti
 * alle nuove 3 classi, si ha un nuovo set di griglie creabili, senza che il resto del codice si sia accorto del cambiamento
 */
public abstract class MyField<T extends IPosition> extends GenericField<T>{
    public MyField(PositionsEnum transitionEnum, int dim, int... values) {
        super(transitionEnum, dim, values);
    }

    public MyField(IField<T> field) {
        super(field);
    }

    /**
     * Ritorna un'istanza di MyCell
     */
    public ICell getCell(Stato stato, int id){
        return new MyCell(stato, id);
    };
}
