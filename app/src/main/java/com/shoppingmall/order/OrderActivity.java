package com.shoppingmall.order;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.ShopCarBean;
import com.shoppingmall.order.adapter.OrderAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class OrderActivity extends BaseActivity {


    private android.widget.ImageView detailBack;
    private android.widget.ImageView detailMenu;
    private android.widget.TextView username;
    private android.widget.TextView userPhone;
    private android.widget.TextView userAddress;
    private androidx.recyclerview.widget.RecyclerView orderRv;
    private android.widget.TextView productPrice;
    private android.widget.TextView orderPrice;
    private android.widget.TextView allMoney;
    private android.widget.Button orderPay;
    private OrderAdapter orderAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {

        detailBack = (ImageView) findViewById(R.id.detailBack);
        detailMenu = (ImageView) findViewById(R.id.detailMenu);
        username = (TextView) findViewById(R.id.username);
        userPhone = (TextView) findViewById(R.id.user_phone);
        userAddress = (TextView) findViewById(R.id.user_address);
        orderRv = (RecyclerView) findViewById(R.id.orderRv);
        productPrice = (TextView) findViewById(R.id.product_price);
        orderPrice = (TextView) findViewById(R.id.order_price);
        allMoney = (TextView) findViewById(R.id.allMoney);
        orderPay = (Button) findViewById(R.id.orderPay);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        LoginBean loginBean = ShopMallUserManager.getInstance().getLoginBean();
        username.setText(""+loginBean.getResult().getName());
        userAddress.setText(""+loginBean.getResult().getAddress());
        userPhone.setText(""+loginBean.getResult().getPhone());

        Intent intent = getIntent();
        String Price = intent.getStringExtra("orderPrice");
        orderPrice.setText("￥"+Price);
        List<ShopCarBean.ResultBean> orderList = (List<ShopCarBean.ResultBean>) intent.getSerializableExtra("orderList");
        orderAdapter = new OrderAdapter();
        orderRv.setLayoutManager(new LinearLayoutManager(this));
        orderRv.setAdapter(orderAdapter);
        orderAdapter.updateData(orderList);

        orderPay.setOnClickListener(v->{
            EventBus.getDefault().post("delCar");

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orderAdapter!=null){
            orderAdapter=null;
        }
    }
}