package com.deniska;

/**
 * Created by WinDA on 19.10.2017.
 */
public interface FigureBase {
    String getType();
    FigureBase copy();
    String represent();
    FigureBase getConcreteObject();
}
