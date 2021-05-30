package com.example.framework.manager;

import android.content.Intent;

import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

import java.util.ArrayList;
import java.util.List;

public class PaySendCacheManager {
    public static PaySendCacheManager paySendCacheManager;

    public synchronized static PaySendCacheManager getInstance() {
        if (paySendCacheManager == null) {
            paySendCacheManager = new PaySendCacheManager();
        }
        return paySendCacheManager;
    }

    private FindForSendBean findForSendBean;
    private FindForPayBean findForPayBean;


    //获取数量
    public int getOneIndex() {
        int size = findForPayBean.getResult().size();
        return size;
    }
    private int a=1;
    public void  setonIndex(int p){
        this.a=p;
    }

    //获取数量
    public int getTwoIndex() {
        return findForSendBean.getResult().size();
    }

    public FindForPayBean getFindForPayBean() {
        return findForPayBean;
    }

    public FindForSendBean getFindForSendBean() {
        return findForSendBean;
    }

    public void setFindForSendBean(FindForSendBean findForSendBean) {
        this.findForSendBean = findForSendBean;

    }

    public void setFindForPayBean(FindForPayBean findForPayBean) {
        this.findForPayBean = findForPayBean;

    }


}
