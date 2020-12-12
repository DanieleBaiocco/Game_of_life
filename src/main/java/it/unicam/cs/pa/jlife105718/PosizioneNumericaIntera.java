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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        PosizioneNumericaIntera other = (PosizioneNumericaIntera) o;
        if(this.getParams().size() != other.params.size())
            return false;
        int count = 0;
        for (int i=0; i<this.params.size(); i++){
            if(this.params.get(i).equals(other.getParams().get(i)))
                count++;
        }
        return count == params.size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for(Integer param : params){
            result = prime * result + param;
        }
        return result;
    }
}
