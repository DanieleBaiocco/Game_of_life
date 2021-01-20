package it.unicam.cs.pa.jlife105718.Model.Deserializator;

import it.unicam.cs.pa.jlife105718.Controller.IController;
import it.unicam.cs.pa.jlife105718.Model.Board.IFactoryField;

/**
 * Deserializza il contenuto di un file scritto in formato .txt in un IController.
 * L'implementazione del metodo è omessa: questa classe serve solo a far vedere che si può estendere il comportamento
 * di un deserializzatore di file anche a altri formati di file oltre che al .json
 */
public class TxtFileDeserialization implements FileDeserialization {

    /**
     * @param pathName il percorso dalla root dell'utente al file da deserializzare
     * @param factoryField mi fornisce i metodi per formare o una griglia 1D o una 2D o una 3D
     */
    @Override
    public IController<?> deserializeFile(String pathName, IFactoryField factoryField) {
        return null;
    }

}
