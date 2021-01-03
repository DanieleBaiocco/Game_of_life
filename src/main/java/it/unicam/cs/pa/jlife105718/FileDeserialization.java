package it.unicam.cs.pa.jlife105718;

import java.io.FileNotFoundException;

public interface FileDeserialization {
    Controller deserializeFile(String pathName) throws FileNotFoundException;

}
