package com.nbhung.gameboom.model;

import com.nbhung.gameboom.manager.ImageStore;

import java.awt.*;

public class Item extends BaseModel {
    public static final int SIZE = 35;
    public static final int TYPE_BOMB = 0;
    public static final int TYPE_BOMB_SIZE = 1;
    public static final int TYPE_SHOE = 2;

    private int type;

    public Item(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;

        rectangle = new Rectangle(x, y, SIZE, SIZE);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type) {
            case TYPE_BOMB:
                graphics2D.drawImage(ImageStore.IMG_ITEM_BOMB, x, y, SIZE, SIZE, null);
                break;
            case TYPE_BOMB_SIZE:
                graphics2D.drawImage(ImageStore.IMG_ITEM_BOMBSIZE, x, y, SIZE, SIZE, null);
                break;
            case TYPE_SHOE:
                graphics2D.drawImage(ImageStore.IMG_ITEM_SHOE, x, y, SIZE, SIZE, null);
                break;
            default:
                break;
        }
    }
}
