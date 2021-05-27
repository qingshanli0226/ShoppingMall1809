package com.example.electricityproject.shopp.orderdetails;

import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.SelectOrderBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.manager.ShopCacheManger;
import com.example.view.ToolBar;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends BaseActivity implements ToolBar.IToolbarListener{

    private com.example.view.ToolBar toolbar;
    private android.widget.TextView username;
    private android.widget.TextView userPhone;
    private android.widget.TextView userAddress;
    private androidx.recyclerview.widget.RecyclerView orderRv;
    private android.widget.TextView allMoney;
    private TextView productPrice;
    private OrderDetailsAdapter orderDetailsAdapter;
    private List<SelectOrderBean> list = new ArrayList<>();
    private List<SelectOrderBean> selectOrderBeanList = new ArrayList<>();

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");

        username.setText("用户名:"+name);
        userPhone.setText("手机号:"+phone);
        userAddress.setText("地址:"+address);

        list = ShopCacheManger.getInstance().getList();
        selectOrderBeanList.addAll(list);

        float sumPrice=0;
        for (SelectOrderBean selectOrderBean : list) {
            float productPrice=Float.parseFloat(selectOrderBean.getMoney()+"");
            int productNum=Integer.parseInt(selectOrderBean.getNum());
            sumPrice=sumPrice+productPrice*productNum;
        }

        if (sumPrice!=0){
            allMoney.setText("￥"+sumPrice);
            productPrice.setText("￥"+sumPrice);
        }

        if (list!=null){
            orderDetailsAdapter = new OrderDetailsAdapter();
            orderDetailsAdapter.updateData(list);
            orderRv.setAdapter(orderDetailsAdapter);
        }

        //问题代码
        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
            @Override
            public void onLeftClick() {
                list.clear();
            }

            @Override
            public void onRightImgClick() {

            }

            @Override
            public void onRightTvClick() {

            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        username = (TextView) findViewById(R.id.username);
        userPhone = (TextView) findViewById(R.id.user_phone);
        userAddress = (TextView) findViewById(R.id.user_address);
        orderRv = (RecyclerView) findViewById(R.id.order_rv);
        allMoney = (TextView) findViewById(R.id.all_money);
        productPrice = (TextView) findViewById(R.id.product_price);
        orderRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
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