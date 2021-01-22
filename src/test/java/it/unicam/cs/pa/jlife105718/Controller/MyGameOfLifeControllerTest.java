package it.unicam.cs.pa.jlife105718.Controller;

import it.unicam.cs.pa.jlife105718.Model.Board.MyField2D;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.MyCell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.MyIdGenerator;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyGameOfLifeControllerTest {
    IController<?> controller;
    @BeforeEach
    void init(){
        controller = new MyGameOfLifeController<>(new MyField2D<>(PositionsEnum.Alfabetico,5,5),RulesEnum.BasicRules,null);
    }

    @Test
    void testAddAEntry(){
        MyIdGenerator.getInstance().resetCount();
        for(int i=0; i<5; i++)
            for (int j=0; j<5; j++){
                controller.addAEntry(j,i);
            }
        assertThrows(IllegalArgumentException.class, () -> {
            controller.addAEntry(3,1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            controller.addAEntry(1,3,4);
        });
        assertThrows(IllegalArgumentException.class,()->{
            controller.getCellulaFromInteger(10,0);
        });
    }

    @Test
    void testColorateDecolorateACellula(){
        MyIdGenerator.getInstance().resetCount();
        for(int i=0; i<5; i++)
            for (int j=0; j<5; j++){
                controller.addAEntry(j,i);
            }
        controller.colorateDecolorateACellula(0,0);
        assertEquals(Stato.VIVO, controller.getCellulaFromInteger(0, 0).getStato());
        controller.colorateDecolorateACellula(0,0);
        assertEquals(Stato.MORTO, controller.getCellulaFromInteger(0, 0).getStato());
        controller.colorateDecolorateACellula(4,0);
        assertEquals(Stato.VIVO, controller.getCellulaFromInteger(4, 0).getStato());
    }

    @Test
    void calculateNextGen(){
        MyIdGenerator.getInstance().resetCount();
        for(int i=0; i<5; i++)
            for (int j=0; j<5; j++){
                controller.addAEntry(j,i);
            }
        controller.colorateDecolorateACellula(1,0);
        controller.colorateDecolorateACellula(3,2);
        controller.colorateDecolorateACellula(1,3);
        controller.colorateDecolorateACellula(4,4);
        controller.colorateDecolorateACellula(0,4);
        controller.colorateDecolorateACellula(3,1);
        controller.colorateDecolorateACellula(2,2);
        controller.colorateDecolorateACellula(4,0);
        controller.colorateDecolorateACellula(2,0);
        controller.nextGen();
        assertEquals(controller.getCellulaFromInteger(0,0).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(1,0).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(2,0).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(3,0).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(4,0).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(0,1).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(1,1).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(2,1).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(3,1).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(4,1).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(0,2).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(1,2).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(2,2).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(3,2).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(4,2).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(0,3).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(1,3).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(2,3).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(3,3).getStato(), Stato.VIVO);
        assertEquals(controller.getCellulaFromInteger(4,3).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(0,4).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(1,4).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(2,4).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(3,4).getStato(), Stato.MORTO);
        assertEquals(controller.getCellulaFromInteger(4,4).getStato(), Stato.MORTO);
    }

    @Test
    void testOnPropertyEvent(){
        MyIdGenerator.getInstance().resetCount();
        for(int i=0; i<5; i++)
            for (int j=0; j<5; j++){
                controller.addAEntry(j,i);

            }
        ICell cell = new MyCell(Stato.MORTO, 0);
        cell.addPropertyListener(controller);
        cell.changeStato();
        assertEquals(Stato.MORTO, cell.getStato());
        assertEquals(Stato.VIVO, controller.getCellulaFromInteger(0,0).getStato());
    }
}
