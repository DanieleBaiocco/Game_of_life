package it.unicam.cs.pa.jlife105718.Model.PrinterTest;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionFactory;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;
import it.unicam.cs.pa.jlife105718.Model.Printer.IPrintPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintPosizioneVirgolaMobileTest {
    private PositionsEnum positionsEnum;
    @BeforeEach
    public void init() {
        positionsEnum = PositionsEnum.VirgolaMobile;
    }

    @Test
    void testToStringFormat(){
        IPosition posizioneIntera = PositionFactory.getInstance().getTransition(positionsEnum).apply(new int[]{0, 4});
        IPrintPosition<IPosition> printer = PositionFactory.getInstance().getPrinter(positionsEnum);
        List<String> list = new ArrayList<>();
        list.add("0.0");
        list.add("4.0");
        assertEquals(list.get(0), printer.toStringFormat(posizioneIntera).get(0));
        assertEquals(list.get(1), printer.toStringFormat(posizioneIntera).get(1));
    }
}
