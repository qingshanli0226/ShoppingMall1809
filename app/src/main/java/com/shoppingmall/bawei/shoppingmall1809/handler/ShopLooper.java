package com.shoppingmall.bawei.shoppingmall1809.handler;

public class ShopLooper {
    private static ThreadLocal<ShopLooper> shopLooperThreadLocal = new ThreadLocal<>();
    public ShopMessageQueue shopMessageQueue;

    public static ShopLooper getLooper() {
        return shopLooperThreadLocal.get();
    }

    public ShopLooper() {
        shopMessageQueue = new ShopMessageQueue();
    }

    public static void prepare() {
        ShopLooper shopLooper = new ShopLooper();
        shopLooperThreadLocal.set(shopLooper);
    }

    public static void loop() {
        for(;;) {
            ShopLooper shopLooper = shopLooperThreadLocal.get();
            ShopMessage shopMessage = shopLooper.shopMessageQueue.next();
            shopMessage.target.dispatchMessage(shopMessage);
        }
    }
}
