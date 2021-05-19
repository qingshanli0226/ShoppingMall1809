package com.example.myapplication;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MainCustomTabEntity implements CustomTabEntity {
    private String title;
    private int select;
    private int unselect;

    public MainCustomTabEntity(String title, int select, int unselect) {
        this.title = title;
        this.select = select;
        this.unselect = unselect;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return select;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselect;
    }
}
