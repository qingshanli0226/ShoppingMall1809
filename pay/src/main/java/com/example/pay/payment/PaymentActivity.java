package com.example.pay.payment;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.net.bean.PaymentBean;
import com.example.pay.R;


public class PaymentActivity extends BaseActivity<PaymentPresenter> implements IPaymentView {

    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView paymentRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setToolbarOnClickLisenter(this);
        paymentRv = (RecyclerView) findViewById(R.id.paymentRv);
    }

    @Override
    public void initPresenter() {
        mPresenter = new PaymentPresenter(this);
        mPresenter.getpay();
    }

    @Override
    public void initData() {

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onPayment(PaymentBean paymentBean) {
        LogUtils.json("pay"+paymentBean.getCode());
    }
}
