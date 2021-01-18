package it.unicam.cs.pa.jlife105718.Model.PositionTest;

import it.unicam.cs.pa.jlife105718.Model.Position.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosizioneNumericaInteraTest {
    List<PosizioneNumericaIntera> list ;
    @BeforeEach
    void init(){
        list = new ArrayList<>();
        for(int i=0; i<30; i++){
            list.add((PosizioneNumericaIntera) PositionFactory.getInstance().getTransition(PositionsEnum.Interno).apply(new int[]{0, i}));
        }
    }

    @Test
    void testGetCoordinateI(){
        List<Integer> listInteger = new ArrayList<>();
        for(int i=0; i<30; i++){
            listInteger.add(i);
        }
        System.out.println(list.get(0).returnToIntegerCoordinates()[1]);
        assertEquals(list.get(0).returnToIntegerCoordinates()[1], listInteger.get(0));
        assertEquals(list.get(1).returnToIntegerCoordinates()[1], listInteger.get(1));
        assertEquals(list.get(25).returnToIntegerCoordinates()[1], listInteger.get(25));
        assertEquals(list.get(26).returnToIntegerCoordinates()[1], listInteger.get(26));
        assertEquals(list.get(27).returnToIntegerCoordinates()[1], listInteger.get(27));
    }

    @Test
    void testChangeToPos(){
        assertEquals(list.get(0).getParams()[1], 0);
        assertEquals(list.get(1).getParams()[1], 1);
        assertEquals(list.get(25).getParams()[1], 25);
        assertEquals(list.get(26).getParams()[1], 26);
        assertEquals(list.get(27).getParams()[1], 27);
    }
}
