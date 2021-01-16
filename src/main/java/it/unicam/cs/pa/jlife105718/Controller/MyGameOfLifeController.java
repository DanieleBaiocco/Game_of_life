package it.unicam.cs.pa.jlife105718.Controller;


import it.unicam.cs.pa.jlife105718.Model.Board.IField;
import it.unicam.cs.pa.jlife105718.Model.Cell.ICell;
import it.unicam.cs.pa.jlife105718.Model.Cell.Stato;
import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionFactory;
import it.unicam.cs.pa.jlife105718.Model.PropertyListener;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesEnum;
import it.unicam.cs.pa.jlife105718.Model.Rule.RulesFactory;

import java.util.logging.Logger;

public class MyGameOfLifeController<T extends IPosition> implements IController<T>, PropertyListener {
private final IField<T> campo;
private final RulesEnum rule;
private int[] cellsToSetAlive;
private static final Logger logger = Logger.getGlobal();

public MyGameOfLifeController(IField<T> campo, RulesEnum rule, int[] cellsToSetAlive){
    this.campo = campo;
    this.rule = rule;
    this.cellsToSetAlive = cellsToSetAlive;
    logger.finest("MyGameOfLifeController created.");
    }

public int[] getCellsToSetAlive() {
        return cellsToSetAlive;
}

@Override
public String getRepresentation(int i, int... values) {
    T pos =campo.getPosizioneFromIntegers(values);
    return (String) PositionFactory.getInstance().getPrinter(campo.getTransitionEnum()).toStringFormat(pos)[i];
}


    @Override
public void nextGen() {
    IField<T> campoCopy =  this.campo.deepCopyClone();
    campoCopy.getMappaPosizioneCellula().values().forEach(x->x.addPropertyListener(this));
    campoCopy.getMappaPosizioneCellula().values()
            .forEach(x-> RulesFactory.getInstance().getRule(rule).step(x, campoCopy.getIntorno(x)));
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
    public IField<T> getCampo() {
        return this.campo;
    }

    @Override
    public void addAEntry(int... values) {
    this.campo.addAEntry(values);
    }

    @Override
    public ICell getCellulaFromInteger(int... values) {
        return campo.getCellulaFromInteger(values);
    }

    public RulesEnum getRule(){
        return this.rule;
    }

    @Override
    public void onPropertyEvent(ICell source, String name, Stato state) {
       if(state==Stato.VIVO)
           source.setStato(Stato.MORTO);
       else source.setStato(Stato.VIVO);
       this.campo.changeCellula(source);
    }

}


