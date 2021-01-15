package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

public class MyFactoryField implements IFactoryField {

    @Override
    public <T extends IPosition> IField1D<T> createField1D(PositionsEnum transitionEnum, int value1) {
        return new MyField1D<>(transitionEnum,value1);
    }

    @Override
    public <T extends IPosition> IField2D<T> createField2D(PositionsEnum transitionEnum, int value1, int value2) {
        return new MyField2D<>(transitionEnum,value1, value2);
    }

    @Override
    public <T extends IPosition> IField3D<T>createField3D(PositionsEnum transitionEnum, int value1, int value2, int value3) {
        return new MyField3D<>(transitionEnum,value1, value2,value3);
    }
}
