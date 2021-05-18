package com.example.electricityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.common.call.BusinessARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void aaa(View view) {
        BusinessARouter.getInstance().getUserManager().OpenLogActivity(this,null);
    }
}