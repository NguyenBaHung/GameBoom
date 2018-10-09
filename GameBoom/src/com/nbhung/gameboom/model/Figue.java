package com.nbhung.gameboom.model;

import java.awt.*;

public abstract class Figue extends BaseModel{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    protected int orient;

    public abstract void move();
}
