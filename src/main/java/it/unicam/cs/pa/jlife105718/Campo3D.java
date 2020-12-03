package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Campo3D<T extends IPosizione> extends Campo<T>{

    public Campo3D( Function<List<Integer>, T> transizioneScelta) {
        super(transizioneScelta,3);
    }



    public Set<Cellula>  getIntorno(Cellula cellula) {
        //implementazione omessa
        return null;
    }

}
