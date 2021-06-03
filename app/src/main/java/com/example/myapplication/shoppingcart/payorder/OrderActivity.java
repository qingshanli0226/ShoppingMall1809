package com.example.myapplication.shoppingcart.payorder;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.view.View;
import com.example.framework.BaseActivity;
import android.widget.Toast;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CaCheLoginUserMannager;
import com.example.framework.manager.CaCheMannager;
import com.example.myapplication.R;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderinfoBean;
import com.example.net.bean.ShoppingCartBean;
import com.example.pay.PayActivity;
import java.util.List;

public class OrderActivity extends BaseActivity<OrderPresenter> implements IOrderView,CaCheLoginUserMannager.UserPhoneAndAddress {


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
    private OrderRecAdapter adapter;

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
                startActivity(OrderActivity.this.intent);


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
        adapter = new OrderRecAdapter();
        CaCheLoginUserMannager.getInstance().registerAddLoginUser(this);//注册
    }

    @Override
    public void initPresenter() {
          mPresenter=new OrderPresenter(this);
        List<ShoppingCartBean.ResultBean> shoppingCartBeanList = CaCheMannager.getInstance().getShoppingCartBeanList();
        mPresenter.getOrderInfo(shoppingCartBeanList);
    }

    @Override
    public void initData() {
        //获取传过来的价格
        Intent intent = getIntent();
        String price = intent.getStringExtra("shoppingPrice");
        pricePay.setText(price);//价格
        priceOrder.setText(price);//总价
        //获取选中集合
        List<ShoppingCartBean.ResultBean> checkList = CaCheMannager.getInstance().getCheckList();
        adapter.updataData(checkList);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        rvOrder.setAdapter(adapter);

        //用户名
        LoginBean bean = CaCheLoginUserMannager.getInstance().getBean();
        nameOrder.setText(bean.getResult().getName());
        phoneOrder.setText(bean.getResult().getPhone()+"");
        adressOrder.setText(bean.getResult().getAddress()+"");

        //提交订单的时候判断是否绑定手机号与地址
        buyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getResult().getPhone()==null||bean.getResult().getAddress()==null){
                    Toast.makeText(OrderActivity.this, "请绑定手机号与地址", Toast.LENGTH_SHORT).show();
                    CaCheArote.getInstance().getUserInterface().openPhoneAddressActivity(OrderActivity.this,null);
                }else {

                }
            }
        });
    }

    @Override
    public void setPhoneOrAddress(LoginBean loginBean) {
        LoginBean.ResultBean result = loginBean.getResult();
        phoneOrder.setText(result.getPhone()+"");
        adressOrder.setText(result.getAddress()+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        CaCheLoginUserMannager.getInstance().unRegisterAddLoginUser(this);//注册
    }
}