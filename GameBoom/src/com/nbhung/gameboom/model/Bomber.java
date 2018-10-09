package com.nbhung.gameboom.model;

import com.nbhung.gameboom.manager.ImageStore;

import java.awt.*;

public class Bomber extends Figue {
    public static final int SIZE = 35;

    public Bomber(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        rectangle = new Rectangle(x, y, SIZE, SIZE);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (orient) {
            case LEFT:
                graphics2D.drawImage(ImageStore.IMG_BOMBER_LEFT, x, y, SIZE, SIZE, null);
                break;
            case RIGHT:
                graphics2D.drawImage(ImageStore.IMG_BOMBER_RIGHT, x, y, SIZE, SIZE, null);
                break;
            case UP:
                graphics2D.drawImage(ImageStore.IMG_BOMBER_UP, x, y, SIZE, SIZE, null);
                break;
            case DOWN:
                graphics2D.drawImage(ImageStore.IMG_BOMBER_DOWN, x, y, SIZE, SIZE, null);
                break;
            default:
                break;
        }
    }


    @Override
    public void move() {
        switch (orient) {
            case LEFT:
                x--;
                rectangle.setLocation(x - 1, y);
                break;
            case RIGHT:
                x++;
                rectangle.setLocation(x + 1, y);
                break;
            case UP:
                y--;
                rectangle.setLocation(x, y - 1);
                break;
            case DOWN:
                y++;
                rectangle.setLocation(x, y + 1);
                break;
            default:
                break;
        }
    }

    public void changeOrient(int newOrient) {
        orient = newOrient;
        switch (orient) {
            case LEFT:
                rectangle.setLocation(x - 1, y);
                break;
            case RIGHT:
                rectangle.setLocation(x + 1, y);
                break;
            case UP:
                rectangle.setLocation(x, y - 1);
                break;
            case DOWN:
                rectangle.setLocation(x, y + 1);
                break;
            default:
                break;
        }
    }
}
