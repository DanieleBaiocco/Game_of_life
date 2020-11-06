package it.unicam.cs.pa.jlife105718;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosizioneAlfabetica implements Posizione{
    private List<Character> params;
    private int dim;
    private Map<Integer, Character> mappaIntChar;
    public PosizioneAlfabetica(List<Integer> params, int dim){
        this.dim=dim;
        initMapIntegerCharacter();

    }

    private void initMapIntegerCharacter() {
        this.mappaIntChar= new HashMap<>();
        
    }

    private void changeToChar(List<Integer> params) {

    }

    @Override
    public Integer getCoordinateI(int i) {
        return null;
    }

}
