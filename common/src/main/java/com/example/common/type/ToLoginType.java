package com.example.common.type;

public class ToLoginType {
    private static ToLoginType type;

    public static ToLoginType getInstance() {
        if (type==null){
            type=new ToLoginType();
        }
        return type;
    }
    private String activityType;

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
