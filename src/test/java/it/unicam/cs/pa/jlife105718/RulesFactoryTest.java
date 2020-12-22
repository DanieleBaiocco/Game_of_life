package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class RulesFactoryTest {
    private  CurrentRulesEnum rule;
    private Campo2D<PosizioneNumericaIntera> campo;
    private GameOfLifeController controller;
    @BeforeEach
    void initController(){
        campo = new Campo2D<>(TransitionFactory.getInstance().getTransitionToInteger());
        rule = CurrentRulesEnum.BasicRules;
        controller = GameOfLifeController.getInstance(campo, rule);
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
    void testNextGen() throws InterruptedException, CloneNotSupportedException {
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
