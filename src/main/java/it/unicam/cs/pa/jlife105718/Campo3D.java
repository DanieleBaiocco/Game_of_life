package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Campo3D<T extends IPosizione> extends Campo<T>{
    private int value1;
    private int value2;
    private int value3;
    public Campo3D( Function<List<Integer>,? extends T> transizioneScelta, int value1, int value2, int value3) {
        super(transizioneScelta,value1,value2,value3);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Set<Cellula>  getIntorno(Cellula cellula) {
        //implementazione omessa
        return null;
    }

    @Override
    protected ICampo<T> getInstance() {
        return new Campo3D<>(getTransition(),value1,value2,value3);
    }

}
