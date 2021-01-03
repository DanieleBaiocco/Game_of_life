package it.unicam.cs.pa.jlife105718;

public class DeserializationFactory {
    private static DeserializationFactory instance;
    public static DeserializationFactory getInstance(){
        if(instance==null)
            instance= new DeserializationFactory();
        return instance;
    }

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
                //forse qua va il throw di NullPointerException(non solo qua ma in tutti i default(?))
                fileDeserialization = null;
        }
        return fileDeserialization;
    }
}
