package it.unicam.cs.pa.jlife105718;

import java.util.ArrayList;
import java.util.List;


public class PosizioneAlfabetica implements Posizione{
        private List<String> params = new ArrayList<>();
    private int dim;

    public PosizioneAlfabetica(List<Integer> params, int dim)  {
        this.dim = dim;
        changeToChar(params);
    }

    private void changeToChar(List<Integer> params)  {
        for(int i=0; i<this.dim; i++){
            int parametroI = params.get(i);
            if(0<=parametroI&&parametroI<=25) {
                String s = Character.toString(parametroI + 65);
                this.params.add(s);
            }else if(25<parametroI&&parametroI<=255){
                String s ="";
                int risultato = (int)((parametroI+65)/65-1);
                String str = Character.toString( risultato+65);
                String str2= str.concat(Character.toString(parametroI%26 + 65));
                this.params.add(str2);
            } //else if(parametroI>255) {
               // throw new ASCIIIntegersEndedException();
          //  }
        }
    }

    @Override
    public Integer getCoordinateI(int i) {
        return null;
    }

}
