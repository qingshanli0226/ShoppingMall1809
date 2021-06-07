package com.shoppingmall.bawei.shoppingmall1809.anr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.shoppingmall.bawei.shoppingmall1809.R;

import java.util.concurrent.locks.ReentrantLock;

public class AnrActivity extends AppCompatActivity {
    boolean flag = false;
    boolean flag2 = false;
    long value = 0;
    ReentrantLock lock1 = new ReentrantLock();
    ReentrantLock lock2 = new ReentrantLock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        findViewById(R.id.btnClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    while (true) {
                        if (value < 1 * 1000 * 1000 * 1000) {
                            value++;
                        } else {
                            value = 0;
                            break;

                        }



                }

              /*  try {
                    Thread.sleep(1*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });

       findViewById(R.id.btnDeadLock).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (flag2!=true) {
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           methodTwo();
                       }
                   }).start();
                   flag2=true;
                   methodOne();

               }
           }
       });

       findViewById(R.id.btnRv).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(AnrActivity.this, RecyAnrActivity.class);
               startActivity(intent);
           }
       });
    }

    private void methodOne() {
        lock1.lock();
        new Throwable().printStackTrace();
        Log.d("LQS", "methodOne start");
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        methodTwo();
        Log.d("LQS", "methodOne end");
        lock1.unlock();
    }

    private void methodTwo() {
        lock2.lock();
        new Throwable().printStackTrace();
        Log.d("LQS", "methodTwo start");
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        methodOne();
        Log.d("LQS", "methodTwo end");

        lock2.unlock();
    }
}
