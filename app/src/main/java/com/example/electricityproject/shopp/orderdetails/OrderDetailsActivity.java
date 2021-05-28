package com.example.electricityproject.shopp.orderdetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.common.bean.ConfirmServerPayResultBean;
import com.example.common.bean.FindForPayBean;
import com.example.common.bean.SelectOrderBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.manager.ShopCacheManger;
import com.example.pay.alipay.sdk.pay.demo.AuthResult;
import com.example.pay.alipay.sdk.pay.demo.PayResult;
import com.example.pay.alipay.sdk.pay.demo.util.OrderInfoUtil2_0;
import com.example.view.ToolBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsActivityPresenter> implements OrderDetailsActivityIView,ToolBar.IToolbarListener{

    public static final String APPID = "2088232582727574";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC0P+VZj+vq6UqPxxymnztSALgfEaUz7aTc7EmKR743Oo8ag0MmueXc/zpKt7/HVDtUOB1BYiJjOOcZ0NKIkvVmaYtuTsyq+GMThjssW+GPTJdfViFE0jebRf9d0mJIZ7azMeQfrjK0sSG2thExyxcBPjaM8Q1A+J4q/dz7azvZrUF6ieVXgkLvPa1MoM5vuI/Q5cQ8cIDOV0ImgKlUCorhFsliH8iI6IsbYCZgsSP4mMVAZJ7IUPjX2j+f+ZC9H1H+fANZ92rCLZWTXVrOYtUllzyi7JzvY6SNciDJLGqSp0kzuB7nkFlx8C6cMb/xDakxuQsLXzx6GZqDi0jw06T3AgMBAAECggEAPSxaSDUiGg2m8nje7mWoGkKIOnMgoCkNBLVkl/uVQOUs4BCgX6pDqucr/2OxQJMDt6d7tk754LbVJ9vJN/S4OFOlsIAp0fSOm3e46WsdeCbEUOTIUbUOjjIJYuwd0vjv9oz+IQpbxxs+l+XenAmS+ycEGH1OxDgcZfbWYrWYEF28jYXhCURDDfeNjOoi46G72qQve66X8b3S6Qbh5r/NkyLEYxDSNguRJRtvt1HP8Yrf/lH2letqiU4CNB3KeLYi8EqK5GX0dhmTErZ6KlxAmLJuRgSe5msH2AFXB8VvPPqMfGkbJygYnTcE9RgksceA4z0VaaJZNWByePjTnGrwUQKBgQDjANAXjl7OBCbqN+nQlKjh0z1X4VJcvdJwp12CrdXKSt0odY/NKJp49MY3nrz1IGVrwqRQeMP9VN6hp3ezGGx8eQhHgNgmEb79iHX5vQfrlentZm7h0h8vNSdi9io7xKQOcU+fK0AFMUePNt9zDXgPhrTdJ2k5wf7yNybV45Y0CwKBgQDLRjQCDMFAdEc5QfmLFUU6nNxDA7BRr3K9kzCPh8cVZyVyLGGysZD/eWVS1ng7PIiBDEncLmz1Awi+bUMnZGADji61KWGljf6RRYRFIq8bUYkWsfjxnNG/xy+0ZX/VsQKu56KsnuFmlYNIcyS/RhsJZW29P2uVp+cA3HTg7ruaRQKBgQC4hFWhgClRvpA/PDnDQMNR5JntUArZtbZTucdJzLGF4HqKQ50i/0ko3pYsacxgV/f4sttLSg7NYeohbnXezxCH0P3I2VNZBn2/qedzm5LPjSj7vpKM3nZYshUU3NGRiZJs5u/4inhcgCURpWHWLHGV9UIYrctblnP8QJZBr9/M5wKBgChomEYgCvqpIs97EgRYAh8Yt6CKxy411nVhITInJ6hVjNgZgoJnUg+3LjE9eUEtU2Vz7+rZP8elBsP0LHlUNtH+HcLdqw+iHoz6aMTllDG2D653SqmdJwUcr5sI4lS0j3f9jBUMsHoYFrT8Tr3HTfCcPSh1xwlFC2LccE+WpayRAoGANkF+rM6bnRgvx9bKvVThXB8SHZPujTJRxs3by6ucfVCXpHaTvzOeugyqtYkUCVlnmsNjOS7jRPVzFQD9ZsnR8vQc9Sfwni/MOtWuEGQj4csb4ljCZb4WZR4rdjrRrcAt+zS56ye5UZj45SMHhENxj4HPy0HCUrEwVtJjUknp7Sg=";
    public static final String RSA_PRIVATE = "123456";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String outTradeNo;
    private String orderInfo;
    private String moneyValue;


    private com.example.view.ToolBar toolbar;
    private android.widget.TextView username;
    private android.widget.TextView userPhone;
    private android.widget.TextView userAddress;
    private androidx.recyclerview.widget.RecyclerView orderRv;
    private android.widget.TextView allMoney;
    private TextView productPrice;
    private OrderDetailsAdapter orderDetailsAdapter;
    private List<SelectOrderBean> list = new ArrayList<>();
    private android.widget.Button goBuy;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
         orderInfo = intent.getStringExtra("orderInfo");
         outTradeNo = intent.getStringExtra("outTradeNo");

        username.setText("用户名:"+name);
        userPhone.setText("手机号:"+phone);
        userAddress.setText("地址:"+address);

        list = ShopCacheManger.getInstance().getList();

        float sumPrice=0;
        for (SelectOrderBean selectOrderBean : list) {
            float productPrice=Float.parseFloat(selectOrderBean.getMoney()+"");
            int productNum=Integer.parseInt(selectOrderBean.getNum());
            sumPrice=sumPrice+productPrice*productNum;
        }

        if (sumPrice!=0){
            allMoney.setText("￥"+sumPrice);
            productPrice.setText("￥"+sumPrice);
        }

        if (list!=null){
            orderDetailsAdapter = new OrderDetailsAdapter();
            orderDetailsAdapter.updateData(list);
            orderRv.setAdapter(orderDetailsAdapter);
        }

    }

    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            Toast.makeText(OrderDetailsActivity.this, "1352132123123", Toast.LENGTH_SHORT).show();

            return;
        }
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailsActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    String payMsg="";
                    if (TextUtils.equals(resultStatus, "9000")) {
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,true);
                        payMsg="支付成功";
                        Toast.makeText(OrderDetailsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        resultBean.setOrderInfo(orderInfo);
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        List<FindForPayBean.ResultBean> paySussList = ShopCacheManger.getInstance().getPaySussList();
                        paySussList.add(resultBean);

                    } else {
                        payMsg="支付失败";
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,false);
                        Toast.makeText(OrderDetailsActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        resultBean.setOrderInfo(orderInfo);
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        List<FindForPayBean.ResultBean> payFailList = ShopCacheManger.getInstance().getPayFailList();
                        payFailList.add(resultBean);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {

                    } else {

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void initPresenter() {
        httpPresenter = new OrderDetailsActivityPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        username = (TextView) findViewById(R.id.username);
        userPhone = (TextView) findViewById(R.id.user_phone);
        userAddress = (TextView) findViewById(R.id.user_address);
        orderRv = (RecyclerView) findViewById(R.id.order_rv);
        allMoney = (TextView) findViewById(R.id.all_money);
        productPrice = (TextView) findViewById(R.id.product_price);
        orderRv.setLayoutManager(new LinearLayoutManager(this));
        goBuy = (Button) findViewById(R.id.go_buy);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        moneyValue = "999";
        OrderInfoUtil2_0.setmoney(moneyValue);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;

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
    public void onConfirmServerPayResultOk(ConfirmServerPayResultBean bean) {
        if (bean.getCode().equals("200")){
            finish();
        }
    }
}