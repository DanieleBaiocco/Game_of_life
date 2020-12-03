package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class CampoAlfaTest {

    private Campo<PosizioneAlfabetica> campoAlfa;
    private List<Integer> listPos1;
    private List<Integer> listPos2;

    @BeforeEach
    void initCampoAlfa(){
        this.campoAlfa =new Campo2D<PosizioneAlfabetica>(TransitionFactory.getInstance().getTransitionToChar());
        campoAlfa.addAEntry(1,3);
    }

    @Test
     void testAddAEntry(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            campoAlfa.addAEntry(1,3,4);
        });
        assertTrue(campoAlfa.isIntoMap(1,3));
        assertFalse(campoAlfa.isIntoMap(3,1));
    }

    @Test
    void testGetPosizioneFromCellula(){
        Cellula cellula0 = new Cellula(Stato.MORTO, 0);
        PosizioneAlfabetica posAlph0 =campoAlfa.getPosizioneFromCellula(cellula0);
        assertNotNull(posAlph0);
        assertEquals(posAlph0.getCoordinateI(0), 1);
        assertEquals(posAlph0.getCoordinateI(1), 3);
        Cellula cellula1 = new Cellula(Stato.MORTO, 1);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            campoAlfa.getPosizioneFromCellula(cellula1);
        });
    }

    @Test
     void testGetIntorno(){



    }


}