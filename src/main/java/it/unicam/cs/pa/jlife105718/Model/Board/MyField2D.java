package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyField2D<T extends IPosition> extends MyField<T> implements IField2D<T>{

    public MyField2D(PositionsEnum transition, int value1, int value2) {
        super(transition,2,  value1,value2);
    }

    public MyField2D(IField<T> field) {
        super(field);
    }

    @Override
    public IField<T> deepCopyClone() {
        return new MyField2D<>(this);
    }

    public Set<ICell> getIntorno(ICell cellula) {
        T pos =getPosizioneFromCellula(cellula);
        int[] result = pos.getCoordinateI();
        int firstCoordinate = result[0];
        int secondCoordinate = result[1];
        return getMappaPosizioneCellula().keySet()
                .stream()
                .filter(p->!p.equals(pos))
                .filter(isInTheIntorno(firstCoordinate,secondCoordinate))
                .map(p->getMappaPosizioneCellula().get(p))
                .collect(Collectors.toSet());
    }

    private Predicate<? super T> isInTheIntorno( int first, int second){
    return po->{
    int[] result = po.getCoordinateI();
    int firstofx = result[0];
    int secondofx = result[1];
    boolean secondCondition = (secondofx==second-1 || secondofx==second || secondofx== second+1);
    return (firstofx==first-1 && secondCondition)||
            (firstofx==first && secondCondition) ||
            (firstofx==first+1 && secondCondition);
    };
   }


}
