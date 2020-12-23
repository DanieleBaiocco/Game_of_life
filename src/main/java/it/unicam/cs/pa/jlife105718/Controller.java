package it.unicam.cs.pa.jlife105718;

public interface Controller {
     <T extends IPosizione> void nextGen();
     void colorateDecolorateACellula(int ... values);
     void loadBoardFromFile();
     ICampo<?> getCampo();
      void addAEntry( int ... values);
      Cellula getCellulaFromInteger(int ... values);

}
