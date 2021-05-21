package com.example.electricityproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.view.ToolBar;

public class DetailsActivity extends AppCompatActivity {
    private ToolBar toolbar;
    private ImageView detailsImg;
    private TextView detailsName;
    private TextView detailsPrice;
    private LinearLayout addShop;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        detailsImg = (ImageView) findViewById(R.id.details_img);
        detailsName = (TextView) findViewById(R.id.details_name);
        detailsPrice = (TextView) findViewById(R.id.details_price);
        addShop = (LinearLayout) findViewById(R.id.add_shop);
        btnAdd = (Button) findViewById(R.id.btn_add);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");
        String price = intent.getStringExtra("price");
        if (img!=null){
            Glide.with(this).load(Constants.BASE_URl_IMAGE+img).into(detailsImg);
        }
        if (name!=null){
            detailsName.setText(name+"");
        }
        if (price!=null){
            detailsPrice.setText("￥"+price);
        }
        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightImgClick() {
                PopupWindow popupWindow = new PopupWindow(DetailsActivity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(200);
                View inflate = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_more,null);
                popupWindow.setContentView(inflate);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(toolbar,0,0);
            }

            @Override
            public void onRightTvClick() {

            }
        });
//        PopupWindow popupWindow1 = new PopupWindow(DetailsActivity.this);
//        popupWindow1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow1.setHeight(90);
//        View view = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_shop, null);
//        popupWindow1.setContentView(view);
//        popupWindow1.showAsDropDown(toolbar,0,500);

    }
}