package it.unicam.cs.pa.jlife105718;


import com.google.common.collect.Table;

public class GameOfLifeController implements Controller{
private final Campo<Posizione> campo;
private final Regole<Cellula> rule;
static private GameOfLifeController instance;

private GameOfLifeController(Campo<Posizione> campo, Regole<Cellula> rule){
    this.campo = campo;
    this.rule = rule;
}
/*
public Controller (File jsonFile){
    this.field =deserialize(jsonFile);
}*/

public void NextGen(){
    this.campo.getMappaPosizioneCellula().values()
            .forEach(w->getRule().step(w));
     }

   static public GameOfLifeController boardCreation(Campo<Posizione> campo, Regole<Cellula> rule) {
        if(instance == null) {
            return new GameOfLifeController(campo,rule);
    } return instance;
}

    public void coloratedecolorateacellula() {

    }

    public void loadBoardFromFile() {

    }

    public Regole<Cellula> getRule(){
        return this.rule;
    }
}


