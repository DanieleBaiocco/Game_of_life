package it.unicam.cs.pa.jlife105718.Model;

import java.util.logging.Logger;

public class MyIdGenerator {
    private static final Logger logger = Logger.getGlobal();
    private int count = -1;
    private static MyIdGenerator instance;
    public static MyIdGenerator getInstance(){
        if(instance ==null)
            instance=new MyIdGenerator();
        return instance;
    }
    public int getIdAndIncrement(){
        logger.finest("ID generated.");
        return ++count;
    }
}
