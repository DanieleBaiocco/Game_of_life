package it.unicam.cs.pa.jlife105718.Model.Board;

import it.unicam.cs.pa.jlife105718.Model.Position.IPosition;
import it.unicam.cs.pa.jlife105718.Model.Position.PositionsEnum;

import java.lang.reflect.Field;

public interface IFactoryField {
    <T extends IPosition> IField1D<T> createField1D(PositionsEnum transitionEnum, int value1);
    <T extends IPosition> IField2D<T> createField2D(PositionsEnum transitionEnum, int value1, int value2);
    <T extends IPosition> IField3D<T> createField3D(PositionsEnum transitionEnum, int value1, int value2, int value3);
}
