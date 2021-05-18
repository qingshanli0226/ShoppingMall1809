package com.example.shoppingmallsix.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MainBean implements CustomTabEntity {

    private String title;
    private int selectIcon;
    private int unselectIcon;

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
