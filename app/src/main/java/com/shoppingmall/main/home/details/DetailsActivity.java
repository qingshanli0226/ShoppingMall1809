package com.shoppingmall.main.home.details;


import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.HomeBean;

public class DetailsActivity extends BaseActivity {

    private android.widget.ImageView detailBack;
    private android.widget.ImageView detailMenu;
    private android.widget.ImageView detailImg;
    private android.widget.TextView detailTitle;
    private android.widget.TextView detailPrice;
    private PopupWindow popupWindow;
    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {

        detailBack = (ImageView) findViewById(R.id.detailBack);
        detailMenu = (ImageView) findViewById(R.id.detailMenu);
        detailImg = (ImageView) findViewById(R.id.detailImg);
        detailTitle = (TextView) findViewById(R.id.detailTitle);
        detailPrice = (TextView) findViewById(R.id.detailPrice);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //退出当前页面
        backActivity();
        //打开popWindow
        openPopWindow();
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        switch (type){
            case "secKill":
                HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = (HomeBean.ResultBean.SeckillInfoBean.ListBean) intent.getSerializableExtra("data");
                Glide.with(this).load(Constants.IMG_HTTPS+listBean.getFigure()).into(detailImg);
                detailTitle.setText(""+listBean.getName());
                detailTitle.setText(""+listBean.getCover_price());
                break;
            case "recommend":
                HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = (HomeBean.ResultBean.RecommendInfoBean) intent.getSerializableExtra("data");
                Glide.with(this).load(Constants.IMG_HTTPS+recommendInfoBean.getFigure()).into(detailImg);
                detailTitle.setText(""+recommendInfoBean.getName());
                detailTitle.setText(""+recommendInfoBean.getCover_price());
                break;
            case "hot":
                HomeBean.ResultBean.HotInfoBean hotInfoBean = (HomeBean.ResultBean.HotInfoBean) intent.getSerializableExtra("data");
                Glide.with(this).load(Constants.IMG_HTTPS+hotInfoBean.getFigure()).into(detailImg);
                detailTitle.setText(""+hotInfoBean.getName());
                detailTitle.setText(""+hotInfoBean.getCover_price());
                break;
        }
    }
    //打开popWindow
    private void openPopWindow() {
        detailMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow = new PopupWindow();
                View inflate = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.detail_pop_wiondow_layout, null);
                popupWindow.setContentView(inflate);
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(inflate, Gravity.TOP,0,0);
            }
        });
    }
    //退出当前Activity
    private void backActivity(){
        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }
}