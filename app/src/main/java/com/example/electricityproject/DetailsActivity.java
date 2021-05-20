package com.example.electricityproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.view.ToolBar;

public class DetailsActivity extends AppCompatActivity {
    private ToolBar toolbar;
    private ImageView detailsImg;
    private TextView detailsName;
    private TextView detailsPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        detailsImg = (ImageView) findViewById(R.id.details_img);
        detailsName = (TextView) findViewById(R.id.details_name);
        detailsPrice = (TextView) findViewById(R.id.details_price);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");
        String price = intent.getStringExtra("price");
        if (img!=null){
            Glide.with(this).load(img).into(detailsImg);
        }
        if (name!=null){
            detailsName.setText(name+"");
        }
        if (price!=null){
            detailsPrice.setText("￥"+price);
        }

    }
}