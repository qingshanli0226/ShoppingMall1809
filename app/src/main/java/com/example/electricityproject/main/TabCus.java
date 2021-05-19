package com.example.electricityproject.main;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabCus implements CustomTabEntity {
    private String tit;
    private int UnIcon;
    private int Icon;

    public TabCus(String tit, int unIcon, int icon) {
        this.tit = tit;
        UnIcon = unIcon;
        Icon = icon;
    }

    @Override
    public String getTabTitle() {
        return tit;
    }

    @Override
    public int getTabSelectedIcon() {
        return Icon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return UnIcon;
    }
}
