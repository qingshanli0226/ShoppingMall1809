package com.shoppingmall.bawei.shoppingmall1809.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShopMessageQueue {
    List<ShopMessage> shopMessageList = new ArrayList<>();
    private Timer timer = new Timer();

    public synchronized ShopMessage next() {
        for(;;) {

            if (shopMessageList.size() > 0) {
                ShopMessage shopMessage = shopMessageList.get(0);
                long now = System.currentTimeMillis();//当前时间
                long timeOut = shopMessage.when-now;
                if (timeOut>0) {//如果大于0代表着该条消息还没有到执行时间，需要启动超时定时器，然后睡眠等待定时器唤醒
                     startTimerTask(timeOut);
                    try {
                        wait();
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    shopMessageList.remove(shopMessage);
                    return shopMessage;
                }
            } else {
                try {
                    wait();
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private synchronized void notifyLooper() {
        //当定时器启动后，需要唤醒looper
        notifyAll();
    }


    private void startTimerTask(long timeOut) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                notifyLooper();
            }
        },timeOut);
    }

    //向队列中插入一条消息
    public synchronized void enqueueMessage(ShopMessage shopMessage) {
        int i = 0;
        if (shopMessageList.size() == 0) {
            shopMessageList.add(shopMessage);
            notifyAll();
            return;
        }
        //按照延迟时间的大小将消息在消息队里里排序，延迟小的放到队列的前面。如果两个消息的延迟时间一样，会把消息按照发送时间顺序进行摆放，发送
        //时间早的，放在前面
        for(; i < shopMessageList.size();i++) {
            if (shopMessage.when>=shopMessageList.get(i).when) {
                continue;
            } else {
                shopMessageList.add(i,shopMessage);//如果新消息小于当前队列的该条消息时，则把新消息放到遍历到的队列消息的前面
                break;
            }
        }
        if (i == shopMessageList.size()) {//遍历完后仍然没有找到延迟时间大于新消息的延迟时间的，将该新消息存放到队列的尾部
            shopMessageList.add(shopMessage);
        }

        notifyAll();//新消息插入后，需要唤醒一次looper
    }
}
