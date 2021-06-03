package com.example.myapplication.findforpay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.common.log.LogUtil;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.PaySendCacheManager;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.payorder.OrderActivity;
import com.example.myapplication.shoporder.Iorder;
import com.example.myapplication.shoporder.ShoporederPresenter;
import com.example.net.bean.ConfirmServerPayResultBean;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.example.net.bean.OrderinfoBean;
import com.example.pay.PayActivity;
import com.example.pay.PayResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindPayMainActivity extends BaseActivity<ShoporederPresenter> implements Iorder {


    private androidx.recyclerview.widget.RecyclerView rv;
    private FindPayAdapter findPayAdapter;
    private List<FindForPayBean.ResultBean> list=new ArrayList<>();
    private final int a=1;
    private OrderinfoBean order;
    private String tradeNo;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case a:
                    Log.i("SSS","456");

                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultStatus = payResult.getResultStatus();

                    Toast.makeText(FindPayMainActivity.this, resultStatus, Toast.LENGTH_SHORT).show();
                    if (resultStatus.equals("9000")) {
                        mPresenter.confrimserver(tradeNo,payResult,true);
                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        for (FindForPayBean.ResultBean bean:list) {
                            if (bean.getTradeNo().equals(tradeNo)){
                                resultBean=bean;
                            }
                        }
                        FindForSendBean.ResultBean resultBean1 = new FindForSendBean.ResultBean();
                        resultBean1.setTime(resultBean.getTime());
                        resultBean1.setTotalPrice(resultBean.getTotalPrice());
                        resultBean1.setTradeNo(resultBean.getTradeNo());
                        PaySendCacheManager.getInstance().getSendList().add(resultBean1);

                        Toast.makeText(FindPayMainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(FindPayMainActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(FindPayMainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }


    };
    @Override
    protected int bandLayout() {
        return R.layout.activity_find_pay_main;
    }


    @Override
    public void initView() {
        rv = findViewById(R.id.rv);
        findPayAdapter = new FindPayAdapter(list);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }

    @Override
    public void initPresenter() {
        mPresenter = new ShoporederPresenter(this);
    }

    @Override
    public void initData() {

        Intent intent = getIntent();

        FindForPayBean findForPayBean = PaySendCacheManager.getInstance().getFindForPayBean();
        list.addAll(findForPayBean.getResult());
        rv.setAdapter(findPayAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        findPayAdapter.setRecyclerItemClickListener(new BaseRecyclerViewAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FindPayMainActivity.this);
                builder.setMessage("确认支付该订单");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Log.i("SSS","123");
                        FindForPayBean.ResultBean resultBean = list.get(position);
                         tradeNo = resultBean.getTradeNo();

//                        list.remove(position);
//                        findPayAdapter.notifyDataSetChanged();
                        PaySendCacheManager.getInstance().setonIndex(list.size());

//                        Intent intent = new Intent(FindPayMainActivity.this, PayActivity.class);
//                        startActivity(intent);

                         order = (OrderinfoBean) intent.getSerializableExtra("order");
                         Log.i("SSS","order"+order);

                                if (order!=null){
                                    String orderInfo = order.getResult().getOrderInfo();
                                    Log.i("SSS","order"+orderInfo);

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            PayTask payTask = new PayTask(FindPayMainActivity.this);
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

                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

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
    public void destroy() {
        super.destroy();

    }




    @Override
    public void onConfirmServer(ConfirmServerPayResultBean confirmServerPayResultBean) {

    }
}
