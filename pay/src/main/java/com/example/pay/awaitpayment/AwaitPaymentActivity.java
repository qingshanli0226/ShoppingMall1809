package com.example.pay.awaitpayment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.LogUtil;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.view.ToolBar;
import com.example.net.bean.AwaitPaymentBean;
import com.example.pay.R;

import java.util.ArrayList;
import java.util.List;

/**
 *代付款
 * 赵子裕
 */
public class AwaitPaymentActivity extends BaseActivity<AwaitPaymentPresenter> implements IAwaitPaymentView {

    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView paymentRv;
    AwaitPaymentAdapter paymentAdapter;
    List<AwaitPaymentBean.ResultBean> list = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_awaitpayment;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setToolbarOnClickLisenter(this);
        paymentRv = (RecyclerView) findViewById(R.id.paymentRv);
    }

    @Override
    public void initPresenter() {
        mPresenter = new AwaitPaymentPresenter(this);
        mPresenter.getpay();
    }

    @Override
    public void initData() {
        paymentAdapter = new AwaitPaymentAdapter();
        paymentRv.setAdapter(paymentAdapter);
        paymentRv.setLayoutManager(new LinearLayoutManager(this));

        paymentAdapter.setRvItemOnClickListener(new BaseRvAdapter.IRvItemOnClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }

            @Override
            public boolean onLongItemClick(int position, View view) {
                return false;
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onAwaitPayment(AwaitPaymentBean paymentBean) {
        LogUtil.i("payyyyyyyy"+paymentBean.getResult().toString());
        List<AwaitPaymentBean.ResultBean> result = paymentBean.getResult();
        list.addAll(result);
        paymentAdapter.getData().addAll(list);
        paymentAdapter.notifyDataSetChanged();
    }
}
