package com.nbhung.gameboom.view;

import com.nbhung.gameboom.manager.GameManager;
import com.nbhung.gameboom.model.Bomber;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

public class GameBoomPanel extends BasePanel {
    GameManager gameManager;
    BitSet bitSet;
    KeyAdapter keyAdapter;

    @Override
    public void initContainer() {
        setLayout(null);
        setBackground(Color.BLUE);
        setFocusable(true);
    }

    @Override
    public void initComponent() {
        gameManager = new GameManager();
        gameManager.generateMap();
        gameManager.generateBosses();
        gameManager.generateBomber();
        gameManager.generateMonsters();
        bitSet = new BitSet();
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                bitSet.set(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bitSet.set(e.getKeyCode(), false);
            }
        };
        addKeyListener(keyAdapter);
    }

    @Override
    public void registerListener() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        gameManager.drawBackgroundGamePlay(graphics2D);
        gameManager.drawStones(graphics2D);
        gameManager.drawRocks(graphics2D);
        gameManager.drawWoods(graphics2D);
        gameManager.drawBoxs(graphics2D);
        gameManager.drawBomber(graphics2D);
        gameManager.drawBosses(graphics2D);
        gameManager.drawMonsters(graphics2D);
        gameManager.drawBombsOfBomber(graphics2D);
        gameManager.drawBombsOfBosses(graphics2D);
        gameManager.drawBombbangsOfBomber(graphics2D);
        gameManager.drawBombbangsOfBosses(graphics2D);
        gameManager.drawItems(graphics2D);
        if (gameManager.finishGame()) {
            gameManager.drawYouWin(graphics2D);
        }
        if (gameManager.dieBomber()) {
            gameManager.drawGameOver(graphics2D);
        }
    }

    public void startGame() {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                while (true) {
                    if (bitSet.get(KeyEvent.VK_LEFT)) {
                        gameManager.moveBomber(Bomber.LEFT);
                    }
                    if (bitSet.get(KeyEvent.VK_RIGHT)) {
                        gameManager.moveBomber(Bomber.RIGHT);
                    }
                    if (bitSet.get(KeyEvent.VK_UP)) {
                        gameManager.moveBomber(Bomber.UP);
                    }
                    if (bitSet.get(KeyEvent.VK_DOWN)) {
                        gameManager.moveBomber(Bomber.DOWN);
                    }
                    if (bitSet.get(KeyEvent.VK_SPACE)) {
                        gameManager.generateBombsOfBomber();
                    }
                    gameManager.moveBosses();
                    gameManager.moveMonsters();
                    gameManager.generateBombsOfBosses();
                    gameManager.generateBombbangsOfBomber();
                    gameManager.generateBombbangsOfBosses();
                    gameManager.countdownTimeOfBomber();
                    gameManager.countdownTimeOfBosses();
                    gameManager.deleteBombbangsOfBomberAndItems();
                    gameManager.deleteBombbangsOfBossesAndItems();
                    gameManager.bomberCollistionItems();
                    repaint();
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread backgroundThread = new Thread(runnable);
        backgroundThread.start();

    }

}

