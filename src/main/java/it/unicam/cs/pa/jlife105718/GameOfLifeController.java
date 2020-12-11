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

public  void NextGen()  throws InterruptedException{
int i =0;
//CLONE
    synchronized (campo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
               this.campo.getMappaPosizioneCellula().values()
                        .stream()
                        .map(w -> {
                            try {
                                return rule.step(w);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return w;
                        })
                       .count();

            });
            executor.shutdown();
        while (!executor.isTerminated())
            i=i+1;
        campo.notifyAll();
        System.out.println(i);
    }
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

    public Regole<Cellula> getRule(){
        return this.rule;
    }


}


