package com.shoppingmall.pay.awitpayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.manager.PaymentManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.FindForPayBean;

import java.util.ArrayList;

public class AwaitPaymentActivity extends BaseActivity<AwaitPaymentPresenter> implements IAwaitPaymentView {

    private android.widget.ImageView detailBack;
    private android.widget.ImageView detailMenu;
    private androidx.recyclerview.widget.RecyclerView paymentRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_await_payment;
    }

    @Override
    public void initView() {

        detailBack = (ImageView) findViewById(R.id.detailBack);
        detailMenu = (ImageView) findViewById(R.id.detailMenu);
        paymentRv = (RecyclerView) findViewById(R.id.paymentRv);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new AwaitPaymentPresenter(this);
    }

    @Override
    public void initData() {
        httpPresenter.getFindForPay();

    }

    @Override
    public void onAwaitPayment(FindForPayBean findForPayBean) {
        LogUtils.json(findForPayBean);

        AwaitPaymentAdapter awaitPaymentAdapter = new AwaitPaymentAdapter();
        paymentRv.setLayoutManager(new LinearLayoutManager(this));
        paymentRv.setAdapter(awaitPaymentAdapter);
        awaitPaymentAdapter.updateData(findForPayBean.getResult());

        awaitPaymentAdapter.setRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
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
                        Toast.makeText(AwaitPaymentActivity.this, "付款成功", Toast.LENGTH_SHORT).show();
                        awaitPaymentAdapter.getData().remove(position);
                        awaitPaymentAdapter.notifyDataSetChanged();

                    }
                });

                builder.show();
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }
}