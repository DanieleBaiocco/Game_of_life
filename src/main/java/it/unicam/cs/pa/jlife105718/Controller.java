package it.unicam.cs.pa.jlife105718;

import java.util.List;

public interface Controller {
     void NextGen();
     void colorateDecolorateACellula(List<Integer> posizioneInInt);
     void loadBoardFromFile();
     ICampo<?> getCampo();
}
