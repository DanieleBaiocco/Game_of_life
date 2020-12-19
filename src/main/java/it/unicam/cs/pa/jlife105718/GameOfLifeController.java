package it.unicam.cs.pa.jlife105718;


import javafx.scene.control.Cell;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameOfLifeController implements Controller{
private final ICampo<?> campo;
private final Regole<Cellula> rule;
static private GameOfLifeController controller;
private List<Cellula> list;

private GameOfLifeController(ICampo<?> campo, Regole<Cellula> rule){
    this.campo = campo;
    this.rule = rule;
    this.list = new ArrayList<>();
}
/*
public Controller (File jsonFile){
    this.field =deserialize(jsonFile);
}*/
static public GameOfLifeController getInstance(ICampo<?> campo, Regole<Cellula> rule){
    if(controller==null){
       controller= new GameOfLifeController(campo,rule);
    }

    return controller;
}

public  void NextGen() {
//CLONE
 this.campo.getMappaPosizioneCellula().values().stream().forEach(rule::step);
}


    public void setList(List<Cellula> list) {
        this.list = list;
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

    @Override
    public void addAEntry(int... values) {
    this.campo.addAEntry(values);
    }

    @Override
    public Cellula getCellulaFromInteger(int... values) {
        return campo.getCellulaFromInteger(values);
    }

    public Regole<Cellula> getRule(){
        return this.rule;
    }


}


