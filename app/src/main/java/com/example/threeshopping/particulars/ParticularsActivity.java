package com.example.threeshopping.particulars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.UserManager;
import com.example.framework.view.ToolBar;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.threeshopping.R;

public class ParticularsActivity extends BaseActivity<DetailPresenter> implements IDetailView {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView paricularsImg;
    private android.widget.TextView paricularsName;
    private android.widget.TextView paricularsPrice;
    private android.widget.ImageView shopcar;
    private android.widget.Button particularsJoin;
    private LoginBean loginBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        paricularsImg = (ImageView) findViewById(R.id.pariculars_img);
        paricularsName = (TextView) findViewById(R.id.pariculars_name);
        paricularsPrice = (TextView) findViewById(R.id.pariculars_price);
        shopcar = (ImageView) findViewById(R.id.shopcar);
        particularsJoin = (Button) findViewById(R.id.particulars_join);
        toolbar.setToolbarOnClickLisenter(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = new DetailPresenter(this);
    }
    private String title;
    private String pic;
    private String price;
    private String id;

    @Override
    public void initData() {
        Bundle bundle = CommonArouter.getInstance().getBundle();
        title = bundle.getString("title");
        pic = bundle.getString("pic");
        price = bundle.getString("price");
        id = bundle.getString("id");
        Glide.with(this).load(pic).into(paricularsImg);
        paricularsName.setText(""+title);
        paricularsPrice.setText("￥"+price);

        loginBean = UserManager.getInstance().getLoginBean();


        particularsJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginBean != null){
                    ProductBean productBean = new ProductBean();
                    productBean.setProductId(id);
                    productBean.setProductName(title);
                    productBean.setProductNum(1);
                    productBean.setUrl(pic);
                    productBean.setProductPrice(price);
                    mPresenter.addProduct(productBean);
                }
            }
        });
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onAddCart(ProductBean productBean) {
        Toast.makeText(this, "添加购物车成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Log.i("zyb", "showError: "+error);
    }

    @Override
    public void onUserChange(LoginBean loginBean) {
        super.onUserChange(loginBean);
        this.loginBean = loginBean;
    }
}
