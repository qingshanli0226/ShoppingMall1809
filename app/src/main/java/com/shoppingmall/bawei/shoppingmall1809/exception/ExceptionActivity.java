package com.shoppingmall.bawei.shoppingmall1809.exception;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shoppingmall.bawei.shoppingmall1809.R;

public class ExceptionActivity extends AppCompatActivity {
    private Button btnException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
       /* btnException = findViewById(R.id.btnException);*/


        findViewById(R.id.btnException).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ExceptionActivity.this, "运行时异常", Toast.LENGTH_SHORT).show();
                btnException.setText("异常测试");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //btnException.setText("异常测试");
                    }
                }).start();
            }
        });

    }
}
