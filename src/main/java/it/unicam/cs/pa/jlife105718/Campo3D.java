package it.unicam.cs.pa.jlife105718;

import java.util.Set;

public class Campo3D<T extends IPosizione> extends Campo<T>{

    public Campo3D( CurrentTransitionEnum transitionEnum, int value1, int value2, int value3) {
        super(transitionEnum,3, value1,value2,value3);
    }

    public Set<Cellula>  getIntorno(Cellula cellula) {
        //implementazione omessa
        return null;
    }

    @Override
    protected ICampo<T> getInstance() {
        return new Campo3D<>(getTransition(),getValues()[0],getValues()[1],getValues()[2]);
    }

}
