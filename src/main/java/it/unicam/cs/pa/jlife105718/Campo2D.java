package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Campo2D<T extends IPosizione> extends Campo<T>{

    public Campo2D( Function<List<Integer>,? extends T> transition) {
        super(transition,2);
    }

    @Override
    protected ICampo<T> getInstance(Function<List<Integer>, ? extends T> function) {
        return new Campo2D<>(function);
    }

    public Set<Cellula> getIntorno(Cellula cellula) {
        T pos =getPosizioneFromCellula(cellula);
        int firstCoordinate = pos.getCoordinateI(0);
        int secondCoordinate = pos.getCoordinateI(1);
        return getMappaPosizioneCellula().keySet()
                .stream()
                .filter(p->!p.equals(pos))
                .filter(isInTheIntorno(firstCoordinate,secondCoordinate))
                .map(p->getMappaPosizioneCellula().get(p))
                .collect(Collectors.toSet());
    }

    private Predicate<? super T> isInTheIntorno( int first, int second){
    return po->{
    int firstofx = po.getCoordinateI(0);
    int secondofx = po.getCoordinateI(1);
    boolean secondCondition = (secondofx==second-1 || secondofx==second || secondofx== second+1);
    return (firstofx==first-1 && secondCondition)||
            (firstofx==first && secondCondition) ||
            (firstofx==first+1 && secondCondition);
    };
   }


}
