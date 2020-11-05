package it.unicam.cs.pa.jlife105718;

public class Coordinate2d implements Coordinate{
    private int x;
    private int y;
    public Coordinate2d(int x, int y){
        this.x=x;
        this.y=y;
    }



    public void setY(int y) {
        this.y = y;
    }



    public void setX(int x) {
        this.x = x;
    }


    public int getDim() {
        return 2;
    }


    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

}
