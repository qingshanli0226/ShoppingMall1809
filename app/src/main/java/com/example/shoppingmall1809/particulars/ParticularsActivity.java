package com.example.shoppingmall1809.particulars;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;

import java.io.Serializable;

public class ParticularsActivity extends BaseActivity {
    private ToolBar toolbar;
    private ImageView particularsImg;
    private TextView particularsText;
    private TextView particularsPrice;
    private LinearLayout toShopCar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();

        int code = intent.getIntExtra("code", 0);
        if (code==1){
            HoemBean.ResultBean.RecommendInfoBean recommend = (HoemBean.ResultBean.RecommendInfoBean) intent.getSerializableExtra("recommend");

            Glide.with(this).load(Constants.BASE_URl_IMAGE +recommend.getFigure()).into(particularsImg);
            particularsText.setText(recommend.getName());
            particularsPrice.setText("￥"+recommend.getCover_price());
        }else if (code==2){
            HoemBean.ResultBean.HotInfoBean hotInfo = (HoemBean.ResultBean.HotInfoBean) intent.getSerializableExtra("hotInfo");

            Glide.with(this).load(Constants.BASE_URl_IMAGE +hotInfo.getFigure()).into(particularsImg);
            particularsText.setText(hotInfo.getName());
            particularsPrice.setText("￥"+hotInfo.getCover_price());
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        particularsImg = (ImageView) findViewById(R.id.particulars_img);
        particularsText = (TextView) findViewById(R.id.particulars_text);
        particularsPrice = (TextView) findViewById(R.id.particulars_price);
        toShopCar = (LinearLayout) findViewById(R.id.toShopCar);

    }
}
