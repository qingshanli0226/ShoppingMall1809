package com.example.myapplication.payorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.framework.BaseActivity;
import com.example.framework.manager.CaCheMannager;
import com.example.myapplication.R;
import com.example.net.bean.OrderinfoBean;
import com.example.net.bean.ShoppingCartBean;
import com.example.pay.PayActivity;

import java.util.List;

public class OrderActivity extends BaseActivity<OrderPresenter>implements IOrderView {


    private com.example.framework.view.MyToorbar toolbar;
    private android.widget.TextView nameOrder;
    private android.widget.TextView phoneOrder;
    private android.widget.TextView adressOrder;
    private androidx.recyclerview.widget.RecyclerView rvOrder;
    private android.widget.TextView priceOrder;
    private android.widget.RelativeLayout llGoodsRoot;
    private android.widget.TextView pricePay;
    private android.widget.Button buyOrder;
    private Intent intent;

    @Override
    protected int bandLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void onOrder(OrderinfoBean orderinfoBean) {

        buyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OrderActivity.this, PayActivity.class);
                intent.putExtra("bean",orderinfoBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initView() {

        toolbar = findViewById(R.id.toolbar);
        nameOrder = findViewById(R.id.name_order);
        phoneOrder = findViewById(R.id.phone_order);
        adressOrder = findViewById(R.id.adress_order);
        rvOrder = findViewById(R.id.rv_order);
        priceOrder = findViewById(R.id.price_order);
        llGoodsRoot = findViewById(R.id.ll_goods_root);
        pricePay = findViewById(R.id.price_pay);
        buyOrder = findViewById(R.id.buy_order);
    }

    @Override
    public void initPresenter() {
          mPresenter=new OrderPresenter(this);
        List<ShoppingCartBean.ResultBean> shoppingCartBeanList = CaCheMannager.getInstance().getShoppingCartBeanList();
        mPresenter.getOrderInfo(shoppingCartBeanList);
    }

    @Override
    public void initData() {

    }
}