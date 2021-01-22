package it.unicam.cs.pa.jlife105718.Model.Field2DTest;

import it.unicam.cs.pa.jlife105718.Model.Board.GenericField;
import it.unicam.cs.pa.jlife105718.Model.Board.MyField2D;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.MyCell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneAlfabetica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class MyField2DTest {

    private GenericField<PosizioneAlfabetica> campoAlfa;

    @BeforeEach
    void initCampoAlfa(){
        this.campoAlfa =new MyField2D<>(PositionsEnum.Alfabetico,10,10);
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
        assertDoesNotThrow(()->{
            campoAlfa.getCellulaFromInteger(9,0);
        });
        assertThrows(IllegalArgumentException.class,()->{
            campoAlfa.getCellulaFromInteger(10,0);
        });
    }

    @Test
    void testGetPosizioneFromCellulaAlfa(){
        campoAlfa.addAEntry(3,0);
        MyCell cellula3 = new MyCell(Stato.MORTO, 100);
        int[] posAlph3 =campoAlfa.getIntegerFromCellula(cellula3);
        assertNotNull(posAlph3);
        assertEquals(posAlph3[0], 3);
        assertEquals(posAlph3[1], 0);
        MyCell cellula900 = new MyCell(Stato.MORTO, 900);
        assertThrows(NullPointerException.class, () -> {
            campoAlfa.getIntegerFromCellula(cellula900);
        });
    }

    @Test
     void testGetIntorno(){
        for(int i=0; i<10; i++){
            for (int j=0; j<10; j++) {
                campoAlfa.addAEntry(j,i);
            }
        }
        Set<ICell> intorno10 = campoAlfa.getIntorno(campoAlfa.getCellulaFromInteger(1,0));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(0,0)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(0,1)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(1,1)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(2,0)));
        assertTrue(intorno10.contains(campoAlfa.getCellulaFromInteger(2,1)));
        Set<ICell> intorno11 = campoAlfa.getIntorno(campoAlfa.getCellulaFromInteger(1,1));
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

    @Test
    void cloneTest() throws CloneNotSupportedException {
        campoAlfa.addAEntry(0,0);
        campoAlfa.addAEntry(0,1);
        MyField2D<PosizioneAlfabetica> campoCopy = (MyField2D<PosizioneAlfabetica>) campoAlfa.deepCopyClone();
        campoAlfa.addAEntry(0,2);
        assertThrows(IllegalArgumentException.class, ()->
                 campoCopy.getCellulaFromInteger(0,2));
        assertEquals(campoAlfa.getCellulaFromInteger(0,0).getId(),campoCopy.getCellulaFromInteger(0,0).getId());
        HashMap<PosizioneAlfabetica, ICell> map = (HashMap<PosizioneAlfabetica, ICell>) campoCopy.getMappaPosizioneCellula();
        assertTrue(map.containsValue(new MyCell(Stato.VIVO,101)));
        assertTrue(map.containsValue(new MyCell(Stato.VIVO,102)));
        assertFalse(map.containsValue(new MyCell(Stato.VIVO,103)));
    }
}