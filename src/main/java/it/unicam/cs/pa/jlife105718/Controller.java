package it.unicam.cs.pa.jlife105718;

import java.util.List;

public interface Controller {
     void NextGen() throws InterruptedException;
     void colorateDecolorateACellula(int ... values);
     void loadBoardFromFile();
     ICampo<?> getCampo();
}
