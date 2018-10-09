package com.nbhung.gameboom.model;

import com.nbhung.gameboom.manager.ImageStore;

import java.awt.*;

public class MapItem extends BaseModel {
    public static final int SIZE = 35;
    public static final int TYPE_STONE = 2;
    public static final int TYPE_WOOD = 1;
    public static final int TYPE_BOX = 3;
    public static final int TYPE_ROCK = 5;

    private int type;


    public MapItem(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;

        rectangle = new Rectangle(x, y, SIZE, SIZE);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type) {
            case TYPE_STONE:
                graphics2D.drawImage(ImageStore.IMG_STONE, x, y, SIZE, SIZE, null);
                break;
            case TYPE_WOOD:
                graphics2D.drawImage(ImageStore.IMG_WOOD, x, y, SIZE, SIZE, null);
                break;
            case TYPE_ROCK:
                graphics2D.drawImage(ImageStore.IMG_ROCK,x,y,SIZE,SIZE,null);
                break;
            case TYPE_BOX:
                graphics2D.drawImage(ImageStore.IMG_BOX, x, y, SIZE, SIZE, null);
            default:
                break;
        }
    }

}
