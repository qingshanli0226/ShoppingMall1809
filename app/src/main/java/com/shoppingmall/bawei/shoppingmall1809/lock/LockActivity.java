package com.shoppingmall.bawei.shoppingmall1809.lock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.shoppingmall.bawei.shoppingmall1809.R;

import java.util.concurrent.locks.ReentrantLock;

public class LockActivity extends AppCompatActivity {
    private int sumValue = 0;
    private Object object = new Object();
    private Object object2 = new Object();
    private static int sValue = 0;
    private static int lValue = 0;
    private static ReentrantLock lock = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        findViewById(R.id.newInstance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LockActivity.this,LockActivity.class);
                startActivity(intent);
            }
        });

        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i  = 0;
                while (i < 10000000) {
                        //addValue();
                    addLValue();

                        i++;

                }
                Log.d("Thread1 LQS lValue = ", lValue + "");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i  = 0;
                while (i < 10000000) {
                        //addValue();
                    addLValue();

                        i++;
                }
                Log.d("Thread2 LQS lValue = ", lValue + "");
            }
        }).start();
    }

    public static void addValue() {
        synchronized (LockActivity.class) {
            sValue = sValue + 1;
        }
    }

    public void addLValue () {
        lock.lock();//获取锁资源
        lValue = lValue + 1;
        lock.unlock();//释放锁资源
    }
}
