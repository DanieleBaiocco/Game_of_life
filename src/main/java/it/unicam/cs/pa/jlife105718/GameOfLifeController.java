package it.unicam.cs.pa.jlife105718;


public class GameOfLifeController {
private Campo<Posizione> campo;
public GameOfLifeController(Campo<Posizione> campo){
    this.campo = campo;
}
/*
public Controller (File jsonFile){
    this.field =deserialize(jsonFile);
}*/
public void NextGen(){
    this.campo.getMappaPosizioneCellula().values()
            .forEach(w->RulesFactory.getRulesFactory(campo).getBasicRules().step(w));
     }

}


