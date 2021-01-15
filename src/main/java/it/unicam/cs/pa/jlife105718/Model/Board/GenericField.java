package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionFactory;
import it.unicam.cs.pa.jlife105718.Model.IdGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    public GenericField(IField<T> field) {
        this.transitionEnum=field.getTransitionEnum();
        this.values = field.getValues();
        this.mappaPosizioneCellula= new HashMap<>();
        deepCopyOfMap(field);
    }

    private void deepCopyOfMap(IField<T> field){
        Map<T, ICell> copy = new HashMap<T, ICell>();
        for (Map.Entry<T, ICell> entry : field.getMappaPosizioneCellula().entrySet())
        {
            copy.put(entry.getKey(),
                    getCell(entry.getValue().getStato(), entry.getValue().getId()));
        }
        this.setMappaPosizioneCellula(copy);
    }

    @Override
    public abstract IField<T> deepCopyClone();

    @Override
    public void changeCellula(ICell cellula){
        T pos = getPosizioneFromCellula(cellula);
        this.getMappaPosizioneCellula().get(pos).changeStato();
    }

    public int[] getValues() {
        return values;
    }

    @Override
    public abstract Set<ICell> getIntorno(ICell cellula);

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

    @Override
    public Map<T, ICell> getMappaPosizioneCellula() {
        return this.mappaPosizioneCellula;
    }

    public void setMappaPosizioneCellula(Map<T, ICell> mappaPosizioneCellula){
        this.mappaPosizioneCellula= mappaPosizioneCellula;
    }
    @Override
    public PositionsEnum getTransition() {
        return this.transitionEnum;
    }

    @Override
    public void addAEntry( int ... values) {
        if(values.length == this.values.length && !isIntoMap(values)) {
            T pos  = getPosizioneFromInteger(values);
            ICell cell =getCell(Stato.MORTO, IdGenerator.getInstance().getIdAndIncrement());
            mappaPosizioneCellula.put(pos,cell);
        }
        else throw new IllegalArgumentException();
    }

    private boolean isIntoMap(int ... coordinate){
        T pos = getPosizioneFromInteger(coordinate);
        return this.mappaPosizioneCellula.containsKey(pos);
    }

    public ICell getCellulaFromInteger(int ... values){
        if(isIntoMap(values)){
            T pos= getPosizioneFromInteger(values);
            return this.mappaPosizioneCellula.get(pos);
       }else
            throw new IllegalArgumentException();
    }

    private T getPosizioneFromInteger( int ... values){
        return (T) PositionFactory.getInstance().getTransition(transitionEnum).apply(values);
    }
@Override
    public PositionsEnum getTransitionEnum(){
        return this.transitionEnum;
}

    @Override
    public T getPosizioneFromIntegers(int ... values){
        return (T) PositionFactory.getInstance().getTransition(transitionEnum).apply(values);
    }
@Override
    public int[] getIntegerFromCellula(ICell cellula){
        T pos = getPosizioneFromCellula(cellula);
        return pos.getCoordinateI();
    }

    @Override
    public void changeStateOfACellula(int ... values) {
            T pos = getPosizioneFromInteger(values);
            getMappaPosizioneCellula().get(pos).changeStato();
    }
}
