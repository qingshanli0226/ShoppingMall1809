package com.example.pay.user.findforpay;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.db.MessageTable;
import com.example.framework.manager.MessageManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.FindForBean;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.RegisterBean;
import com.example.pay.IPayView;
import com.example.pay.PayPresenter;
import com.example.pay.R;
import com.example.pay.user.adapter.FindForAdapter;

import java.util.List;
import java.util.Map;

@Route(path = "/pay/FindForPayActivity")
public class FindForPayActivity extends BaseActivity<PayPresenter> implements IPayView {

    private RecyclerView shopActPayRv;
    private static final int SDK_PAY_FLAG = 1;
    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    boolean isSucceed = false;
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    String resultStatus = result.get("resultStatus");
                    if (resultStatus.equals("9000")) {
                        Toast.makeText(FindForPayActivity.this, getResources().getString(R.string.paySuccess), Toast.LENGTH_SHORT).show();
                        isSucceed = true;
                        FindForBean.ResultBean resultBean = new FindForBean.ResultBean();
                        resultBean.setTime(System.currentTimeMillis() + "");
                        resultBean.setTradeNo(findForBean.getTradeNo());
                        ShoppingCarManager.getInstance().addForSend(resultBean);
                        ShoppingCarManager.getInstance().deleteForPayLast(findForBean);
                        finish();
                    } else {
                        if (resultStatus.equals("8000")) {
                            Toast.makeText(FindForPayActivity.this, getResources().getString(R.string.inTheConfirmation), Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (resultStatus.equals("6001")) { //用户中途取消
                            Toast.makeText(FindForPayActivity.this, getResources().getString(R.string.cancelThePayment), Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败
                            Toast.makeText(FindForPayActivity.this, getResources().getString(R.string.paymentFailed), Toast.LENGTH_SHORT).show();

                        }
                    }
                    OrderinfoBean.ResultBean orderinfoBeanResult = new OrderinfoBean.ResultBean(findForBean.getOrderInfo().toString(),findForBean.getTradeNo());
                    httpPresenter.getConfirmServerPayResult(orderinfoBeanResult.getOutTradeNo(), orderinfoBeanResult.getOrderInfo(), isSucceed);
                    String message = getResources().getString(R.string.paymentFailed);
                    if (isSucceed) {
                        message = getResources().getString(R.string.paySuccess);
                    }
                    MessageTable messageTable = new MessageTable(null, message, System.currentTimeMillis() + "", true);
                    MessageManager.getInstance().setMessage(messageTable);
                    MessageManager.getInstance().addCount();
                    break;
            }

        }
    };
    private  FindForBean.ResultBean findForBean ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    protected void initData() {
        List<FindForBean.ResultBean> forPay = ShoppingCarManager.getInstance().getForPay();
        LogUtils.json(forPay);
        if (forPay != null) {
            FindForAdapter findForPayAdapter = new FindForAdapter(forPay);
            shopActPayRv.setAdapter(findForPayAdapter);


            findForPayAdapter.setRecyclerItemClickListener(new BaseRVAdapter.IRecyclerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FindForPayActivity.this);
                    builder.setTitle(getResources().getString(R.string.hint));
                    builder.setMessage(getResources().getString(R.string.areYouSureYouWantToContinuePaying));
                    builder.setPositiveButton(getResources().getString(R.string.confirm), (dialogInterface, i) -> {
                        findForBean = forPay.get(position);
                        new Thread(() -> {
                            PayTask payTask = new PayTask(FindForPayActivity.this);
                            Map<String, String> result = payTask.payV2(findForBean.getOrderInfo().toString(), false);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            handler.sendMessage(msg);

                        }).start();
                    });
                    builder.setNegativeButton(getResources().getString(R.string.cancel), (dialogInterface, i) -> {
                    });
                    builder.show();
                }

                @Override
                public void onItemLongClick(int position) {

                }
            });
        }
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new PayPresenter(this);
    }

    @Override
    protected void initView() {
        shopActPayRv = (RecyclerView) findViewById(R.id.shop_act_pay_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FindForPayActivity.this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        shopActPayRv.setLayoutManager(linearLayoutManager);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }

    @Override
    public void getConfirmServerPayResult(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
//            Toast.makeText(this, "请求服务端成功", Toast.LENGTH_SHORT).show();
        }
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
}