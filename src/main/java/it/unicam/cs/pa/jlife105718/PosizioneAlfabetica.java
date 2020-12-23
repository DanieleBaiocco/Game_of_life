package it.unicam.cs.pa.jlife105718;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PosizioneAlfabetica implements IPosizione{
    private List<String> params;
    public PosizioneAlfabetica(List<Integer> params) {
        this.params = params.stream().map(this::changeToPos).collect(Collectors.toList());
    }

    public PosizioneAlfabetica(){
        this.params = new ArrayList<>();
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
        String charI = this.params.get(i);
        return charI.charAt(0)-65;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for(String param : params){
        result = prime * result + param.charAt(0);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PosizioneAlfabetica other = (PosizioneAlfabetica) obj;
        if(this.getParams().size() != other.params.size())
            return false;
        int count = 0;
        for (int i=0; i<this.params.size(); i++){
            if(this.params.get(i).equals(other.getParams().get(i)))
                count++;
        }
        return count == params.size();
    }

    public List<String> getParams() {
        return this.params;
    }


}
