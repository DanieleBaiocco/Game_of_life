package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;
public class Campo2dTest {
    @Test
    public void initializeMapTest() {
        int ascissa = 2;
        int ordinata = 3;


        BiFunction<Integer, Integer, Posizione> bi = ((x, y) -> {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(x);
            arr.add(y);
            return new PosizioneNumerica(arr,2);
        });
        Campo2d<Posizione> campo2d= new Campo2d<>(ascissa,ordinata,bi);
        Map<Posizione,Cellula> mappa = campo2d.getMappaPosizioneCellula();
        for(int i=0; i<campo2d.getOrdinata();i++) {
            for (int j = 0; j < campo2d.getAscissa(); j++){
               Posizione pos = bi.apply(j,i);
        assertTrue(mappa.containsKey(bi.apply(j,i)));

            }
        }
    }
}

