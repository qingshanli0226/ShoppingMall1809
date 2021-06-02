package com.example.pay.payment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheMessageManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.PayCheckBean;
import com.example.net.bean.PayResult;
import com.example.net.bean.SelectBean;
import com.example.pay.R;
import com.fiannce.sql.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PaymentActivity extends BaseActivity<PayMentPresenter> implements IPaymentView {

    private com.example.framework.view.ToolBar toolbar;
    private android.widget.CheckBox weiCheck;
    private android.widget.CheckBox zfbCheck;
    private android.widget.Button pay;
    private TextView payPrice;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == Constants.PAY_SDK_FLAG){
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                String result = payResult.getResult();
                String resultStatus = payResult.getResultStatus();
                if(TextUtils.equals(resultStatus,"9000")){
                    Toast.makeText(PaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putInt("page",4);
                    CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
                    PayCheckBean payCheckBean = new PayCheckBean();
                    payCheckBean.setOutTradeNo(outTradeNo);
                    payCheckBean.setResult(orderInfo);
                    payCheckBean.setClientPayResult(true);
                    mPresenter.checkNumAll(payCheckBean);

                    //信息
                    MessageBean messageBean = new MessageBean();
                    messageBean.setId(null);
                    messageBean.setType(0);
                    messageBean.setMessage("支付成功");
                    messageBean.setMessageTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(System.currentTimeMillis()));
                    messageBean.setIsRead(true);
                    CacheMessageManager.getInstance().addMessage(messageBean);


                } else{
                    if(TextUtils.equals(resultStatus,"8000")){
                        Toast.makeText(PaymentActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(PaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        PayCheckBean payCheckBean = new PayCheckBean();
                        payCheckBean.setOutTradeNo(outTradeNo);
                        payCheckBean.setResult(orderInfo);
                        payCheckBean.setClientPayResult(false);
                        mPresenter.checkNumAll(payCheckBean);
                        //信息
                        MessageBean messageBean = new MessageBean();
                        messageBean.setId(null);
                        messageBean.setType(0);
                        messageBean.setMessage("支付失败");
                        messageBean.setMessageTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(System.currentTimeMillis()));
                        messageBean.setIsRead(true);
                        CacheMessageManager.getInstance().addMessage(messageBean);
                    }
                }
            }

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        weiCheck = (CheckBox) findViewById(R.id.weiCheck);
        zfbCheck = (CheckBox) findViewById(R.id.zfbCheck);
        pay = (Button) findViewById(R.id.pay);
        toolbar.setToolbarOnClickLisenter(this);
        payPrice = (TextView) findViewById(R.id.payPrice);
    }

    @Override
    public void initPresenter() {
        mPresenter = new PayMentPresenter(this);

    }
    private String totalPrice;
    private String orderInfo;
    private String outTradeNo;

    @Override
    public void initData() {
        Bundle bundle = CommonArouter.getInstance().getBundle();
        totalPrice = bundle.getString("totalPrice");
        orderInfo = bundle.getString("orderInfo");
        outTradeNo = bundle.getString("outTradeNo");
        payPrice.setText(totalPrice);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weiCheck.isChecked()){

                } else if(zfbCheck.isChecked()){
                    //支付宝
                    handleResult(orderInfo);

                } else{
                    Toast.makeText(PaymentActivity.this, "没有选中支付方式", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //支付详细
    private void handleResult(final  String payInfo){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(PaymentActivity.this);
                Map<String, String> stringStringMap = payTask.payV2(payInfo, true);
                Message obtain = Message.obtain();
                obtain.what = Constants.PAY_SDK_FLAG;
                obtain.obj = stringStringMap;
                handler.sendMessage(obtain);
            }
        };
        Thread payThread = new Thread(runnable);
        payThread.start();
    }


    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        Bundle bundle = new Bundle();
        bundle.putInt("page",3);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onConfigPay(SelectBean selectBean) {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
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
    public void onConect() {
        super.onConect();

    }

    @Override
    public void onDisConnect() {
        super.onDisConnect();

    }
}
