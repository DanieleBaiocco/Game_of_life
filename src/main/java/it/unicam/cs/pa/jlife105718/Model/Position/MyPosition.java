package it.unicam.cs.pa.jlife105718.Model.Position;

import java.util.Arrays;

/**
 * Personale implementazione dell'interfaccia IPosition. Questa classe è abstract e è un contenitore di tutto
 * il codice che può esser messo a fattor comune per le classi che le estenderanno.
 */
public abstract class MyPosition implements IPosition {
    private Object[] params;

    /**
     * Per ogni singola coordinata intera passata, viene eseguito il metodo changeToPos (la cui implementazione
     * è definita nelle classi che estenderanno questa classe) che attua una conversione nel tipo ritornato dal metodo.
     * @param params
     */
    public MyPosition(int[] params) {
        this.params = Arrays.stream(params).mapToObj(this::changeToPos).toArray();
    }

    /**
     * Ritorna tutte le coordinate che rappresentano questa specifica posizione
     * @return
     */
    public Object[] getParams(){
        return params;
    }

    /**
     * La parte iniziale dell'equals è la solita. L'ultima parte controlla se la dimensione dell'array di coordinate
     * è la stessa per entrambi gli oggetti che vengon confrontati. Poi per ogni coordinata nel primo array, si controlla
     * se questa è uguale secondo equals alla coordinata nell'altro array allo stesso indice. Ogni volta che ciò è vero si
     * incrementa un contatore. Se al termine dell'iterazione dell'array si ha che il numero nel contatore è pari alla
     * lunghezza dell'array di coordinate allora le due istanze sono uguali
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyPosition other = (MyPosition) obj;
        if(this.getParams().length != other.getParams().length)
            return false;
        int count = 0;
        for (int i=0; i<this.getParams().length; i++){
            if(this.getParams()[i].equals(other.getParams()[i]))
                count++;
        }
        return count == getParams().length;
    }

    /**
     * Vien fatto utilizzando il pattern template: viene fornito uno scheletro comune alle sottoclassi che implementeranno
     * questa classe, a cui verrà chiesto di definire un metodo dichiarato qua abstract. Il motivo è che non si è a conoscenza
     * di come calcolare l'hashCode a questo livello e quindi si demanda questa responsabilità alle sottoclassi nella gerarchia.
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for(Object param : getParams()){
            result= getResultForHash(param, result, prime);
        }
        return result;
    }

    protected abstract int getResultForHash(Object obj, int result, int prime);
}
