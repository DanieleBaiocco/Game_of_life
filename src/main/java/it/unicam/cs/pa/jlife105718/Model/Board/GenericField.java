package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionFactory;
import it.unicam.cs.pa.jlife105718.Model.MyIdGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Implementazione dell'interfaccia IField che fa da raccoglitore di codice e che descrive comportamenti
 * di alto livello di un campo
 * @param <T> parametrizzata rispetto a un tipo di coordinate
 */
public abstract class GenericField<T extends IPosition> implements IField<T> {
    private int[] values;
    private  Map<T, ICell> mappaPosizioneCellula;
    private PositionsEnum transitionEnum;
    private int dim;

    public GenericField(PositionsEnum transitionEnum, int dim, int... values) {
        if(values.length != dim)
            throw new IllegalArgumentException();
        this.transitionEnum=transitionEnum;
        this.mappaPosizioneCellula= new HashMap<>();
        this.values = values;
    }

    /**
     * Overloading del costruttore funzionale per realizzare il pattern creazionale prototype. Questo costruttore viene chiamato
     * quando va in esecuzione il metodo deepCopyClone nelle sottoclassi che estendono questa classe. Permette di ritornare un'istanza
     * che altro non è che la deep copy di un'altra istanza
     */
    public GenericField(IField<T> field) {
        this.transitionEnum=field.getTransitionEnum();
        this.values = field.getValues();
        this.mappaPosizioneCellula= new HashMap<>();
        deepCopyOfMap(field);
    }

    /**
     * Metodo di utilità che, preso un campo, ne prende la mappa e la copia in maniera profonda in un'altra mappa. Infine
     * mette la mappa creata come mappa dell'istanza che chiama questo metodo
     * @param field
     */
    private void deepCopyOfMap(IField<T> field){
        Map<T, ICell> copy = new HashMap<T, ICell>();
        for (Map.Entry<T, ICell> entry : field.getMappaPosizioneCellula().entrySet())
        {
            copy.put(entry.getKey(),
                    getCell(entry.getValue().getStato(), entry.getValue().getId()));
        }
        this.setMappaPosizioneCellula(copy);
    }

    /**
     * Metodo astratto che permette alle sottoclassi di definire un metodo per creare una copia di sè
     */
    @Override
    public abstract IField<T> deepCopyClone();

    /**
     * Cambia lo stato di una cellula all'interno della mappa posizione-cellula
     * @param cellula
     */
    @Override
    public void changeCellula(ICell cellula){
        T pos = getPosizioneFromCellula(cellula);
        this.getMappaPosizioneCellula().get(pos).changeStato();
    }

    public int[] getValues() {
        return values;
    }

    /**
     * Comportamento definito dalle sottoclassi, in quanto si è ancora a un livello troppo alto (è specifico e diverso per ogni dimensione)
     * @param cellula
     * @return
     */
    @Override
    public abstract Set<ICell> getIntorno(ICell cellula);

    /**
     * E' una ricerca all'interno della mappa posizione-cellula della posizione riferita alla cellula passata
     * @param cellula
     * @return
     */
    protected T getPosizioneFromCellula(ICell cellula) {
        Optional<T> posToReturn = mappaPosizioneCellula.entrySet()
                .stream()
                .filter(entry -> cellula.getId() ==(entry.getValue().getId()))
                .map(Map.Entry::getKey)
                .findFirst();
        if (posToReturn.isPresent())
            return posToReturn.get();
        else throw new NullPointerException();
    }

    /**
     * Viene ritoranata la mappa posizione-cellula
     * @return
     */
    @Override
    public Map<T, ICell> getMappaPosizioneCellula() {
        return this.mappaPosizioneCellula;
    }

    /**
     * Viene settata una mappa posizione-cellula nuova
     */
    public void setMappaPosizioneCellula(Map<T, ICell> mappaPosizioneCellula){
        this.mappaPosizioneCellula= mappaPosizioneCellula;
    }

    /**
     * Viene ritorato il tipo di coordinate adottato sotto forma di valore dell'enumerazione PositionsEnum
     * @return
     */
    @Override
    public PositionsEnum getTransition() {
        return this.transitionEnum;
    }

    /**
     * Aggiunge nella mappa posizione-cellula una nuova cellula, il cui stato è messo a false, con associata una posizione. Da notare che
     * non si crea una cellula di un tipo specifico, ma si tratta sempre con l'interfaccia ICell. Questo in quanto è stato introdotto il pattern
     * factory method. Difatti questo metodo classe usa/lavora con una cellula di tipo ICell, demandando la responsabilità di creare questa cellula
     * e definirne il tipo specifico alle classi che estenderanno questa classe. In questo modo questa classe lavora con una ICell senza curarsi di
     * come questa venga creata (supporta il principio di Single Responsability, in quanto le responsabilità vengono disaccoppiate)
     * @param values
     */
    @Override
    public void addAEntry( int ... values) {
        if(values.length == this.values.length && !isIntoMap(values)) {
            T pos  = getPosizioneFromIntegers(values);
            ICell cell =getCell(Stato.MORTO, MyIdGenerator.getInstance().getIdAndIncrement());
            mappaPosizioneCellula.put(pos,cell);
        }
        else throw new IllegalArgumentException();
    }

    /**
     * Mi dice se, date le coordinate che identificano una cellula nella griglia, questa è presente o meno nella mappa posizione - cellula
     */
    private boolean isIntoMap(int ... coordinate){
        T pos = getPosizioneFromIntegers(coordinate);
        return this.mappaPosizioneCellula.containsKey(pos);
    }

    /**
     * Mi dà una cellula, se presente, passati i valori rappresentanti le coordinate con cui è possibile accedere a quella cellula
     */
    public ICell getCellulaFromInteger(int ... values){
        if(isIntoMap(values)){
            T pos= getPosizioneFromIntegers(values);
            return this.mappaPosizioneCellula.get(pos);
       }else
            throw new IllegalArgumentException();
    }

    /**
     * Ritorna il tipo di coordinate utilizzate espresso come un elemento dell'enumerazione PositionsEnum
     * @return
     */
    @Override
    public PositionsEnum getTransitionEnum(){
        return this.transitionEnum;
}

    /**
     * Mi permette di calcolare la posizione di tipo T a partire dalla posizione espressa da valori interi. Questo grazie alla chiamata della
     * PositionFactory che mi permette di ottenere la funzione che permette la trasformazione da posizione espressa come array di interi a posizione
     * espressa come istanza di T
     */

    @Override
    public T getPosizioneFromIntegers(int ... values){
        return (T) PositionFactory.getInstance().getTransition(transitionEnum).apply(values);
    }

    /**
     * Ritorna la posizione espressa in array di interi corrispondente alla cellula passata
     */
    @Override
    public int[] getIntegerFromCellula(ICell cellula){
        T pos = getPosizioneFromCellula(cellula);
        return pos.returnToIntegerCoordinates();
    }

    /**
     * Cambia lo stato di una cellula passata la sua posizione espressa come array di interi
     */
    @Override
    public void changeStateOfACellula(int ... values) {
            T pos = getPosizioneFromIntegers(values);
            getMappaPosizioneCellula().get(pos).changeStato();
    }
}
