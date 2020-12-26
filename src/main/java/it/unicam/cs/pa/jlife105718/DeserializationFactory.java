package it.unicam.cs.pa.jlife105718;

public class DeserializationFactory {
    private static DeserializationFactory instance;
    public static DeserializationFactory getInstance(){
        if(instance==null)
            instance= new DeserializationFactory();
        return instance;
    }

    public FileDeserialization getControllerFromFile(String string){
        FileDeserialization fileDeserialization =null;
        switch (string){
            case "json":
                fileDeserialization = new JsonFileDeserialization();
                break;
            case "txt":
                fileDeserialization = new TxtFileDeserialization();
                break;
        }
        return fileDeserialization;
    }
}
