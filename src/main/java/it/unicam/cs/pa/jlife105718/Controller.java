package it.unicam.cs.pa.jlife105718;

import java.util.List;

public interface Controller {
    public void NextGen();
    public  void colorateDecolorateACellula(List<Integer> posizioneInInt);
    public void loadBoardFromFile();
}
