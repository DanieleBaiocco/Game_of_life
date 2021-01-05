package it.unicam.cs.pa.jlife105718;


public class  GameOfLifeController<T extends IPosizione> implements Controller<T>, PropertyListener {
private final ICampo<T> campo;
private final CurrentRulesEnum rule;
private int[] cellsToSetAlive;

public GameOfLifeController(ICampo<T> campo, CurrentRulesEnum  rule, int[] cellsToSetAlive){
    this.campo = campo;
    this.rule = rule;
    this.cellsToSetAlive = cellsToSetAlive;
    }

public int[] getCellsToSetAlive() {
        return cellsToSetAlive;
}

@Override
public String getRepresentation(int i, int... values) {
    T pos =campo.getPosizioneFromIntegers(values);
    return (String) TransitionFactory.getInstance().getPrinter(campo.getTransitionEnum()).toStringFormat(pos)[i];
}


    @Override
public void nextGen() {
    ICampo<T> campoCopy =  this.campo.deepCopyOfThis();
    campoCopy.getMappaPosizioneCellula().values().forEach(x->x.addPropertyListener(this));
    campoCopy.getMappaPosizioneCellula().values()
            .forEach(x->RulesFactory.getInstance().getRule(rule).step(x, campoCopy.getIntorno(x)));
}


    @Override
    public void colorateDecolorateACellula(int ... posInInt) {
      campo.changeStateOfACellula(posInInt);
      String str = "";
      for(int value : posInInt){
          str = str.concat(String.valueOf(value));
      }
      System.out.println(str);
    }

    @Override
    public ICampo<T> getCampo() {
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


