package com.example.pay;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.db.MessageTable;
import com.example.framework.manager.MessageManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.FindForBean;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.RegisterBean;

import java.util.Map;

@Route(path = "/pay/PayActivity")
public class PayActivity extends BaseActivity<PayPresenter> implements IPayView {

    private OrderinfoBean orderinfoBean;
    private float money;
    private ToolBar toolbar;
    private TextView payBut;
    private static final int SDK_PAY_FLAG = 1;
    private Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SDK_PAY_FLAG:
                    boolean isSucceed = false;
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    String resultStatus = result.get("resultStatus");
                    if (resultStatus.equals("9000")){
                        Toast.makeText(PayActivity.this, getResources().getString(R.string.paySuccess), Toast.LENGTH_SHORT).show();
                        isSucceed = true;
                        FindForBean.ResultBean resultBean = new FindForBean.ResultBean();
                        resultBean.setTotalPrice(money+"");
                        resultBean.setTime(System.currentTimeMillis()+"");
                        resultBean.setTradeNo(orderinfoBean.getResult().getOutTradeNo());
                        ShoppingCarManager.getInstance().addForSend(resultBean);
                        finish();
                    }else {
                        if (resultStatus.equals("8000")) {
                            Toast.makeText(PayActivity.this, getResources().getString(R.string.inTheConfirmation), Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (resultStatus.equals("6001")) { //用户中途取消
                            Toast.makeText(PayActivity.this, getResources().getString(R.string.cancelThePayment), Toast.LENGTH_SHORT).show();
                            FindForBean.ResultBean resultBean = new FindForBean.ResultBean();
                            resultBean.setTotalPrice(money+"");
                            resultBean.setTime(System.currentTimeMillis()+"");
                            resultBean.setTradeNo(orderinfoBean.getResult().getOutTradeNo());
                            resultBean.setOrderInfo(orderinfoBean.getResult().getOrderInfo());
                            ShoppingCarManager.getInstance().addForPay(resultBean);
                        } else {
                            // 其他值就可以判断为支付失败
                            Toast.makeText(PayActivity.this, getResources().getString(R.string.paymentFailed), Toast.LENGTH_SHORT).show();
                            FindForBean.ResultBean resultBean = new FindForBean.ResultBean();
                            resultBean.setTotalPrice(money+"");
                            resultBean.setTime(System.currentTimeMillis()+"");
                            resultBean.setTradeNo(orderinfoBean.getResult().getOutTradeNo());
                            resultBean.setOrderInfo(orderinfoBean.getResult().getOrderInfo());
                            ShoppingCarManager.getInstance().addForPay(resultBean);
                        }
                    }
                    OrderinfoBean.ResultBean orderinfoBeanResult = orderinfoBean.getResult();
                    httpPresenter.getConfirmServerPayResult(orderinfoBeanResult.getOutTradeNo(),orderinfoBeanResult.getOrderInfo(),isSucceed);
                    String message =  getResources().getString(R.string.paymentFailed);
                    if (isSucceed) {
                        message =  getResources().getString(R.string.paySuccess);
                    }
                    MessageTable messageTable = new MessageTable(null,message,System.currentTimeMillis()+"",true);
                    MessageManager.getInstance().setMessage(messageTable);
                    MessageManager.getInstance().addCount();
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

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
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

    @Override
    public void getConfirmServerPayResult(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")){
//            Toast.makeText(this, "请求服务端成功", Toast.LENGTH_SHORT).show();
        }
    }
}