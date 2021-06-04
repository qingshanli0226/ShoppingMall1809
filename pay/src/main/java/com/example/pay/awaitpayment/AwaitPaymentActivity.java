package com.example.pay.awaitpayment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.AwaitPaymentBean;
import com.example.pay.R;

import java.util.List;

/**
 * 代付款
 * 赵子裕
 */
public class AwaitPaymentActivity extends BaseActivity implements CacheAwaitPaymentManager.IAwaitPay {

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
//        mPresenter = new AwaitPaymentPresenter(this);
//        if (CacheConnectManager.getInstance().isConnect()) {
//            mPresenter.getpay();
//        } else {
//            Toast.makeText(this, "网络走丢了", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void initData() {
        paymentAdapter = new AwaitPaymentAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        paymentRv.setAdapter(paymentAdapter);
        paymentRv.setLayoutManager(linearLayoutManager);

        awaitPayment = CacheAwaitPaymentManager.getInstance().getAwaitpayment();
        paymentAdapter.getData().addAll(awaitPayment);
        paymentAdapter.notifyDataSetChanged();


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
                        String outTradeNo = awaitPayment.get(position).getTradeNo();

                        Bundle bundle = new Bundle();
                        bundle.putString("totalPrice", totalPrice);
                        bundle.putString("orderInfo", orderInfo);
                        bundle.putString("outTradeNo", outTradeNo);
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
        bundle.putInt("page", 4);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
    }

    @Override
    public void onClickRight() {

    }

//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void showError(String error) {
//
//    }
//
//    @Override
//    public void onAwaitPayment(AwaitPaymentBean paymentBean) {
//
//    }

    @Override
    public void onConect() {
        super.onConect();
//        mPresenter.getpay();
        Toast.makeText(this, "正在缓冲...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        super.onDisConnect();
    }

    @Override
    public void onAwaitPay(List<AwaitPaymentBean.ResultBean> awaitPayment) {
        this.awaitPayment = awaitPayment;
        paymentAdapter.updata(awaitPayment);
    }

    @Override
    public void onAddPay(int position) {
        paymentAdapter.notifyItemChanged(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheAwaitPaymentManager.getInstance().unRegisterPay(this);
    }
}
