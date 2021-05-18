package com.shoppingmall.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CustomBean implements CustomTabEntity {
    private String tabTitle;
    private int selectIcon;
    private int unSelectIcon;

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }

    public int getUnSelectIcon() {
        return unSelectIcon;
    }

    public void setUnSelectIcon(int unSelectIcon) {
        this.unSelectIcon = unSelectIcon;
    }

    public CustomBean() {
    }

    public CustomBean(String tabTitle, int selectIcon, int unSelectIcon) {
        this.tabTitle = tabTitle;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectIcon;
    }
}