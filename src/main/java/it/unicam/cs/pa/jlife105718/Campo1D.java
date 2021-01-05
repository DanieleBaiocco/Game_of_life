package it.unicam.cs.pa.jlife105718;

import java.util.Set;

public class Campo1D<T extends IPosizione> extends Campo<T>{
    public Campo1D(CurrentTransitionEnum currentTransitionEnum , int value1) {
        super(currentTransitionEnum,1, value1);
    }

    @Override
    public Set<Cellula> getIntorno(Cellula cellula) {
        //implementazione omessa
        return null;
    }

    @Override
    protected ICampo<T> getInstance() {
        return new Campo1D<>(getTransition(),getValues()[0]);
    }


}
