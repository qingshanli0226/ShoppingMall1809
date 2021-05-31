package com.example.pay.awaitpayment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.AwaitPaymentBean;
import com.example.pay.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *代付款
 * 赵子裕
 */
public class AwaitPaymentActivity extends BaseActivity<AwaitPaymentPresenter> implements IAwaitPaymentView {

    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView paymentRv;
    AwaitPaymentAdapter paymentAdapter;
    List<AwaitPaymentBean.ResultBean> awaitPayment;


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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        paymentRv.setLayoutManager(linearLayoutManager);

        paymentAdapter.setRvItemOnClickListener(new BaseRvAdapter.IRvItemOnClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AwaitPaymentActivity.this);
                builder.setTitle("确认支付该订单");
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String totalPrice = awaitPayment.get(position).getTotalPrice();
                        String orderInfo = (String) awaitPayment.get(position).getOrderInfo();

                        Bundle bundle = new Bundle();
                        bundle.putString("totalPrice",totalPrice);
                        bundle.putString("orderInfo",orderInfo);
                        CommonArouter.getInstance().build(Constants.PATH_PAYMENT).with(bundle).navigation();
                        finish();
                    }
                });

                builder.show();
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
        Bundle bundle = new Bundle();
        bundle.putInt("page",4);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
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
       awaitPayment = CacheAwaitPaymentManager.getInstance().getAwaitPayment();
        paymentAdapter.getData().addAll(awaitPayment);
        paymentAdapter.notifyDataSetChanged();
    }
}
