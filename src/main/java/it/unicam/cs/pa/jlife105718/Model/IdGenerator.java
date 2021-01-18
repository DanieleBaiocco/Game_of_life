package it.unicam.cs.pa.jlife105718.Model;

/**
 * Chi implementa questa interfaccia svolge il ruolo di generatore di ID
 */
public interface IdGenerator {

     /**
      * Ritorna il contatore e lo incrementa
      */
     int getIdAndIncrement();
}
