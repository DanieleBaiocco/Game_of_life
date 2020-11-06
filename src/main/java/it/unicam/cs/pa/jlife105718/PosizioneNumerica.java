package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.Objects;

public class PosizioneNumerica implements Posizione {
   private List<Integer> params;
   private int dim;
    public PosizioneNumerica(List<Integer> params, int dim){
        this.params=params;
        this.dim=dim;
    }


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

    @Override
    public Integer getCoordinateI(int i) {
        if(i>=this.dim)
            throw new IllegalArgumentException();
        return this.params.get(i);}
}
