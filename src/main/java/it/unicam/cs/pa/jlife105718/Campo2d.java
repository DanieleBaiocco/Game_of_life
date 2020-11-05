package it.unicam.cs.pa.jlife105718;

import java.util.ArrayList;
import java.util.List;


public class Campo2d implements Campo {
    private int ascissa;
    private int ordinata;
    private List<Cellula> cellule;
    public Campo2d(int ascissa, int ordinata) {
        this.cellule= new ArrayList<>();
        this.ascissa=ascissa;
        this.ordinata=ordinata;
        initializeCellule(this.ascissa,this.ordinata);
    }

    private void initializeCellule(int asc, int ord) {
        for(int i=0; i<ord;i++) {
            for (int j = 0; j < asc; j++){
                Cellula cellula = new Cellula(Stato.MORTO,new Coordinate2d(j,i));
                cellule.add(cellula);
            }
        }
    }
    public boolean findCellula(int CoordinateX, int CoordinateY){
        for(Cellula cellula : cellule){
            if(cellula.getCoordinate().getx()==CoordinateX & cellula.getCoordinate().gety()==CoordinateY)
                return true;
        }
        return false;
    }
    public Cellula searchCellula(int CoordinateX, int CoordinateY){
        for(Cellula cellula : cellule){
            if(cellula.getCoordinate().getx()==CoordinateX & cellula.getCoordinate().gety()==CoordinateY)
                return cellula;
        }
        return null;
    }
    public List<Cellula> intornoCellula(Cellula cellula){
        List<Cellula> listaIntorno = new ArrayList<>();
        for (int i = getY(cellula)-1;i<=getY(cellula)+1;i++){
            for(int j= getX(cellula)-1; j<=getX(cellula)+1;j++){
                listaIntorno.add(searchCellula(j,i));
            }
        }
        return listaIntorno;

    }

     private int getX(Cellula cellula){
       return cellula.getCoordinate().getx();
     }
     private int getY(Cellula cellula){
        return cellula.getCoordinate().gety();
     }


    public List<Cellula> getCellule() {
        return cellule;
    }

    public void setCellule(List<Cellula> cellule) {
        this.cellule = cellule;
    }
}
