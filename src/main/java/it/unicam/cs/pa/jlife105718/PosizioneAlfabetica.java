package it.unicam.cs.pa.jlife105718;

import java.util.List;

public class PosizioneAlfabetica extends Posizione{

    public PosizioneAlfabetica(List<Integer> params) {
        super(params);
    }

        @Override
         public String changeToPos(int x) {
            if (x<=25) {
                return Character.toString(x + 65);
            }else if(x<=255){
                String s ="";
                int risultato =(x+65)/65-1;
                String str = Character.toString( risultato+65);
                return str.concat(Character.toString(x%26 + 65));
            } else {
                throw new IllegalArgumentException("Il numero inserito "+x+" è troppo grande (>255");
           }
        }

    @Override
    public Integer getCoordinateI(int i) {
        String charI = (String) super.getParams().get(i);
        return charI.charAt(0)-65;
    }

@Override
    protected int getResultForHash(Object obj, int result, int prime){
        String stringObj = (String) obj;
        if(stringObj.length()==1)
         return prime * result + stringObj.charAt(0);
        else return prime * result + stringObj.charAt(0) +stringObj.charAt(1);
    }

}
