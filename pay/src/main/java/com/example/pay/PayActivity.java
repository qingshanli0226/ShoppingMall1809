package com.example.pay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.PaySendCacheManager;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.OrderinfoBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class PayActivity extends AppCompatActivity {
    private Button btn;
    private OrderinfoBean bean;
    private final int a=1;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case a:
                    Map<String,String> result= (Map<String, String>) msg.obj;
                    String resultStatus= result.get("resultStatus");
                    Toast.makeText(PayActivity.this, resultStatus, Toast.LENGTH_SHORT).show();
                    if (resultStatus.equals("9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        CaCheMannager.getInstance().payNotify(1);//通知购物车刷新数据
                        finish();
                    } else {
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else if (resultStatus.equals("6001")){
                            Toast.makeText(PayActivity.this, "用户取消支付", Toast.LENGTH_SHORT).show();
                            FindForPayBean.ResultBean bean1 = new FindForPayBean.ResultBean();
                            bean1.setTradeNo(bean.getResult().getOutTradeNo());
                            PaySendCacheManager.getInstance().setFindForPayBean(bean1);
                        }
                        else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            FindForPayBean.ResultBean bean1 = new FindForPayBean.ResultBean();
                            bean1.setTradeNo(bean.getResult().getOutTradeNo());
                            PaySendCacheManager.getInstance().setFindForPayBean(bean1);
                            CaCheMannager.getInstance().payNotify(2);//通知购物车刷新数据
                        }
                    }
                    break;
            }
        }


    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_main);
        initView();
        Intent intent = getIntent();
         bean = (OrderinfoBean) intent.getSerializableExtra("bean");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bean!=null){
                    String orderInfo = bean.getResult().getOrderInfo();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            PayTask payTask = new PayTask(PayActivity.this);
                            Map<String, String> result = payTask.payV2(orderInfo, false);
                            Message message = new Message();
                            message.what=a;
                            message.obj=result;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
            }
        });

    }




    private void initView() {
        btn = findViewById(R.id.btn);

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }
}