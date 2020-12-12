package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.stream.Collectors;

public class PosizioneVirgolaMobile implements IPosizione{
    private List<Double> params;

    public PosizioneVirgolaMobile(List<Integer> params){
        this.params = params.stream().map(this::changeToPos).collect(Collectors.toList());
    }

    public List<Double> getParams() {
        return params;
    }

    @Override
    public Integer getCoordinateI(int i) {
        double d = this.params.get(i);
        return (int) d;
    }

    @Override
     public Double changeToPos(int x) {
        return (double) x;
    }
}
