package com.nbhung.gameboom.view;

import javax.swing.*;

public abstract class BasePanel extends JPanel implements ViewInializer {
    public BasePanel(){
        initContainer();
        initComponent();
        registerListener();
    }
}
