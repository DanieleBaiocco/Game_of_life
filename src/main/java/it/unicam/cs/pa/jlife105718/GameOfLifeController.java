package it.unicam.cs.pa.jlife105718;


import java.util.List;

public class GameOfLifeController implements Controller{
private final ICampo<?> campo;
private final Regole<Cellula> rule;
static private GameOfLifeController controller;


private GameOfLifeController(ICampo<?> campo, Regole<Cellula> rule){
    this.campo = campo;
    this.rule = rule;
}
/*
public Controller (File jsonFile){
    this.field =deserialize(jsonFile);
}*/
static public GameOfLifeController getInstance(ICampo<?> ICampo, Regole<Cellula> rule){
    if(controller==null){
       controller= new GameOfLifeController(ICampo,rule);
    }

    return controller;
}

public void NextGen(){
synchronized (rule) {
    this.campo.getMappaPosizioneCellula().values()
            .forEach(w -> {
                        try {
                            rule.step(w);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
    rule.notifyAll();
}
}


    @Override
    public void colorateDecolorateACellula(int ... posInInt) {
      campo.changeStateOfACellula(posInInt);
    }

    public void loadBoardFromFile() {

    }

    @Override
    public ICampo<?> getCampo() {
        return this.campo;
    }

    public Regole<Cellula> getRule(){
        return this.rule;
    }
}


