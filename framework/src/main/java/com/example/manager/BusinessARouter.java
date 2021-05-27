package com.example.manager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

public class BusinessARouter {

    private iUserManager userManager;
    private iAppManager appManager;
    private iPayManager payManager;

    public BusinessARouter() {
    }
    private static BusinessARouter businessARouter;

    public static BusinessARouter getInstance() {
        if (businessARouter==null){
            businessARouter=new BusinessARouter();
        }
        return businessARouter;
    }

    public iUserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(iUserManager userManager) {
        this.userManager = userManager;
    }

    public iAppManager getAppManager() {
        return appManager;
    }

    public void setAppManager(iAppManager appManager) {
        this.appManager = appManager;
    }

    public iPayManager getPayManager() {
        return payManager;
    }

    public void setPayManager(iPayManager payManager) {
        this.payManager = payManager;
    }

    public interface iUserManager{
        void OpenLogActivity(Context context,Bundle bundle);

    }

    public interface iAppManager{
        void OpenMainActivity(Context context,Bundle bundle);

    }

    public interface iPayManager{
        void OpenPayActivity(Context context, Build build);
    }


}
