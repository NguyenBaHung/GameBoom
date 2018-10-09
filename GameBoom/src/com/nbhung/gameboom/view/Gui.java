package com.nbhung.gameboom.view;

import com.nbhung.gameboom.manager.GameManager;
import com.nbhung.gameboom.model.MapItem;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame implements ViewInializer, OnMenuSelectListener {
    public static final int WIDTH_FRAME = GameManager.COLUMNS * MapItem.SIZE;
    public static final int HEIGHT_FRAME = GameManager.ROWS * MapItem.SIZE;

    private GameBoomPanel gameBoomPanel;
    private MenuPanel menuPanel;


    public Gui() {
        initContainer();
        initComponent();
        registerListener();
    }

    @Override
    public void initContainer() {
        setTitle("Game boom");
        setLayout(new CardLayout());
        //setSize(WIDTH_FRAME,HEIGHT_FRAME);
        getContentPane().setPreferredSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void initComponent() {
        menuPanel = new MenuPanel();
        getContentPane().add(menuPanel);
    }

    @Override
    public void registerListener() {
        menuPanel.setOnMenuSelectListener(this);
    }

    @Override
    public void onMenuSelectedListener(String name) {
        switch (name) {
            case "Play_game":
                getContentPane().remove(menuPanel);

                gameBoomPanel = new GameBoomPanel();
                getContentPane().add(gameBoomPanel);
                gameBoomPanel.startGame();
                gameBoomPanel.requestFocusInWindow();
                getContentPane().validate();
                break;
            default:
                break;
        }
    }
}
