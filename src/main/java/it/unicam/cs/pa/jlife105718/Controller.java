package it.unicam.cs.pa.jlife105718;


public class Controller  {
private Field<Posizione> field;
public Controller (Field<Posizione> field){
    this.field = field;
}
/*
public Controller (File jsonFile){
    this.field =deserialize(jsonFile);
}*/
public void NextGen(){
    this.field.getMappaPosizioneCellula().values()
            .stream()
            .forEach(w->RulesFactory.getRulesFactory(field).getBasicRules().step(w));
     }

}


