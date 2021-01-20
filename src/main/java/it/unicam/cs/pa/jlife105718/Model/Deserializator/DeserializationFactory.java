package it.unicam.cs.pa.jlife105718.Model.Deserializator;

/**
 * Classe che realizza il pattern Factory e il pattern Singleton. Responsabile della creazione
 * di un tipo di deserializzatore in IController a seconda dell'estensione del file che si vuole deserializzare
 */
public class DeserializationFactory {
    private static DeserializationFactory instance;
    public static DeserializationFactory getInstance(){
        if(instance==null)
            instance= new DeserializationFactory();
        return instance;
    }

    /**
     * Metodo che permette di creare un deserializzatore ad HOC per il file che si vuol deserializzare in un IController.
     * La creazione di un deserializzatore rispetto a un altro è data dalla stringa passata in input corrispondente
     * all'estensione del file. Le estensioni supportate sono per ora .json e .txt, l'unica implementazione fatta è quella
     * per il .json
     */
    public FileDeserialization getControllerFromFile(String string){
        FileDeserialization fileDeserialization;
        switch (string){
            case "json":
                fileDeserialization = new JsonFileDeserialization();
                break;
            case "txt":
                fileDeserialization = new TxtFileDeserialization();
                break;
            default:
                fileDeserialization = null;
        }
        return fileDeserialization;
    }
}
