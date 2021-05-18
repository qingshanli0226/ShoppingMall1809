package com.example.net.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MainBean implements CustomTabEntity {

    private String title;
    private int selectIcon;
    private int unselectIcon;

    public MainBean(String title, int selectIcon, int unselectIcon) {
        this.title = title;
        this.selectIcon = selectIcon;
        this.unselectIcon = unselectIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectIcon;
    }
}
