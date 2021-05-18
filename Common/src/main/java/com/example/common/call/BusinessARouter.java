package com.example.common.call;

import android.content.Context;
import android.os.Bundle;

public class BusinessARouter {
    private iUserManager userManager;
    private iAppManager appManager;

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

    public interface iUserManager{
        void OpenUserActivity(Context context,Bundle bundle);

    }

    public interface iAppManager{
        void OpenMainActivity(Context context,Bundle bundle);

    }


}
