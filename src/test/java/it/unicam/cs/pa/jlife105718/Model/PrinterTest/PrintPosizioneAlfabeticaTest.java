package it.unicam.cs.pa.jlife105718.Model.PrinterTest;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionFactory;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Position.PosizioneAlfabetica;
import it.unicam.cs.pa.jlife105718.Model.Printer.IPrintPosition;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PrintPosizioneAlfabeticaTest {
    private PositionsEnum positionsEnum;
    @BeforeEach
    public void init() {
        positionsEnum = PositionsEnum.Alfabetico;
    }

    @Test
    void testToStringFormat(){
        PosizioneAlfabetica posizioneAlfabetica = (PosizioneAlfabetica) PositionFactory.getInstance().getTransition(positionsEnum).apply(new int[]{0, 4});
        IPrintPosition<IPosition> printer = PositionFactory.getInstance().getPrinter(positionsEnum);
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("E");
        assertEquals(list.get(0), printer.toStringFormat(posizioneAlfabetica).get(0));
        assertEquals(list.get(1), printer.toStringFormat(posizioneAlfabetica).get(1));
    }
}
