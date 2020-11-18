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
                .forEach(w -> {
                            try {
                                getRule().step(w);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                );
}

   static public GameOfLifeController boardCreation(Campo<Posizione> campo, Regole<Cellula> rule) {
        if(instance == null) {
            instance = new GameOfLifeController(campo,rule);
    } return instance;
}

    public void colorateDecolorateACellula(Posizione pos) {
     if(this.campo.findCellula(pos)){
         this.campo.searchCellula(pos).changeStato();
     } else throw new IllegalArgumentException();
    }

    public void loadBoardFromFile() {

    }

    public Regole<Cellula> getRule(){
        return this.rule;
    }
}


