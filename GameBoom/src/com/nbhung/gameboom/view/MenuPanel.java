package com.nbhung.gameboom.view;

import com.nbhung.gameboom.manager.GameManager;
import com.nbhung.gameboom.manager.PlayerWav;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MenuPanel extends BasePanel implements ActionListener {
    public static final String ACTION_PLAY_GAME = "PLAY";

    private JLabel lbTitle;
    private JButton btnPlay;
    private OnMenuSelectListener onMenuSelectListener;
    GameManager gameManager;

    public void setOnMenuSelectListener(OnMenuSelectListener onMenuSelectListener) {
        this.onMenuSelectListener = onMenuSelectListener;
    }

    @Override
    public void initContainer() {
        setLayout(null);
        setBackground(Color.CYAN);
    }

    @Override
    public void initComponent() {
        gameManager = new GameManager();
        Font titleFont = new Font("Arial", Font.BOLD, 30);
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        FontMetrics titleFontMeTrics = this.getFontMetrics(titleFont);

        lbTitle = new JLabel();
        lbTitle.setText("GAME BOOM :");
        lbTitle.setFont(titleFont);

        int lbTitleWidth = titleFontMeTrics.stringWidth(lbTitle.getText());
        int lbTitleHeight = titleFontMeTrics.getHeight();
        lbTitle.setSize(lbTitleWidth, lbTitleHeight);
        lbTitle.setLocation((Gui.WIDTH_FRAME - lbTitleWidth) / 3, 20);
        lbTitle.setForeground(Color.BLACK);
        add(lbTitle);

        btnPlay = new JButton();
        btnPlay.setText("PLAY");
        btnPlay.setFont(buttonFont);
        btnPlay.setBackground(Color.CYAN);
        btnPlay.setBounds(530, lbTitle.getY(), 180, 30);
        add(btnPlay);
    }

    @Override
    public void registerListener() {
        btnPlay.setActionCommand(ACTION_PLAY_GAME);
        btnPlay.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_PLAY_GAME:
                onMenuSelectListener.onMenuSelectedListener("Play_game");
                PlayerWav playerWav = new PlayerWav("menu");
                playerWav.play();
                break;
            default:
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        gameManager.drawBackgroundMenu(graphics2D);

    }
}
