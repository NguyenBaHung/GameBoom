package com.nbhung.gameboom.manager;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageStore {

    public static final Image IMG_STONE = getImage("/res/drawable/stone.png");
    public static final Image IMG_WOOD = getImage("/res/drawable/wood.png");
    public static final Image IMG_ROCK = getImage("/res/drawable/brick.png");
    public static final Image IMG_BOX = getImage("/res/drawable/box1.png");

    public static final Image IMG_BOMBER_LEFT = getImage("/res/drawable/bomber_left.png");
    public static final Image IMG_BOMBER_RIGHT = getImage("/res/drawable/bomber_right.png");
    public static final Image IMG_BOMBER_UP = getImage("/res/drawable/bomber_up.png");
    public static final Image IMG_BOMBER_DOWN = getImage("/res/drawable/bomber_down.png");

    public static final Image IMG_BOSS_LEFT = getImage("/res/drawable/boss_left.png");
    public static final Image IMG_BOSS_RIGHT = getImage("/res/drawable/boss_right.png");
    public static final Image IMG_BOSS_UP = getImage("/res/drawable/boss_up.png");
    public static final Image IMG_BOSS_DOWN = getImage("/res/drawable/boss_down.png");

    public static final Image IMG_MONSTER_LEFT = getImage("/res/drawable/monster_left.png");
    public static final Image IMG_MONSTER_RIGHT = getImage("/res/drawable/monster_right.png");
    public static final Image IMG_MONSTER_UP = getImage("/res/drawable/monster_up.png");
    public static final Image IMG_MONSTER_DOWN = getImage("/res/drawable/monster_down.png");

    public static final Image IMG_BOMB = getImage("/res/drawable/bomb.png");
    public static final Image IMG_BACKGROUND_GAME_PLAY = getImage("/res/drawable/background_Play2.png");
    public  static final Image IMG_BACKGROUND_MENU = getImage("/res/drawable/background_option.png");

    public static final Image IMG_BOMBBANG_LEFT = getImage("/res/drawable/bombbang_left_2.png");
    public static final Image IMG_BOMBBANG_RIGHT = getImage("/res/drawable/bombbang_right_2.png");
    public static final Image IMG_BOMBBANG_UP = getImage("/res/drawable/bombbang_up_2.png");
    public static final Image IMG_BOMBBANG_DOWN = getImage("/res/drawable/bombbang_down_2.png");

    public static final Image IMG_ITEM_BOMB = getImage("/res/drawable/item_bomb.png");
    public static final Image IMG_ITEM_BOMBSIZE = getImage("/res/drawable/item_bombsize.png");
    public static final Image IMG_ITEM_SHOE = getImage("/res/drawable/item_shoe.png");

    public static final Image IMG_YOU_WIN = getImage("/res/drawable/youwin.jpg");
    public static final Image IMG_GAME_OVER = getImage("/res/drawable/gameover.jpg");

    public static Image getImage(String path) {
        URL url = Image.class.getResource(path);
        return new ImageIcon(url).getImage();
    }
}
