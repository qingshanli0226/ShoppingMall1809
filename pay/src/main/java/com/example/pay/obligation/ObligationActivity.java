package com.example.pay.obligation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.view.ToolBar;
import com.example.net.bean.find.FindForPayBean;
import com.example.pay.BuildConfig;
import com.example.pay.R;
import com.example.pay.demo.AuthResult;
import com.example.pay.demo.PayResult;
import com.example.pay.demo.util.OrderInfoUtil2_0;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObligationActivity extends BaseActivity<ObligationPresenter> implements Iobligation {
    private ToolBar toolbar;
    private  List<FindForPayBean.ResultBean> list = new ArrayList<>();
    ObligationPresenter obligationPresenter;
    private RecyclerView rv;
    private  ObligationAdapter obligationAdapter;
    public static final String APPID = "2021000117602508";
    public static final String PID = "2088912429840735";
    public static final String TARGET_ID = "123456";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCNMY12iEK2E1Ym9TJVlCh9uNyhhptjP9xp8MrERBjKWgWJQmhTYS89nI6C9zeIXLK7Fv0zhY0SUwFMTMTEEfI9NkakQF5/xr8zsEbOztKX4hUOdpcT8Q2kt4ZUPGh0VQGeJnwSy2CXbt5rw7GfPce+PjwQ5AT5w3RLyDNVWUELYGsvy8rIx0RubKqhj7h8bWY7Rfmn2s6t3gPFxmeLHhXDpGcn5ew920WgvqcUe01BGZVq3rTvCVSM4Gu9PCPA/ssezXpYnxbEcWJ8Yz50pUydeYgiITJkJB1W/s8Z1nyQ8pQC7IhhdH+ZbjCo2dSgOcCreRoD5JuHKw09e3sLlUFvAgMBAAECggEAZMHn4Z57fNNvt31i3g9MletGBz63xKvd7vwWttihmEWFjd4F9Pp1a9I9INiqmHUOVtSg/BNiCKGTW4+tGv5zj5sb7jrBNQ3vhENXRF3zis+blyapyWFMCy+sfJBfOiUvclykTgU0eNx1fRG5v5mm4OwokSUnJ9WDOFE10MJQ2Hx1YPrQyMMYt/8Va5T85133+YAHSYF5uNpOnXSQIhwDM16gheG5qUAQVQvNTj/rkDOU+4szGo6QBiMkB6TyDzGzyf5Fcu9+4XbhuJmBa7Aeljug/GiesltZ40s6lMWLH1PpJ0tQpjGh2+nGMFVUEq7aUzcYiQ+wz52od2fYYMvCgQKBgQDRfWxvCug9SVioL4Z9OzquGUvglFHZOgWWeoL/bXDXv+RiRfLfenWmoYOra5wmxXR8HSx0ATSOZr3JBREqSGkTIefKyb06ZqJdLXk0elmzK9LO5xj7L2jEwQiTpyZKk+ILb/Tnyhk9+A/LK8aHCK55Ibaalu7qEMBDZNPN2IdKTQKBgQCsim8zb0ohtXT55EXKjrxQIgVN1erbYmUKopB9zK9nF/ipr5PwahkyoYokxJACv8mt7wUc5tDGYs/Q0NEQIcVvJNKvBqqWS9KuhKCtueonHZrcToKX7FWvTHQCsgJCbVlay5ufnpz8XfR/y5KfQuyvqTGS9JP8b5gcfst/nCEgqwKBgDEzY+Qwi5rZPxlBEDUZVA7PoiJ8szxxhB5zZ5DWA0qoptrt0rzp5ffcty/rdiEk/tang2k7XzySpMAxkHZ9yy/TJr1Z5DdzI27+3xiUI70Q7dHqNNPJrrGHJJyJBVU+pjhJcxt9O7ByMkNlZJsSTufkIx7rk/ElPgKWC6is0hmBAoGBAJifzEX/R+ARl2wFVQccw89OiMqVU67ElwSVUKM3EGFtfvT67y5Xa/qxknV7urfz1v8Y07kBI+SZO6NHU+elN/Rc6lEQ4I0afT8K6udB8bKwKXB/sl2ZHSJ3ypcnx3/jIouCWCMWN1+17LWu6gAfPycTz1pjJxTB0hhCwNgcz+EJAoGAEM7i8hx7fe0a/02v4K4v9fVfzjaieIxTpYYmyjvEQ+u1Ol3Jfsp9y01U0+zFoNrTMGqV+leshwxsyjIe4qqOmVn3hTqrbBhBLlRiO5xI4+4QA39JbSELCtMNrs3uJavz4KeaKAXHl1DJcZeHHvsunivzYGF+YIr7cmNvKgH4KVk=";
    public static final String RSA_PRIVATE = "";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @Override
    protected void initPresenter() {
       obligationPresenter = new ObligationPresenter(this);

    }

    @Override
    protected void initData() {
        obligationPresenter.findForSend();
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        obligationAdapter = new ObligationAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(obligationAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_obligation;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onfindForpay(FindForPayBean findForPayBean) {
        if (BuildConfig.DEBUG) Log.d("ObligationActivity", "findForSendbean:" + findForPayBean);
        Toast.makeText(this, ""+findForPayBean, Toast.LENGTH_SHORT).show();
        if (findForPayBean.getCode().equals("200")){
            if (BuildConfig.DEBUG) Log.d("ObligationActivity", "findForSendbean:" + findForPayBean.getResult());
            list.addAll(findForPayBean.getResult());
            obligationAdapter.notifyDataSetChanged();
        }


        obligationAdapter.setiRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ObligationActivity.this, "aaaa", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(ObligationActivity.this);
                builder.setIcon(R.drawable.msp_icon);
                builder.setMessage("确认支付该订单?");
                builder.setTitle("请选择");

                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        payV2();

                    }
                });

                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onItwmLongClick(int position) {

            }
        });



    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    String payMsg="";
                    if (TextUtils.equals(resultStatus, "9000")) {

                        payMsg = "支付成功";
                        Toast.makeText(ObligationActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                    } else {
                        payMsg = "支付失败";

                        Toast.makeText(ObligationActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }



                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户

                    } else {
                        // 其他状态值则为授权失败

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            Toast.makeText(this, "买了吗", Toast.LENGTH_SHORT).show();
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ObligationActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
