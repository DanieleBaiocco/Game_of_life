package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PosizioneNumerica implements Posizione {
   private List<Integer> params;

    public PosizioneNumerica(List<Integer> params){
        this.params = params.stream().map(this::changeToPos).collect(Collectors.toList());
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosizioneNumerica that = (PosizioneNumerica) o;
        return dim == that.dim &&
                Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(params, dim);
    }
*/
    @Override
    public Integer getCoordinateI(int i) {
        return this.params.get(i);
    }

    @Override
    public Integer changeToPos(int x) {
    return x;
    }


}
