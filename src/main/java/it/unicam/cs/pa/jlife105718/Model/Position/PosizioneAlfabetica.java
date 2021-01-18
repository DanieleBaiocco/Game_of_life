package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Un'istanza di questa classe ha all'interno coordinate alfabetiche (es. {C,Y}, {R,T,D}, {AB,D,E}...)
 */
public class PosizioneAlfabetica extends MyPosition {

    private static final Logger logger = Logger.getGlobal();

    /**
     * Chiama il costruttore della classe Posizione. La differenza è che come metodo changeToPos viene preso quello
     * definito all'interno di questa classe
     * @param params
     */
    public PosizioneAlfabetica(int[] params) {
        super(params);
        logger.finest("Alphabet Position created.");
    }

    /**
     * Applica una conversione della singola coordinata numerica in una coordinata alfabetica. Nel caso in cui
     * la coordinata numerica sia superiore a 26 (superando il numero di lettere dell'alfabeto) la rappresentazione
     * di questa in alfabetica sarà data da una coppia di lettere una affianco all'altra (es. 27 equivarrà a
     * "AB" in formato alfabetico)
     */
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

    /**
     * Trasforma ogni coordinata contenuta all'interno della specifica posizione in intero.
     */
    @Override
    public int[] returnToIntegerCoordinates() {
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

    /**
     * Hashcode su una singola coordinata calcolato moltiplicando, nel caso di coordinata formata da una sola lettera,
     *  prime con result sommato a questa lettera. Nel caso di coordinata formata da una coppia di lettere, moltiplicando
     *  prime con result sommato alla prima e alla seconda lettera.
     * @param obj
     * @param result
     * @param prime
     * @return
     */
    @Override
    protected int getResultForHash(Object obj, int result, int prime){
        String stringObj = (String) obj;
        if(stringObj.length()==1)
         return prime * result + stringObj.charAt(0);
        else return prime * result + stringObj.charAt(0) +stringObj.charAt(1);
    }

}
