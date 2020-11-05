package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Campo2dTest {
    @Test
    public void inizializeCelluleTest(){
        int x =10;
        int y=12;
        Campo2d campo2d = new Campo2d(10,12);
        for(int i=0; i<12;i++) {
            for (int j = 0; j < 10; j++){
                assertTrue(campo2d.findCellula(j,i));
            }
        }
        for(Cellula cellula: campo2d.getCellule()){
            assertTrue(cellula.getStato()==Stato.MORTO);
        }

    }

}
