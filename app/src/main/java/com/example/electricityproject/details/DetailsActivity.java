package com.example.electricityproject.details;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.call.BusinessARouter;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.view.ToolBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView{
    private ToolBar toolbar;
    private ImageView detailsImg;
    private TextView detailsName;
    private TextView detailsPrice;
    private LinearLayout addShop;
    private Button btnAdd;
    private String name;
    private String img;
    private String price;
    private Map<String,String> map = new HashMap<>();
    private String productId;
    private String productPrice;
    private String productNum;
    private String url;
    private Intent intent;
    private List<ShortcartProductBean.ResultBean> resultBeans = new ArrayList<>();
    private ImageView buyCar;

    @Override
    protected void initData() {

        intent = getIntent();
        name = intent.getStringExtra("name");
        img = intent.getStringExtra("img");
        price = intent.getStringExtra("price");
        productNum = "1";
        url = "http://www.baidu.com";


        productId = intent.getStringExtra("productId");
        productPrice = intent.getStringExtra("productPrice");
        productPrice = intent.getStringExtra("productPrice");
        map.put("productId",productId);
        map.put("productNum",productNum);
        map.put("productNum",productNum);
        map.put("productName",name);
        map.put("url",url);
        map.put("productPrice",productPrice);


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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpPresenter.postAddOneProduct(map);
            }
        });

        buyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("notify","go_buyCar");
                BusinessARouter.getInstance().getAppManager().OpenMainActivity(DetailsActivity.this,bundle);
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new DetailsPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        detailsImg = (ImageView) findViewById(R.id.details_img);
        detailsName = (TextView) findViewById(R.id.details_name);
        detailsPrice = (TextView) findViewById(R.id.details_price);
        addShop = (LinearLayout) findViewById(R.id.add_shop);
        btnAdd = (Button) findViewById(R.id.btn_add);
        buyCar = (ImageView) findViewById(R.id.buy_car);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void getAddOneProduct(AddOneProductBean addOneProductBean) {
        Toast.makeText(this, ""+addOneProductBean.getCode(), Toast.LENGTH_SHORT).show();
    }

}