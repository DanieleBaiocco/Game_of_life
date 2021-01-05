package it.unicam.cs.pa.jlife105718;

import java.io.FileNotFoundException;

public interface FileDeserialization {
    <T extends IPosizione> Controller<?> deserializeFile(String pathName) throws FileNotFoundException;

}
