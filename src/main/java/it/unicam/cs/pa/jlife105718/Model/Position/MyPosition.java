package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.Arrays;

public abstract class MyPosition implements IPosition {
    private Object[] params;
    public MyPosition(int[] params) {
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
        MyPosition other = (MyPosition) obj;
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
