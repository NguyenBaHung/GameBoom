package com.nbhung.gameboom.manager;

import com.nbhung.gameboom.model.*;
import sun.java2d.windows.GDIRenderer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    public static final int ROWS = 20;
    public static final int COLUMNS = 30;
    public static final int TIME_DOWN_MAX_OF_BOMBER = 1600;
    public static final int TIME_DOWN_MAX_OF_BOSSES = 1600;
    public static boolean isFinish = false;

    private ArrayList<MapItem> stones;
    private ArrayList<MapItem> rocks;
    private ArrayList<MapItem> woods;
    private ArrayList<MapItem> boxs;
    private Bomber bomber;
    private ArrayList<Boss> bosses;
    private ArrayList<Monster> monsters;
    private ArrayList<Bomb> bombsOfBomber;
    private ArrayList<Bomb> bombsOfBosses;
    private ArrayList<Bombbang> bombbangsOfBomber;
    private ArrayList<Bombbang> bombbangsOfBosses;
    private ArrayList<Item> items;

    private int timeDownOfBomber;
    private int timeDownOfBosses;


    public GameManager() {
        stones = new ArrayList<>();
        rocks = new ArrayList<>();
        woods = new ArrayList<>();
        boxs = new ArrayList<>();
        bosses = new ArrayList<>();
        monsters = new ArrayList<>();
        bombsOfBomber = new ArrayList<>();
        bombsOfBosses = new ArrayList<>();
        bombbangsOfBomber = new ArrayList<>();
        bombbangsOfBosses = new ArrayList<>();
        items = new ArrayList<>();
    }

    public void generateMap() {
        try {
            String path = GameManager.class.getResource("/res/assets/map.txt").getPath();
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int i = 0; i < ROWS; i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < COLUMNS; j++) {
                    int type = line.charAt(j) - 48;
                    if (type != 0) {
                        int x = MapItem.SIZE * j;
                        int y = MapItem.SIZE * i;
                        MapItem mapItem = new MapItem(x, y, type);
                        switch (type) {
                            case MapItem.TYPE_STONE:
                                stones.add(mapItem);
                                break;
                            case MapItem.TYPE_ROCK:
                                rocks.add(mapItem);
                                break;
                            case MapItem.TYPE_WOOD:
                                woods.add(mapItem);
                                break;
                            case MapItem.TYPE_BOX:
                                boxs.add(mapItem);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateBosses() {
        Boss boss = new Boss(650, 200, Boss.LEFT);
        Boss boss1 = new Boss(660, 300, Boss.LEFT);
        Boss boss2 = new Boss(670, 400, Boss.LEFT);
        bosses.add(boss);
        bosses.add(boss1);
        bosses.add(boss2);
    }

    public void generateBomber() {
        bomber = new Bomber(50, 50, Bomber.LEFT);
    }

    public void generateMonsters() {
        Monster monster = new Monster(600, 620, Monster.LEFT);
        Monster monster1 = new Monster(200, 620, Monster.LEFT);
        Monster monster2 = new Monster(700, 620, Monster.LEFT);
        monsters.add(monster);
        monsters.add(monster1);
        monsters.add(monster2);
    }

    public void generateBombsOfBomber() {
        if (timeDownOfBomber == 0) {
            int x = bomber.getX();
            int y = bomber.getY();
            Bomb bomb = new Bomb(x, y);
            bombsOfBomber.add(bomb);
            PlayerWav playerWav = new PlayerWav("newbomb");
            playerWav.play();
            timeDownOfBomber = TIME_DOWN_MAX_OF_BOMBER;
        }
    }

    public void generateBombsOfBosses() {
        if (timeDownOfBosses == 0) {
            for (int i = 0; i < bosses.size(); i++) {
                int x = bosses.get(i).getX();
                int y = bosses.get(i).getY();
                Bomb bomb = new Bomb(x, y);
                bombsOfBosses.add(bomb);
                PlayerWav playerWav = new PlayerWav("newbomb");
                timeDownOfBosses = TIME_DOWN_MAX_OF_BOSSES;
            }
        }
    }


    public void generateBombbangsOfBosses() {
        if (timeDownOfBosses == 720) {
            for (int i = 0; i < bombsOfBosses.size(); i++) {
                if (bombsOfBosses.get(i).isComplete() == true) {
                    int x = bombsOfBosses.get(i).getX();
                    int y = bombsOfBosses.get(i).getY();
                    Bombbang bombbang = new Bombbang(x, y, Bombbang.LEFT);
                    bombbangsOfBosses.add(bombbang);
                    Bombbang bombbang1 = new Bombbang(x, y, Bombbang.RIGHT);
                    bombbangsOfBosses.add(bombbang1);
                    Bombbang bombbang2 = new Bombbang(x, y, Bombbang.UP);
                    bombbangsOfBosses.add(bombbang2);
                    Bombbang bombbang3 = new Bombbang(x, y, Bombbang.DOWN);
                    bombbangsOfBosses.add(bombbang3);
                    PlayerWav playerWav = new PlayerWav("bomb_bang");
                    playerWav.play();

                }
                bombsOfBosses.remove(bombsOfBosses.get(i));
            }
        }
    }

    public void generateBombbangsOfBomber() {
        if (timeDownOfBomber == 720) {
            for (int i = 0; i < bombsOfBomber.size(); i++) {
                if (bombsOfBomber.get(i).isComplete() == true) {
                    int x = bombsOfBomber.get(i).getX();
                    int y = bombsOfBomber.get(i).getY();
                    Bombbang bombbang = new Bombbang(x, y, Bombbang.LEFT);
                    bombbangsOfBomber.add(bombbang);
                    Bombbang bombbang1 = new Bombbang(x, y, Bombbang.RIGHT);
                    bombbangsOfBomber.add(bombbang1);
                    Bombbang bombbang2 = new Bombbang(x, y, Bombbang.UP);
                    bombbangsOfBomber.add(bombbang2);
                    Bombbang bombbang3 = new Bombbang(x, y, Bombbang.DOWN);
                    bombbangsOfBomber.add(bombbang3);
                    PlayerWav playerWav = new PlayerWav("bomb_bang");
                    playerWav.play();
                }
                bombsOfBomber.remove(bombsOfBomber.get(i));
            }
        }
    }

    public void deleteBombbangsOfBomberAndItems() {
        if (timeDownOfBomber >= 0 && timeDownOfBomber <= 480) {
            for (int i = 0; i < bombbangsOfBomber.size(); i++) {
                if (bombbangsOfBomber.get(i).isComplete() == true) {
                    bombbangOfBomberCollistionWoods(i);
                    bombbangOfBomberCollistionBoxs(i);
                    bombbangOfBomberCollistionBosses(i);
                    bombbangOfBomberCollistionMonsters(i);
                    bombbangsOfBomber.remove(bombbangsOfBomber.get(i));
                }
            }
        }
    }

    public void deleteBombbangsOfBossesAndItems() {
        if (timeDownOfBosses >= 0 && timeDownOfBosses <= 480) {
            for (int i = 0; i < bombbangsOfBosses.size(); i++) {

                if (bombbangsOfBosses.get(i).isComplete() == true) {
                    bombbangOfBossesCollistionWoods(i);
                    bombbangOfBossesCollistionBoxs(i);
                    bombbangOfBossesCollistionBosses(i);
                    bombbangOfBossesCollistionMonsters(i);
                    bombbangsOfBosses.remove(bombbangsOfBosses.get(i));
                }
            }
        }
    }

    public void generateItem(int x, int y, int type) {
        Item item = new Item(x, y, type);
        items.add(item);
    }

    public boolean finishGame() {
        if (bosses.size() == 0 && monsters.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean dieBomberByBombbangsOfBomber() {
        if (timeDownOfBomber >= 0 && timeDownOfBomber <= 480) {
            for (int i = 0; i < bombbangsOfBomber.size(); i++) {
                if (bombbangsOfBomber.get(i).isComplete() == true) {
                    if (isBombbangOfBomberCollistionBomber(i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dieBomberByBombbangsOfBosses() {
        if (timeDownOfBosses >= 0 && timeDownOfBosses <= 480) {
            for (int i = 0; i < bombbangsOfBosses.size(); i++) {
                if (bombbangsOfBosses.get(i).isComplete() == true) {
                    if (isBombbangOfBossesCollistionBomber(i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void drawStones(Graphics2D graphics2D) {
        for (int i = 0; i < stones.size(); i++) {
            stones.get(i).draw(graphics2D);
        }
    }

    public void drawRocks(Graphics2D graphics2D) {
        for (int i = 0; i < rocks.size(); i++) {
            rocks.get(i).draw(graphics2D);
        }
    }

    public void drawWoods(Graphics2D graphics2D) {
        for (int i = 0; i < woods.size(); i++) {
            woods.get(i).draw(graphics2D);
        }
    }

    public void drawBoxs(Graphics2D graphics2D) {
        for (int i = 0; i < boxs.size(); i++) {
            boxs.get(i).draw(graphics2D);
        }
    }

    public void drawBomber(Graphics2D graphics2D) {
        bomber.draw(graphics2D);
    }

    public void drawBosses(Graphics2D graphics2D) {
        for (int i = 0; i < bosses.size(); i++) {
            bosses.get(i).draw(graphics2D);
        }
    }

    public void drawMonsters(Graphics2D graphics2D) {
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).draw(graphics2D);
        }
    }

    public void drawBombsOfBomber(Graphics2D graphics2D) {
        for (int i = 0; i < bombsOfBomber.size(); i++) {
            bombsOfBomber.get(i).draw(graphics2D);
        }
    }

    public void drawBombsOfBosses(Graphics2D graphics2D) {
        for (int i = 0; i < bombsOfBosses.size(); i++) {
            bombsOfBosses.get(i).draw(graphics2D);
        }
    }


    public void drawBombbangsOfBomber(Graphics2D graphics2D) {
        for (int i = 0; i < bombbangsOfBomber.size(); i++) {
            bombbangsOfBomber.get(i).draw(graphics2D);
        }
    }

    public void drawBombbangsOfBosses(Graphics2D graphics2D) {
        for (int i = 0; i < bombbangsOfBosses.size(); i++) {
            bombbangsOfBosses.get(i).draw(graphics2D);
        }
    }

    public void drawItems(Graphics2D graphics2D) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(graphics2D);
        }
    }

    public void moveBomber(int newOrient) {
        bomber.changeOrient(newOrient);
        if (isBomberCanMove() && !finishGame()
                && isFinish == false) {
            bomber.move();
        }
    }

    public void moveBosses() {
        for (int i = 0; i < bosses.size(); i++) {
            bosses.get(i).randomOrient();
            if (isBossesCanMove(i) && !finishGame() && isFinish == false) {
                bosses.get(i).move();
            }
        }
    }

    public void moveMonsters() {
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).randomOrient();
            if (isMonstersCanMove(i) && !finishGame() && isFinish==false) {
                monsters.get(i).move();
            }

        }
    }

    public boolean dieBomber() {
        if (dieBomberByBombbangsOfBomber() || dieBomberByBombbangsOfBosses()) {
            isFinish = true;
        }
        return isFinish;
    }

    private boolean isBomberCanMove() {
        if (isBomberCollistionStones() || isBomberCollistionRocks()
                || isBomberCollistionWoods() || isBomberCollistionBoxs()) {
            return false;
        }
        return true;
    }

    private boolean isBossesCanMove(int index) {
        if (isBossCollistionStones(index) || isBossCollistionRocks(index)
                || isBossCollistionWoods(index) || isBossCollistionBoxs(index)) {
            return false;
        }
        return true;
    }

    private boolean isMonstersCanMove(int index) {
        if (isMonsterCollistionStones(index) || isMonsterCollistionRocks(index)
                || isMonsterCollistionWoods(index) || isMonsterCollistionBoxs(index)) {
            return false;
        }
        return true;
    }


    private boolean isBomberCollistionStones() {
        for (int i = 0; i < stones.size(); i++) {
            if (isBomberCollistion(stones.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBomberCollistionRocks() {
        for (int i = 0; i < rocks.size(); i++) {
            if (isBomberCollistion(rocks.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBomberCollistionWoods() {
        for (int i = 0; i < woods.size(); i++) {
            if (isBomberCollistion(woods.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBomberCollistionBoxs() {
        for (int i = 0; i < boxs.size(); i++) {
            if (isBomberCollistion(boxs.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBossCollistionStones(int index) {
        for (int i = 0; i < stones.size(); i++) {
            if (isBossesCollistion(stones.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBossCollistionRocks(int index) {
        for (int i = 0; i < rocks.size(); i++) {
            if (isBossesCollistion(rocks.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBossCollistionWoods(int index) {
        for (int i = 0; i < woods.size(); i++) {
            if (isBossesCollistion(woods.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBossCollistionBoxs(int index) {
        for (int i = 0; i < boxs.size(); i++) {
            if (isBossesCollistion(boxs.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMonsterCollistionStones(int index) {
        for (int i = 0; i < stones.size(); i++) {
            if (isMonstersCollistion(stones.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMonsterCollistionRocks(int index) {
        for (int i = 0; i < rocks.size(); i++) {
            if (isMonstersCollistion(rocks.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMonsterCollistionWoods(int index) {
        for (int i = 0; i < woods.size(); i++) {
            if (isMonstersCollistion(woods.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMonsterCollistionBoxs(int index) {
        for (int i = 0; i < boxs.size(); i++) {
            if (isMonstersCollistion(boxs.get(i), index)) {
                return true;
            }
        }
        return false;
    }

    private void bombbangOfBomberCollistionWoods(int index) {
        for (int i = 0; i < woods.size(); i++) {
            if (isBombbangOfBomberCollistion(woods.get(i), index)) {
                woods.remove(woods.get(i));
            }
        }
    }

    private void bombbangOfBossesCollistionWoods(int index) {
        for (int i = 0; i < woods.size(); i++) {
            if (isBombbangOfBossesCollistion(woods.get(i), index)) {
                woods.remove(woods.get(i));
            }
        }
    }


    private void bombbangOfBomberCollistionBoxs(int index) {
        for (int i = 0; i < boxs.size(); i++) {
            if (isBombbangOfBomberCollistion(boxs.get(i), index)) {
                Random random = new Random();
                generateItem(boxs.get(i).getX(), boxs.get(i).getY(), random.nextInt(3));
                boxs.remove(boxs.get(i));
            }
        }
    }

    private void bombbangOfBossesCollistionBoxs(int index) {
        for (int i = 0; i < boxs.size(); i++) {
            if (isBombbangOfBossesCollistion(boxs.get(i), index)) {
                Random random = new Random();
                generateItem(boxs.get(i).getX(), boxs.get(i).getY(), random.nextInt(3));
                boxs.remove(boxs.get(i));
            }
        }
    }

    private void bombbangOfBomberCollistionBosses(int index) {
        for (int i = 0; i < bosses.size(); i++) {
            if (isBombbangOfBomberCollistionBoss(bosses.get(i), index)) {
                PlayerWav playerWav = new PlayerWav("monster_die");
                playerWav.play();
                bosses.remove(bosses.get(i));
            }
        }
    }

    private void bombbangOfBossesCollistionBosses(int index) {
        for (int i = 0; i < bosses.size(); i++) {
            if (isBombbangOfBossesCollistionBoss(bosses.get(i), index)) {
                PlayerWav playerWav = new PlayerWav("monster_die");
                playerWav.play();
                bosses.remove(bosses.get(i));
            }
        }
    }

    private void bombbangOfBomberCollistionMonsters(int index) {
        for (int i = 0; i < monsters.size(); i++) {
            if (isBombbangOfBomberCollistionMonster(monsters.get(i), index)) {
                PlayerWav playerWav = new PlayerWav("monster_die");
                playerWav.play();
                monsters.remove(monsters.get(i));
            }
        }
    }

    private void bombbangOfBossesCollistionMonsters(int index) {
        for (int i = 0; i < monsters.size(); i++) {
            if (isBombbangOfBossesCollistionMonster(monsters.get(i), index)) {
                PlayerWav playerWav = new PlayerWav("monster_die");
                playerWav.play();
                monsters.remove(monsters.get(i));
            }
        }
    }


    public void bomberCollistionItems() {
        for (int i = 0; i < items.size(); i++) {
            if (isBomberCollistionItem(items.get(i))) {
                PlayerWav playerWav = new PlayerWav("item");
                playerWav.play();
                items.remove(items.get(i));
            }
        }
    }


    private boolean isBombbangOfBomberCollistion(MapItem mapItem, int index) {
        return bombbangsOfBomber.get(index).getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isBombbangOfBossesCollistion(MapItem mapItem, int index) {
        return bombbangsOfBosses.get(index).getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isBombbangOfBomberCollistionBoss(Boss boss, int index) {
        return bombbangsOfBomber.get(index).getRectangle().intersects(boss.getRectangle());
    }

    private boolean isBombbangOfBossesCollistionBoss(Boss boss, int index) {
        return bombbangsOfBosses.get(index).getRectangle().intersects(boss.getRectangle());
    }

    private boolean isBombbangOfBomberCollistionMonster(Monster monster, int index) {
        return bombbangsOfBomber.get(index).getRectangle().intersects(monster.getRectangle());
    }

    private boolean isBombbangOfBossesCollistionMonster(Monster monster, int index) {
        return bombbangsOfBosses.get(index).getRectangle().intersects(monster.getRectangle());
    }

    private boolean isBombbangOfBomberCollistionBomber(int index) {
        return bombbangsOfBomber.get(index).getRectangle().intersects(bomber.getRectangle());
    }

    private boolean isBombbangOfBossesCollistionBomber(int index) {
        return bombbangsOfBosses.get(index).getRectangle().intersects(bomber.getRectangle());
    }

    private boolean isBomberCollistion(MapItem mapItem) {
        return bomber.getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isBossesCollistion(MapItem mapItem, int index) {
        return bosses.get(index).getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isMonstersCollistion(MapItem mapItem, int index) {
        return monsters.get(index).getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isBomberCollistionItem(Item item) {
        return bomber.getRectangle().intersects(item.getRectangle());
    }


    private boolean isBombCollistionLeftRocks(int index) {
        for (int i = 0; i < rocks.size(); i++) {
            isBombCollistionLeft(rocks.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionRightRocks(int index) {
        for (int i = 0; i < rocks.size(); i++) {
            isBombCollistionRight(rocks.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionUpRocks(int index) {
        for (int i = 0; i < rocks.size(); i++) {
            isBombCollistionUp(rocks.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionDownRocks(int index) {
        for (int i = 0; i < rocks.size(); i++) {
            isBombCollistionDown(rocks.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionLeftStones(int index) {
        for (int i = 0; i < stones.size(); i++) {
            isBombCollistionLeft(stones.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionRightStones(int index) {
        for (int i = 0; i < stones.size(); i++) {
            isBombCollistionRight(stones.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionUpStones(int index) {
        for (int i = 0; i < stones.size(); i++) {
            isBombCollistionUp(stones.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionDownStones(int index) {
        for (int i = 0; i < stones.size(); i++) {
            isBombCollistionDown(stones.get(i), index);
            return true;
        }
        return false;
    }

    private boolean isBombCollistionLeft(MapItem mapItem, int index) {
        bombsOfBomber.get(index).getRectangle().setLocation(bombsOfBomber.get(index).getX() - 1, bombsOfBomber.get(index).getY());
        return bombsOfBomber.get(index).getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isBombCollistionRight(MapItem mapItem, int index) {
        bombsOfBomber.get(index).getRectangle().setLocation(bombsOfBomber.get(index).getX() + 1, bombsOfBomber.get(index).getY());
        return bombsOfBomber.get(index).getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isBombCollistionUp(MapItem mapItem, int index) {
        bombsOfBomber.get(index).getRectangle().setLocation(bombsOfBomber.get(index).getX(), bombsOfBomber.get(index).getY() - 1);
        return bombsOfBomber.get(index).getRectangle().intersects(mapItem.getRectangle());
    }

    private boolean isBombCollistionDown(MapItem mapItem, int index) {
        bombsOfBomber.get(index).getRectangle().setLocation(bombsOfBomber.get(index).getX(), bombsOfBomber.get(index).getY() + 1);
        return bombsOfBomber.get(index).getRectangle().intersects(mapItem.getRectangle());
    }


    public void countdownTimeOfBomber() {
        if (timeDownOfBomber > 0) {
            timeDownOfBomber -= 8;
        } else {
            timeDownOfBomber = 0;
        }
    }

    public void countdownTimeOfBosses() {
        if (timeDownOfBosses > 0) {
            timeDownOfBosses -= 8;
        } else {
            timeDownOfBosses = 0;
        }
    }

    public void drawBackgroundGamePlay(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_BACKGROUND_GAME_PLAY, 0, 0,
                GameManager.COLUMNS * MapItem.SIZE,
                GameManager.ROWS * MapItem.SIZE, null);
    }

    public void drawBackgroundMenu(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_BACKGROUND_MENU, 0, 0,
                GameManager.COLUMNS * MapItem.SIZE,
                GameManager.ROWS * MapItem.SIZE, null);
    }

    public void drawYouWin(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_YOU_WIN, 300, 100,
                400, 400, null);
    }

    public void drawGameOver(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_GAME_OVER, 300, 100,
                400, 400, null);
    }

}
