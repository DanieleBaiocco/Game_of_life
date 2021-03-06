package it.unicam.cs.pa.jlife105718.Model.PositionTest;

import it.unicam.cs.pa.jlife105718.Model.Position.PositionFactory;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneAlfabetica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosizioneAlfabeticaTest {
    List<PosizioneAlfabetica> list ;
    @BeforeEach
    void init(){
        list = new ArrayList<>();
        for(int i=0; i<30; i++){
            list.add((PosizioneAlfabetica) PositionFactory.getInstance().getTransition(PositionsEnum.Alfabetico).apply(new int[]{0, i}));
        }
    }
    @Test
    void testGetCoordinateI(){
        List<Integer> listInteger = new ArrayList<>();
        for(int i=0; i<30; i++){
            listInteger.add(i);  }
            System.out.println(list.get(0).returnToIntegerCoordinates()[1]);
            assertEquals(list.get(0).returnToIntegerCoordinates()[1], listInteger.get(0));
            assertEquals(list.get(1).returnToIntegerCoordinates()[1], listInteger.get(1));
            assertEquals(list.get(25).returnToIntegerCoordinates()[1], listInteger.get(25));
            assertEquals(list.get(26).returnToIntegerCoordinates()[1], listInteger.get(26));
            assertEquals(list.get(27).returnToIntegerCoordinates()[1], listInteger.get(27));
    }

    @Test
    void testChangeToPos(){
        assertEquals(list.get(0).getParams()[1], "A");
        assertEquals(list.get(1).getParams()[1], "B");
        assertEquals(list.get(25).getParams()[1], "Z");
        assertEquals(list.get(26).getParams()[1], "AA");
        assertEquals(list.get(27).getParams()[1], "AB");
    }
}
