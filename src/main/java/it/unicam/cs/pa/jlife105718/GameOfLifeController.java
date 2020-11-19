package it.unicam.cs.pa.jlife105718;


import com.google.common.collect.Table;

import java.util.List;

public class GameOfLifeController implements Controller{
private final Campo<?> campo;
private final Regole<Cellula> rule;


public GameOfLifeController(Campo<?> campo, Regole<Cellula> rule){
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


    @Override
    public void colorateDecolorateACellula(List<Integer> posInInt) {
    Posizione pos =this.campo.getTransition().apply(posInInt);
     if(this.campo.getMappaPosizioneCellula().containsKey(pos)){
          this.campo.getMappaPosizioneCellula().get(pos).changeStato();
     } else throw new IllegalArgumentException();
    }

    public void loadBoardFromFile() {

    }

    public Regole<Cellula> getRule(){
        return this.rule;
    }
}


