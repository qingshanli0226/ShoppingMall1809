package com.shoppingmall.bawei.shoppingmall1809.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.fiannce.bawei.common.LogUtil;
import com.shoppingmall.bawei.shoppingmall1809.R;

public class HandlerActivity extends AppCompatActivity {
    private Handler handlerThread;
    private Handler handlerL;
    private Handler handlerT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        LogUtil.d( "onCreate 线程ID：" + Thread.currentThread().getId());

        handlerA.sendEmptyMessage(1);
        handlerA.sendEmptyMessage(3);
        handlerA.sendEmptyMessage(5);
        handlerA.sendEmptyMessage(2);
        handlerA.sendEmptyMessage(4);
        handlerA.sendEmptyMessage(6);
        handlerA.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HandlerActivity.this,HandlerTimeActivity.class);
                startActivity(intent);
            }
        });


        initHandlerInThread();

        findViewById(R.id.btnSendThreadMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.sendEmptyMessage(10);
                handlerL.sendEmptyMessage(20);
                handlerT = new Handler(handlerThread.getLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        LogUtil.d("handlerT process msg:" + msg.what+ " 线程ID：" + Thread.currentThread().getId());

                    }
                };
                handlerT.sendEmptyMessage(30);
            }
        });

    }

    //在子线程中怎么样使用Handler
    private void initHandlerInThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();//它会在子线程中的私有存储空间里存储一个Looper实例.Looper在构造实例时会创建一消息队列
                //做准备工作
                handlerL = new Handler(Looper.getMainLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        LogUtil.d("handlerL process msg:" + msg.what+ " 线程ID：" + Thread.currentThread().getId());
                    }
                };
                handlerThread = new Handler() {//如果希望handlerThread发送的消息由子线程来处理，它需要在调用Looper.prepare后，实例化该handlerThread
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        LogUtil.d("handlerThread process msg:" + msg.what+ " 线程ID：" + Thread.currentThread().getId());
                    }
                };//Handler的构造函数会获取当前实例化该handler线程的looper，并且让Handler的成员变量messageQueue指向looper的消息队列
                Looper.loop();//looper开始遍历消息队列的消息，获取到消息后，处理该消息

            }
        }).start();
    }


    private Handler handlerA = new Handler(new Handler.Callback() {
        //第一个是Callback的回调接口
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what%2 == 0) {
                LogUtil.d("HandlerA process msg:" +" --Callback: " + msg.what + " 线程ID：" + Thread.currentThread().getId());
                return true;//如果返回true，发送的消息将优先由该callback处理完毕，Handler的handleMessage将不会调用
            }
            return false;
        }
    }) {
        //是handler的回调接口
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (Looper.getMainLooper() == handlerA.getLooper()) {//判断是否在主线程中
                LogUtil.d("HandlerA process msg:" + msg.what + " 线程ID：" + Thread.currentThread().getId());
            }
        }
    };

    private Handler handlerB = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (Looper.getMainLooper() == handlerA.getLooper()) {
                LogUtil.d("HandlerB process msg:" + msg.what+ " 线程ID：" + Thread.currentThread().getId());
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.removeCallbacksAndMessages(null);
        handlerThread.getLooper().quit();
    }
}
