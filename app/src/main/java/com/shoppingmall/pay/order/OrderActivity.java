package com.shoppingmall.pay.order;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.EnvUtils;
import com.shoppingmall.R;
import com.shoppingmall.framework.manager.PaymentManager;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.FindForPayBean;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.OrderBean;
import com.shoppingmall.net.bean.PayBean;
import com.shoppingmall.net.bean.ShopCarBean;
import com.shoppingmall.pay.order.adapter.OrderAdapter;
import com.shoppingmall.pay.payment.PaymentActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderActivity extends BaseActivity<OrderPresenter> implements IOrderView {
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
    private String tradeNo="";
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
        httpPresenter = new OrderPresenter(this);

    }
    private PayBean payBean;
    @Override
    public void initData() {
        LoginBean loginBean = ShopMallUserManager.getInstance().getLoginBean();
        //用户信息
        username.setText(loginBean.getResult().getName());
        userPhone.setText(loginBean.getResult().getPhone()+"");
        userAddress.setText(loginBean.getResult().getAddress()+"");

        Intent intent = getIntent();
        payBean = (PayBean) intent.getSerializableExtra("body");

        orderPrice.setText(""+payBean.getTotalPrice());
        orderAdapter = new OrderAdapter();
        orderAdapter.notifyDataSetChanged();
        orderRv.setLayoutManager(new LinearLayoutManager(this));
        orderRv.setAdapter(orderAdapter);
        orderAdapter.updateData(payBean.getBody());

        orderPay.setOnClickListener(v->{
            httpPresenter.getOrder(payBean);
        });
    }

    private String getTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD_hh:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }

    @Override
    public void orderInfo(OrderBean orderBean) {
        Intent intent = new Intent(OrderActivity.this,PaymentActivity.class);
        intent.putExtra("totalPrice",payBean.getTotalPrice());
        intent.putExtra("orderInfo",orderBean.getResult().getOrderInfo());
        intent.putExtra("outTradeNo",orderBean.getResult().getOutTradeNo());
        startActivity(intent);
    }
}
