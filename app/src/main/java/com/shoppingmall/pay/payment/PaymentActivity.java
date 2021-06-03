package com.shoppingmall.pay.payment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.shoppingmall.R;

import com.shoppingmall.detail.messagedao.MessageBean;
import com.shoppingmall.detail.messagedao.MessageManager;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.PayCheckBean;
import com.shoppingmall.net.bean.SelectBean;
import com.shoppingmall.zfb.PayResult;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;

public class PaymentActivity extends BaseActivity<PayMentPresenter> implements IPaymentView {

    private Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == Constants.PAY_SDK_FLAG){
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                String result = payResult.getResult();
                String resultStatus = payResult.getResultStatus();
                String paymessage="";
                MessageBean messageBean = new MessageBean();
                messageBean.setIsNew(true);

                if(TextUtils.equals(resultStatus,"9000")){
                    EventBus.getDefault().post("delCar");
                    Toast.makeText(PaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    paymessage="支付成功";
                    ARouter.getInstance().build(Constants.TO_MAIN_ACTIVITY).withInt("shopMallPosition",3).navigation();

                    PayCheckBean payCheckBean = new PayCheckBean();
                    payCheckBean.setOutTradeNo(outTradeNo);
                    payCheckBean.setResult(orderInfo);
                    payCheckBean.setClientPayResult(true);
                    httpPresenter.checkNumAll(payCheckBean);


                } else{
                    if(TextUtils.equals(resultStatus,"8000")){
                        Toast.makeText(PaymentActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                    } else{
                        EventBus.getDefault().post("delCar");
                        ARouter.getInstance().build(Constants.TO_MAIN_ACTIVITY).withInt("shopMallPosition",3).navigation();
                        paymessage="支付失败";
                        Toast.makeText(PaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        PayCheckBean payCheckBean = new PayCheckBean();
                        payCheckBean.setOutTradeNo(outTradeNo);
                        payCheckBean.setResult(orderInfo);
                        payCheckBean.setClientPayResult(false);
                        httpPresenter.checkNumAll(payCheckBean);
                        


                    }
                }
                messageBean.setTitle("支付消息:");
                messageBean.setMsg(paymessage);
                messageBean.setTime(System.currentTimeMillis());
                MessageManager.getInstance().addMessage(messageBean, new MessageManager.IMessageListener() {
                    @Override
                    public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                        EventBus.getDefault().post("payback");
                    }
                });

            }

        }
    };


    private android.widget.TextView payPrice;
    private android.widget.CheckBox weiCheck;
    private android.widget.CheckBox zfbCheck;
    private android.widget.Button pay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_ment;
    }

    @Override
    public void initView() {

        payPrice = (TextView) findViewById(R.id.payPrice);
        weiCheck = (CheckBox) findViewById(R.id.weiCheck);
        zfbCheck = (CheckBox) findViewById(R.id.zfbCheck);
        pay = (Button) findViewById(R.id.pay);
    }

    private String totalPrice;
    private String orderInfo;
    private String outTradeNo;

    @Override
    public void initPresenter() {
        httpPresenter = new PayMentPresenter(this);
    }

    @Override
    public void initData() {

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        Intent intent = getIntent();
        totalPrice = intent.getStringExtra("totalPrice");
        orderInfo = intent.getStringExtra("orderInfo");
        outTradeNo = intent.getStringExtra("outTradeNo");
        payPrice.setText(""+totalPrice);

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
                obtain.what = 0;
                obtain.obj = stringStringMap;
                handler.sendMessage(obtain);
            }
        };
        Thread payThread = new Thread(runnable);
        payThread.start();
    }

    @Override
    public void onConfigPay(SelectBean selectBean) {

    }
}