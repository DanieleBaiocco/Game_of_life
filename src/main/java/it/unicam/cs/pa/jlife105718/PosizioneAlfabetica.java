package it.unicam.cs.pa.jlife105718;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PosizioneAlfabetica implements Posizione{
    private List<String> params = new ArrayList<>();
    public PosizioneAlfabetica(List<Integer> params)  {
        this.params = params.stream().map(this::changeToPos).collect(Collectors.toList());
    }

        public String changeToPos(int x)  {
            if(0<=x&&x<=25) {
                return Character.toString(x + 65);
            }else if(25<x&&x<=255){
                String s ="";
                int risultato =(x+65)/65-1;
                String str = Character.toString( risultato+65);
                return str.concat(Character.toString(x%26 + 65));
            } //else if(parametroI>255) {
               // throw new ASCIIIntegersEndedException();
          //  }
        return "";
    }

    @Override
    public Integer getCoordinateI(int i) {
        return null;
    }

}
