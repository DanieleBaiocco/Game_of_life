package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class CampoAlfaTest {

    private Campo<PosizioneAlfabetica> campoAlfa;
    private List<Integer> listPos1;
    private List<Integer> listPos2;

    @BeforeEach
    void initCampoAlfa(){
        this.campoAlfa =new Campo2D<PosizioneAlfabetica>(TransitionFactory.getInstance().getTransitionToChar());
        for(int i=0; i<10; i++){
            for (int j=0; j<10; j++) {
                campoAlfa.addAEntry(j,i);
            }
        }
    }

    @Test
     void testAddAEntry(){
        assertThrows(IllegalArgumentException.class, () -> {
            campoAlfa.addAEntry(3,1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            campoAlfa.addAEntry(1,3,4);
            });
        assertTrue(campoAlfa.isIntoMap(9,0));
        assertFalse(campoAlfa.isIntoMap(10,0));
        assertEquals(campoAlfa.getCellulaFromInteger(0,0).getId(),0);
        assertEquals(campoAlfa.getCellulaFromInteger(1,0).getId(),1);
        assertEquals(campoAlfa.getCellulaFromInteger(9,0).getId(),9);
        assertEquals(campoAlfa.getCellulaFromInteger(0,1).getId(),10);
    }

    @Test
    void testGetPosizioneFromCellula(){
        Cellula cellula3 = new Cellula(Stato.MORTO, 3);
        PosizioneAlfabetica posAlph3 =campoAlfa.getPosizioneFromCellula(cellula3);
        assertNotNull(posAlph3);
        assertEquals(posAlph3.getCoordinateI(0), 3);
        assertEquals(posAlph3.getCoordinateI(1), 0);
        Cellula cellula100 = new Cellula(Stato.MORTO, 100);
        assertThrows(NullPointerException.class, () -> {
            campoAlfa.getPosizioneFromCellula(cellula100);
        });
    }


    @Test
     void testGetIntorno(){
        Set<Cellula> intorno = campoAlfa.getIntorno(campoAlfa.getCellulaFromInteger(1,0));
        for(Cellula cell : intorno){
            PosizioneAlfabetica pos =campoAlfa.getPosizioneFromCellula(cell);
            System.out.println(pos.getCoordinateI(0)+" "+ pos.getCoordinateI(1)+"\n");
        }
    }


}