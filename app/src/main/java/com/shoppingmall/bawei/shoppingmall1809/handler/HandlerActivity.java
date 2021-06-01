package com.shoppingmall.bawei.shoppingmall1809.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.fiannce.bawei.common.LogUtil;
import com.shoppingmall.bawei.shoppingmall1809.R;

public class HandlerActivity extends AppCompatActivity {
    private Handler handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        LogUtil.d( "onCreate 线程ID：" + Thread.currentThread().getId());

        handlerA.sendEmptyMessage(1);
        handlerA.sendEmptyMessage(3);
        handlerA.sendEmptyMessage(5);
        handlerB.sendEmptyMessage(2);
        handlerB.sendEmptyMessage(4);
        handlerB.sendEmptyMessage(6);
        handlerB.post(new Runnable() {
            @Override
            public void run() {

            }
        });


        initHandlerInThread();

        findViewById(R.id.btnSendThreadMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.sendEmptyMessage(10);
            }
        });

    }

    //在子线程中怎么样使用Handler
    private void initHandlerInThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //做准备工作
                //Looper.prepare();//它会在子线程中的私有存储空间里存储一个Looper实例.Looper在构造实例时会创建一消息队列
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


    private Handler handlerA = new Handler() {
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
}
