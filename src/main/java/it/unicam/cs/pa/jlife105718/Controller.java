package it.unicam.cs.pa.jlife105718;

public interface Controller {
    public void NextGen();
    public void colorateDecolorateACellula(Posizione pos);
    public void loadBoardFromFile();
}
