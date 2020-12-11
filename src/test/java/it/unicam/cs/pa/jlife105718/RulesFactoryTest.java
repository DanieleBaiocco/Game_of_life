package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RulesFactoryTest {
    private  Regole<Cellula> rule;
    private Campo2D<PosizioneNumericaIntera> campo;
    private GameOfLifeController controller;
    @BeforeEach
    void initController(){
        campo = new Campo2D<>(TransitionFactory.getInstance().getTransitionToInteger());
        rule = RulesFactory.getRulesFactory(campo).getBasicRules();
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
    void testNextGen() throws InterruptedException {
        controller.NextGen();
    }
}
