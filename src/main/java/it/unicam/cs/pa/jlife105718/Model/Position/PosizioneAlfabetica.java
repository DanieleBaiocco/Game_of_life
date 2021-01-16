package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.Arrays;
import java.util.logging.Logger;

public class PosizioneAlfabetica extends MyPosition {

    private static final Logger logger = Logger.getGlobal();

    public PosizioneAlfabetica(int[] params) {
        super(params);
        logger.finest("Alphabet Position created.");
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
    public int[] getCoordinateI() {
          return Arrays.stream(getParams()).map(x->(String)x).mapToInt(x->{
            if(x.length()==2){
                int first = x.toCharArray()[0];
                int second = x.toCharArray()[1];
                return 26*(first-65+1)+second-65;
            }else{
                return x.toCharArray()[0]-65;
            }
        }).toArray();
    }

@Override
    protected int getResultForHash(Object obj, int result, int prime){
        String stringObj = (String) obj;
        if(stringObj.length()==1)
         return prime * result + stringObj.charAt(0);
        else return prime * result + stringObj.charAt(0) +stringObj.charAt(1);
    }

}
