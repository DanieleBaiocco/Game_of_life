package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class Campo2DAlfaTest {

    private Campo<PosizioneAlfabetica> campoAlfa;

    @BeforeEach
    void initCampoAlfa(){
        this.campoAlfa =new Campo2D<PosizioneAlfabetica>(TransitionFactory.getInstance().getTransitionToChar());
    }

    @Test
    void testAddAEntryAlfa(){
        for(int i=0; i<10; i++){
            for (int j=0; j<10; j++) {
                campoAlfa.addAEntry(j,i);
            }
        }
        assertThrows(IllegalArgumentException.class, () -> {
            campoAlfa.addAEntry(3,1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            campoAlfa.addAEntry(1,3,4);
        });
        assertTrue(campoAlfa.isIntoMap(9,0));
        assertFalse(campoAlfa.isIntoMap(10,0));
    }

    @Test
    void testGetPosizioneFromCellulaAlfa(){
        campoAlfa.addAEntry(3,0);
        Cellula cellula3 = new Cellula(Stato.MORTO, 0);
        PosizioneAlfabetica posAlph3 =campoAlfa.getPosizioneFromCellula(cellula3);
        assertNotNull(posAlph3);
        assertEquals(posAlph3.getCoordinateI(0), 3);
        assertEquals(posAlph3.getCoordinateI(1), 0);
        Cellula cellula900 = new Cellula(Stato.MORTO, 900);
        assertThrows(NullPointerException.class, () -> {
            campoAlfa.getPosizioneFromCellula(cellula900);
        });
    }

    @Test
     void testGetIntornoAlfa(){
        for(int i=0; i<10; i++){
            for (int j=0; j<10; j++) {
                campoAlfa.addAEntry(j,i);
            }
        }
        Set<Cellula> intorno10 = campoAlfa.getIntorno(campoAlfa.getCellulaFromInteger(1,0));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(0,0)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(0,1)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(1,1)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(2,0)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(2,1)));
        Set<Cellula> intorno11 = campoAlfa.getIntorno(campoAlfa.getCellulaFromInteger(1,1));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(0,0)));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(0,1)));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(0,2)));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(1,0)));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(1,2)));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(2,2)));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(2,1)));
        assertTrue(intorno11.contains(campoAlfa.getCellulaFromInteger(2,0)));
    }


    @Test
    void testChangeToPos(){
        List<PosizioneAlfabetica> list = new ArrayList<>();
        for(int i=0; i<30; i++){
            List<Integer> integers = new ArrayList<>();
            integers.add(0);
            integers.add(i);
            list.add(campoAlfa.getTransition().apply(integers));
        }
        assertEquals(list.get(0).getParams().get(1), "A");
        assertEquals(list.get(1).getParams().get(1), "B");
        assertEquals(list.get(25).getParams().get(1), "Z");
        assertEquals(list.get(26).getParams().get(1), "AA");
        assertEquals(list.get(27).getParams().get(1), "AB");
    }

    @Test
    void testChangeStateOfACellula(){
        for(int i=0; i<10; i++){
            for (int j=0; j<10; j++) {
                campoAlfa.addAEntry(j,i);
            }
        }
        campoAlfa.changeStateOfACellula(0,0);
        assertSame(campoAlfa.getCellulaFromInteger(0,0).getStato(), Stato.VIVO);
        campoAlfa.changeStateOfACellula(0,0);
        assertSame(campoAlfa.getCellulaFromInteger(0,0).getStato(), Stato.MORTO);
        campoAlfa.changeStateOfACellula(2,6);
        assertSame(campoAlfa.getCellulaFromInteger(2,6).getStato(), Stato.VIVO);
        assertNotSame(campoAlfa.getCellulaFromInteger(2, 4).getStato(), Stato.VIVO);
    }
}