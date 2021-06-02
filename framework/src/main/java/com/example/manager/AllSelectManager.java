package com.example.manager;

public class AllSelectManager {
    private static AllSelectManager manager;

    public static AllSelectManager getInstance() {
        if (manager==null){
            manager=new AllSelectManager();
        }
        return manager;
    }
    private boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
