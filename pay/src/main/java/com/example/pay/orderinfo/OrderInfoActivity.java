package com.example.pay.orderinfo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderBean;
import com.example.net.bean.PayBean;
import com.example.pay.R;
import com.example.pay.orderinfo.adapter.OrderAdapter;

/**
 * 订单信息
 * 赵岩博
 */
public class OrderInfoActivity extends BaseActivity<OrderPresenter> implements IOrderView {


    private TextView username;
    private TextView userPhone;
    private TextView userAddress;
    private RecyclerView orderRv;
    private TextView productPrice;
    private TextView allMoney;
    private Button orderPay;

    private OrderAdapter orderAdapter;
    private ToolBar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_info;
    }

    @Override
    public void initView() {

        username = (TextView) findViewById(R.id.username);
        userPhone = (TextView) findViewById(R.id.user_phone);
        userAddress = (TextView) findViewById(R.id.user_address);
        orderRv = (RecyclerView) findViewById(R.id.orderRv);
        productPrice = (TextView) findViewById(R.id.product_price);
        allMoney = (TextView) findViewById(R.id.allMoney);
        orderPay = (Button) findViewById(R.id.orderPay);

        orderAdapter = new OrderAdapter();
    }

    @Override
    public void initPresenter() {
        mPresenter = new OrderPresenter(this);


    }
    private PayBean payBean;
    @Override
    public void initData() {
        //用户信息
        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        username.setText(loginBean.getResult().getName());
        userPhone.setText(loginBean.getResult().getPhone()+"");
        userAddress.setText(loginBean.getResult().getAddress()+"");

        //适配器
        orderRv.setLayoutManager(new LinearLayoutManager(this));
        //数据源
        Bundle bundle = CommonArouter.getInstance().getBundle();
        payBean = (PayBean) bundle.getSerializable("data");

        orderRv.setAdapter(orderAdapter);
        orderAdapter.updata(payBean.getBody());
        allMoney.setText(payBean.getTotalPrice());

        orderPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getOrder(payBean);
            }
        });

    }

    @Override
    public void onClickCenter() {
        super.onClickCenter();

    }

    @Override
    public void onClickLeft() {
        super.onClickLeft();
        Bundle bundle = new Bundle();
        bundle.putInt("page",3);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();

    }

    @Override
    public void onClickRight() {
        super.onClickRight();
    }


    @Override
    public void onOrder(OrderBean orderBean) {
        Bundle bundle = new Bundle();
        bundle.putString("totalPrice",payBean.getTotalPrice());
        bundle.putString("orderInfo",orderBean.getResult().getOrderInfo());
        bundle.putString("outTradeNo",orderBean.getResult().getOutTradeNo());
        CommonArouter.getInstance().build(Constants.PATH_PAYMENT).with(bundle).navigation();
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
}