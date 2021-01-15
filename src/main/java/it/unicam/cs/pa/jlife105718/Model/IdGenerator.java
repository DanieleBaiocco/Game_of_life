package it.unicam.cs.pa.jlife105718.Model;

public class IdGenerator {
    private int count = -1;
    private static IdGenerator instance;
    public static IdGenerator getInstance(){
        if(instance ==null)
            instance=new IdGenerator();
        return instance;
    }
    public int getIdAndIncrement(){
        return count++;
    }
}
