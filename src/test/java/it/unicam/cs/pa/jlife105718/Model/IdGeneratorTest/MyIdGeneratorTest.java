package it.unicam.cs.pa.jlife105718.Model.IdGeneratorTest;

import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.MyCell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import static org.junit.jupiter.api.Assertions.*;
import it.unicam.cs.pa.jlife105718.Model.MyIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyIdGeneratorTest {
    ICell cell1;
    ICell cell2;
    ICell cell3;
    ICell cell4;
    ICell cell5;

    @BeforeEach
    void init(){
        MyIdGenerator.getInstance().resetCount();
        cell1 = new MyCell(Stato.MORTO, MyIdGenerator.getInstance().getIdAndIncrement());
        cell2 = new MyCell(Stato.VIVO, MyIdGenerator.getInstance().getIdAndIncrement());
        cell3 = new MyCell(Stato.MORTO, MyIdGenerator.getInstance().getIdAndIncrement());
        cell4 = new MyCell(Stato.MORTO, 2);
        cell5 = new MyCell(Stato.VIVO, MyIdGenerator.getInstance().getIdAndIncrement());
    }

    @Test
    void getIdAndIncrement(){
        assertNotEquals(cell1,cell2);
        assertEquals(cell3,cell4);
        assertEquals(cell1.getId(),0);
        assertEquals(cell2.getId(),1);
        assertEquals(cell3.getId(),2);
        assertEquals(cell5.getId(),3);
    }
}
