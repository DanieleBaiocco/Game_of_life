package it.unicam.cs.pa.jlife105718.Model.DeserializatorTest;

import it.unicam.cs.pa.jlife105718.Controller.IController;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.MyCell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.MyIdGenerator;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class JsonFileDeserializationTest {
    String completedPath;
    @BeforeEach
    void init() {
        String basePath = new File("").getAbsolutePath();
        String finalPath = "/src/main/resources/preconfiguredGridsInJson/Beacon.json";
        completedPath = basePath.concat(finalPath);
    }

    @Test
    void testDeserializeFile() throws FileNotFoundException {
        MyIdGenerator.getInstance().resetCount();
        IController<?> controller = IController.createControllerFromFile(completedPath);
        assertTrue(Arrays.equals(controller.getCellsToSetAlive(), new int[]{1, 1, 2, 1, 1, 2, 2, 2, 3, 3, 4, 3, 3, 4, 4, 4}));
        assertEquals(RulesEnum.BasicRules, controller.getRule());
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                controller.addAEntry(j,i);
            }
        }
        ICell cell1 = new MyCell(Stato.MORTO, 0);
        ICell cell2=  new MyCell(Stato.MORTO, 1);
        ICell cell36 = new MyCell(Stato.MORTO, 35);
        ICell cell37 = new MyCell(Stato.MORTO, 36);
        assertTrue(controller.getCampo().getMappaPosizioneCellula().containsValue(cell1));
        assertTrue(controller.getCampo().getMappaPosizioneCellula().containsValue(cell2));
        assertTrue(controller.getCampo().getMappaPosizioneCellula().containsValue(cell36));
        assertFalse(controller.getCampo().getMappaPosizioneCellula().containsValue(cell37));
    }

}
