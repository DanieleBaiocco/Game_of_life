package it.unicam.cs.pa.jlife105718;

import java.util.Arrays;

public abstract class Posizione implements IPosizione {
    private Object[] params;
    public Posizione(int[] params) {
        this.params = Arrays.stream(params).mapToObj(this::changeToPos).toArray();
    }

public Object[] getParams(){
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
        if(this.getParams().length != other.getParams().length)
            return false;
        int count = 0;
        for (int i=0; i<this.getParams().length; i++){
            if(this.getParams()[i].equals(other.getParams()[i]))
                count++;
        }
        return count == getParams().length;
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
