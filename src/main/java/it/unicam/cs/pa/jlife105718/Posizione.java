package it.unicam.cs.pa.jlife105718;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Posizione implements IPosizione {
    private List<Object> params;
    public Posizione(List<Integer> params) {
        this.params = params.stream().map(this::changeToPos).collect(Collectors.toList());
    }

public List<Object> getParams(){
        return params;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Posizione other = (Posizione) obj;
        if(this.getParams().size() != other.getParams().size())
            return false;
        int count = 0;
        for (int i=0; i<this.getParams().size(); i++){
            if(this.getParams().get(i).equals(other.getParams().get(i)))
                count++;
        }
        return count == getParams().size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for(Object param : getParams()){
            result= getResultForHash(param, result, prime);
        }
        return result;
    }

    protected abstract int getResultForHash(Object obj, int result, int prime);
}
