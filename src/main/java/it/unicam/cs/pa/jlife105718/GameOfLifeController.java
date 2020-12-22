package it.unicam.cs.pa.jlife105718;


public class GameOfLifeController implements Controller, PropertyListener{
private final ICampo<?> campo;
private final CurrentRulesEnum rule;
static private GameOfLifeController controller;

private GameOfLifeController(ICampo<?> campo, CurrentRulesEnum  rule){
    this.campo = campo;
    this.rule = rule;
}
/*
public Controller (File jsonFile){
    this.field =deserialize(jsonFile);
}*/
static public GameOfLifeController getInstance(ICampo<?> campo, CurrentRulesEnum rule){
    if(controller==null){
       controller= new GameOfLifeController(campo,rule);
    }
    return controller;
}

public  void nextGen() throws CloneNotSupportedException {
    RulesFactory rulesFactory = new RulesFactory();
    Campo<?> campoCopy = (Campo<?>) this.campo.clone();
    campoCopy.getMappaPosizioneCellula().values().forEach(x->x.addPropertyListener(this));
    campoCopy.getMappaPosizioneCellula().values()
            .stream()
            .forEach(x->rulesFactory.getRule(rule,campoCopy).step(x));
}

    @Override
    public void colorateDecolorateACellula(int ... posInInt) {
      campo.changeStateOfACellula(posInInt);
    }

    public void loadBoardFromFile() { }

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

    public CurrentRulesEnum getRule(){
        return this.rule;
    }

    @Override
    public void onPropertyEvent(Cellula source, String name, Stato state) {
    //forse serve un metodo per ritornare la cellula passata all'interno del campo
       if(state==Stato.VIVO)
           source.setStato(Stato.MORTO);
       else source.setStato(Stato.VIVO);
       this.campo.changeCellula(source);
    }
}


