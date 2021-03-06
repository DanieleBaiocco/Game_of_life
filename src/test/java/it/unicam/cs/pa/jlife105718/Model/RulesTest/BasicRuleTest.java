package it.unicam.cs.pa.jlife105718.Model.RulesTest;

import it.unicam.cs.pa.jlife105718.Controller.MyGameOfLifeController;
import it.unicam.cs.pa.jlife105718.Model.Board.IField2D;
import it.unicam.cs.pa.jlife105718.Model.Board.MyField2D;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.MyIdGenerator;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneNumericaIntera;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicRuleTest {
    private RulesEnum rule;
    private IField2D<PosizioneNumericaIntera> campo;
    private MyGameOfLifeController<?> controller;
    @BeforeEach
    void initController(){
        MyIdGenerator.getInstance().resetCount();
        campo = new MyField2D<>(PositionsEnum.Interno,5,5);
        rule = RulesEnum.BasicRules;
        controller = new MyGameOfLifeController<>(campo,rule, null);
        for(int i=0; i<5; i++){
            for (int j=0; j<5; j++) {
                campo.addAEntry(j,i);
            }
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
    }

    @Test
    void testNextGen(){
        MyIdGenerator.getInstance().resetCount();
        controller.nextGen();
        assertEquals(this.campo.getCellulaFromInteger(0,0).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(1,0).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(2,0).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(3,0).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(4,0).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(0,1).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(1,1).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(2,1).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(3,1).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(4,1).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(0,2).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(1,2).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(2,2).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(3,2).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(4,2).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(0,3).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(1,3).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(2,3).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(3,3).getStato(), Stato.VIVO);
        assertEquals(this.campo.getCellulaFromInteger(4,3).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(0,4).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(1,4).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(2,4).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(3,4).getStato(), Stato.MORTO);
        assertEquals(this.campo.getCellulaFromInteger(4,4).getStato(), Stato.MORTO);
    }
}
