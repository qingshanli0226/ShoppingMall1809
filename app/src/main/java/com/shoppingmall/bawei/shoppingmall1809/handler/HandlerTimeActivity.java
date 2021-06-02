package com.shoppingmall.bawei.shoppingmall1809.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;

import com.fiannce.bawei.common.LogUtil;
import com.shoppingmall.bawei.shoppingmall1809.R;

public class HandlerTimeActivity extends AppCompatActivity {

    private HandlerThread handlerThread = new HandlerThread("1809");
    private Handler handlerT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_time);
        handler.sendEmptyMessageDelayed(1,5*1000);
        handler.sendEmptyMessageDelayed(2,0);
        handler.sendEmptyMessageDelayed(3,3*1000);

        handlerThread.start();
        handlerT = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                LogUtil.d("thread id:"+Thread.currentThread().getId());
            }
        };
        handlerT.sendEmptyMessage(11);

    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LogUtil.d(msg.what+"");

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handlerThread.quit();
    }
}
