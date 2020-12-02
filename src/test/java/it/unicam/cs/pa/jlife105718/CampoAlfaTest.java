package it.unicam.cs.pa.jlife105718;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;
public class CampoAlfaTest {

    private Campo<PosizioneAlfabetica> campoAlfa;

    @BeforeEach
    void initCampoAlfa(){
        this.campoAlfa =new Campo2D<PosizioneAlfabetica>(TransitionFactory.getInstance().getTransitionToChar());
    }

    @Test
    public void testGetIntorno(){


    }


}