package com.shoppingmall.bawei.shoppingmall1809.handler;

import android.telecom.Call;

import com.fiannce.bawei.common.LogUtil;

public class ShopHandler {
    public ShopMessageQueue shopMessageQueue;
    public CallBack mCallBack;

    public ShopHandler(){
        ShopLooper shopLooper = ShopLooper.getLooper();
        shopMessageQueue = shopLooper.shopMessageQueue;
    }
    public ShopHandler(CallBack callBack) {
        ShopLooper shopLooper = ShopLooper.getLooper();
        shopMessageQueue = shopLooper.shopMessageQueue;
        mCallBack = callBack;
    }

    public void sendEmptyDelayMessage(int what,int delayMills) {
        ShopMessage shopMessage = new ShopMessage();
        shopMessage.what = what;
        shopMessage.when = System.currentTimeMillis()+delayMills;
        shopMessage.target = this;
        shopMessageQueue.enqueueMessage(shopMessage);
    }

    public void sendEmptyMessage(int what) {
        sendEmptyDelayMessage(what,0);
    }

    public void post(Runnable runnable) {
        ShopMessage shopMessage = new ShopMessage();
        shopMessage.callBack = runnable;
        shopMessage.target = this;
        shopMessageQueue.enqueueMessage(shopMessage);
    }

    public void dispatchMessage(ShopMessage shopMessage) {
        if (shopMessage.callBack!=null) {
            shopMessage.callBack.run();
            return;
        } else {
            if (mCallBack!=null) {
                if (mCallBack.handleMessage(shopMessage)) {
                    return;
                }
            }
            handleMessage(shopMessage);
        }
    }

    public void handleMessage(ShopMessage shopMessage) {
        LogUtil.d("what = " + shopMessage.what);
    }

    public interface CallBack {
        boolean handleMessage(ShopMessage shopMessage);
    }
}
