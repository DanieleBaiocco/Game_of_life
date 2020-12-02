package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Campo1D<T extends IPosizione> extends Campo<T>{
    public Campo1D(Function<List<Integer>, ? extends T> transition) {
        super(transition);
    }

    @Override
    public void addAEntry(List<Integer> position, int dim) {
       super.addAEntry(position,1);
    }

    @Override
    public Set<Cellula> getIntorno(Cellula cellula) {
        //implementazione omessa
        return null;
    }
}
