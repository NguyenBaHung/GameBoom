package com.nbhung.gameboom.model;

import com.nbhung.gameboom.manager.ImageStore;

import java.awt.*;

public class Bomb extends BaseModel{

    public static final int SIZE = 35;
    private boolean isComplete = false;

    public boolean isComplete() {
        return isComplete;
    }

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;

        rectangle = new Rectangle();

    }
    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_BOMB, x, y, SIZE, SIZE, null);
        isComplete = true;
    }
}
