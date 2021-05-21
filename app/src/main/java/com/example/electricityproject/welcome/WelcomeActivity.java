package com.example.electricityproject.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.SPUtility;
import com.example.electricityproject.R;
import com.example.electricityproject.main.MainActivity;
import com.example.user.auto.AutoService;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {
    private int time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

            Log.i("zx", "onCreate: "+SPUtility.getString(this));
            startService(new Intent(this, AutoService.class));

        time=3;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                if (time<=0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timer.cancel();
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        }
                    });
                }
            }
        },0,1000);
    }


}