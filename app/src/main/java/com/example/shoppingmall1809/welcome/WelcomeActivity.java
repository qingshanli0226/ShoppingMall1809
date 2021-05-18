package com.example.shoppingmall1809.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.framework.service.ShopService;
import com.example.shoppingmall1809.main.MainActivity;
import com.example.shoppingmall1809.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int i=3;
            @Override
            public void run() {
                if (i<=0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timer.cancel();

                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }

                i--;
            }
        },0,1000);


        Intent intent = new Intent(this, ShopService.class);
        startService(intent);
    }
}