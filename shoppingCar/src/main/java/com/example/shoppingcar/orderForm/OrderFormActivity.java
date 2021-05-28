package com.example.shoppingcar.orderForm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commom.LogUtils;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.net.model.LoginBean;
import com.example.net.model.OrderInfoParamBean;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.R;
import com.example.shoppingcar.orderForm.adapter.OrderFormAdapter;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

import retrofit2.http.HEAD;

@Route(path = "/orderForm/OrderFormActivity")
public class OrderFormActivity extends BaseActivity<OrderFormPresenter> implements IOrderFormView {

    private TextView orderName;
    private TextView orderPhone;
    private TextView orderAddress;
    private RecyclerView orderRv;
    private TextView productPrice;
    private TextView orderMoney;
    private Button orderBut;
    private float aFloat;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_orde_form;
    }

    @Override
    protected void initData() {
        float money = getIntent().getFloatExtra("money", 0.00f);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        aFloat = Float.valueOf(decimalFormat.format(money));

        orderMoney.setText("￥"+aFloat + "");
        productPrice.setText("￥"+aFloat + "");
        LoginBean.ResultBean result = ShopeUserManager.getInstance().getLoginBean().getResult();
        orderAddress.setText(getResources().getString(R.string.userName)+(String) result.getAddress());
        orderName.setText(getResources().getString(R.string.phone)+(String) result.getName());
        orderPhone.setText(getResources().getString(R.string.address)+(String) result.getPhone());

        ArrayList<ShoppingTrolleyBean.ResultBean> deleteBean = ShoppingCarManager.getInstance().addDeleteBean();

        OrderFormAdapter orderFormAdapter = new OrderFormAdapter(deleteBean);
        orderRv.setAdapter(orderFormAdapter);

        orderBut.setOnClickListener(view -> {
            ArrayList<OrderInfoParamBean.BodyBean> bodyBeans = new ArrayList<>();
            for (ShoppingTrolleyBean.ResultBean resultBean : deleteBean) {
                bodyBeans.add(new OrderInfoParamBean.BodyBean(resultBean.getProductName(), resultBean.getProductId()));
            }
            OrderInfoParamBean orderInfoParamBean = new OrderInfoParamBean("buy", money + "", bodyBeans);
            httpPresenter.getOrderInfo(orderInfoParamBean);
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new OrderFormPresenter(this);
    }

    @Override
    protected void initView() {
        orderName = (TextView) findViewById(R.id.order_name);
        orderPhone = (TextView) findViewById(R.id.order_phone);
        orderAddress = (TextView) findViewById(R.id.order_address);
        orderRv = (RecyclerView) findViewById(R.id.order_rv);
        productPrice = (TextView) findViewById(R.id.product_price);
        orderMoney = (TextView) findViewById(R.id.order_money);
        orderBut = (Button) findViewById(R.id.order_but);
        orderRv.setLayoutManager(new LinearLayoutManager(OrderFormActivity.this));
    }

    @Override
    public void onOrderInfo(OrderinfoBean orderinfoBean) {
        if (orderinfoBean.getCode().equals("200")) {
            Toast.makeText(OrderFormActivity.this, getResources().getString(R.string.placeAnOrderSuccessfully), Toast.LENGTH_SHORT).show();
            ShoppingCarManager.getInstance().deletePartResult();
            ARouter.getInstance().build(ShopConstants.PAY_PATH).withSerializable("orderinfoBean",orderinfoBean).withFloat("money",aFloat).navigation();
        }
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSucessView();
    }

    @Override
    public void Error(String error) {
        loadingPage.showErrorView(error);
    }
}