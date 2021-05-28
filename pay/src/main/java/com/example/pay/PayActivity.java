package com.example.pay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.PayTask;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.model.OrderinfoBean;

import java.io.Serializable;
import java.util.Map;

@Route(path = "/pay/PayActivity")
public class PayActivity extends BaseActivity<PayPresenter> implements IPayView {

    private OrderinfoBean orderinfoBean;
    private float money;
    private ToolBar toolbar;
    private TextView payBut;
    private static final int SDK_PAY_FLAG = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SDK_PAY_FLAG:
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    String resultStatus = result.get("resultStatus");
                    if (resultStatus.equals("9000")){
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    }else {
                        if (resultStatus.equals("8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else if (resultStatus.equals("6001")) { //用户中途取消
                            Toast.makeText(PayActivity.this, "取消支付", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }

        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        money = intent.getFloatExtra("money", 0.00f);
        orderinfoBean = (OrderinfoBean) intent.getSerializableExtra("orderinfoBean");
        payBut.setText(getResources().getString(R.string.zfb) + money + getResources().getString(R.string.yuan));

        payBut.setOnClickListener(view -> {
            if (orderinfoBean != null) {
                String orderInfo = orderinfoBean.getResult().getOrderInfo();
                new Thread(() -> {
                    PayTask payTask = new PayTask(this);
                    Map<String, String> result = payTask.payV2(orderInfo, false);
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj =result;
                    handler.sendMessage(msg);

                }).start();

            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new PayPresenter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        payBut = (TextView) findViewById(R.id.pay_but);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightTitle() {
        super.onRightTitle();
        ARouter.getInstance().build(ShopConstants.SHOP_PAY).navigation();
    }

}