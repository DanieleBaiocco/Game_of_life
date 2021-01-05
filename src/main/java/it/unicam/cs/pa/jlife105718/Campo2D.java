package it.unicam.cs.pa.jlife105718;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Campo2D<T extends IPosizione> extends Campo<T>{

    public Campo2D( CurrentTransitionEnum transition, int value1, int value2) {
        super(transition,2, value1,value2);
    }

    @Override
    protected ICampo<T> getInstance() {
        return new Campo2D<>(getTransition(),getValues()[0], getValues()[1]);
    }

    public Set<Cellula> getIntorno(Cellula cellula) {
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
