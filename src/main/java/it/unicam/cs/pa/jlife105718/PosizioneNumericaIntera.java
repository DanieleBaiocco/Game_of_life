package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.stream.Collectors;

public class PosizioneNumericaIntera implements IPosizione {
   private List<Integer> params;

    public PosizioneNumericaIntera(List<Integer> params){
        this.params = params.stream().map(this::changeToPos).collect(Collectors.toList());
    }

    public List<Integer> getParams() {
        return params;
    }

    @Override
    public Integer changeToPos(int x) {
    return x;
    }

    @Override
    public Integer getCoordinateI(int i) {
        return this.params.get(i);
    }

}
