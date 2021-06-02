package com.shoppingmall.bawei.shoppingmall1809.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.fiannce.bawei.common.LogUtil;
import com.shoppingmall.bawei.shoppingmall1809.R;

public class ShopHandlerActivity extends AppCompatActivity {

    private ShopHandler shopHandler;
    private ShopHandler shopHandler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_handler);
        initThread();

        findViewById(R.id.btnSendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("threadId:" + Thread.currentThread().getId());
                shopHandler.sendEmptyMessage(10000);
                shopHandler.sendEmptyMessage(20000);
                shopHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.d("run---" +"threadId:" + Thread.currentThread().getId());
                    }
                });

                shopHandler2.sendEmptyMessage(50000);
                shopHandler2.sendEmptyMessage(60000);
            }
        });

        findViewById(R.id.btnSendDelayMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopHandler.sendEmptyDelayMessage(70000, 1*1000);
                shopHandler.sendEmptyDelayMessage(80000, 500);
                shopHandler.sendEmptyDelayMessage(90000, 2*1000);
                shopHandler.sendEmptyDelayMessage(100000, 1*1000);
            }
        });

    }

    private void initThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopLooper.prepare();
                shopHandler = new ShopHandler(new ShopHandler.CallBack() {
                    @Override
                    public boolean handleMessage(ShopMessage shopMessage) {
                        if (shopMessage.what == 20000) {
                            LogUtil.d("message what = "+shopMessage.what+"callback threadId:" + Thread.currentThread().getId());
                            return true;
                        }
                        return false;
                    }
                }){
                    @Override
                    public void handleMessage(ShopMessage shopMessage) {
                        LogUtil.d("message what = "+shopMessage.what+" threadId:" + Thread.currentThread().getId());
                    }
                };
                shopHandler2 = new ShopHandler() {
                    @Override
                    public void handleMessage(ShopMessage shopMessage) {
                        LogUtil.d("message what = "+shopMessage.what+"shopHandler2 threadId:" + Thread.currentThread().getId());

                    }
                };
                ShopLooper.loop();
            }
        }).start();
    }
}
