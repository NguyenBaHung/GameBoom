package com.nbhung.gameboom.model;

import com.nbhung.gameboom.manager.ImageStore;

import java.awt.*;

public class Bombbang extends BaseModel {
    public static final int WIDTH = 35;
    public static final int HEIGHT = WIDTH * 2;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    private boolean isComplete = false;

    private int orient;

    public boolean isComplete() {
        return isComplete;
    }

    public Bombbang(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        switch (orient) {
            case LEFT:
                rectangle = new Rectangle(x - WIDTH, y, HEIGHT, WIDTH);
            case RIGHT:
                rectangle = new Rectangle(x, y, HEIGHT, WIDTH);
                break;
            case UP:
                rectangle = new Rectangle(x, y - WIDTH, WIDTH, HEIGHT);
                break;
            case DOWN:
                rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
                break;
            default:
                break;
        }
    }


    @Override
    public void draw(Graphics2D graphics2D) {
        switch (orient) {
            case LEFT:
                graphics2D.drawImage(ImageStore.IMG_BOMBBANG_LEFT, x - WIDTH, y, HEIGHT, WIDTH, null);
                break;
            case RIGHT:
                graphics2D.drawImage(ImageStore.IMG_BOMBBANG_RIGHT, x, y, HEIGHT, WIDTH, null);
                break;
            case UP:
                graphics2D.drawImage(ImageStore.IMG_BOMBBANG_UP, x, y - WIDTH, WIDTH, HEIGHT, null);
                break;
            case DOWN:
                graphics2D.drawImage(ImageStore.IMG_BOMBBANG_DOWN, x, y, WIDTH, HEIGHT, null);
                break;
        }
        isComplete = true;
    }
}

