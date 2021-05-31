package com.example.electricityproject.person.findforpay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.example.adapter.BaseAdapter;
import com.example.common.bean.ConfirmServerPayResultBean;
import com.example.common.bean.FindForPayBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.manager.ShopCacheManger;
import com.example.pay.alipay.sdk.pay.demo.PayResult;

import java.util.List;
import java.util.Map;

public class FindForPayActivity extends BaseActivity<FindForPayPresenter> implements IFindForPayView {
    private FindForPayAdapter findForPayAdapter;
    private RecyclerView unpaidRe;
    private String orderInfo;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private  String outTradeNo;
    private int ThisPoi;

    @Override
    protected void initData() {
        httpPresenter = new FindForPayPresenter(this);
        httpPresenter.getForPayData();
        findForPayAdapter.setRecyclerItemClickListener(new BaseAdapter.iRecyclerItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                ThisPoi = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(FindForPayActivity.this);
                builder.setTitle("确认支付该订单");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payV2();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

            @Override
            public void OnItemLongClick(int position) {

            }
        });
    }

    @Override
    protected void initPresenter() {


    }

    @Override
    protected void initView() {
        unpaidRe = (RecyclerView) findViewById(R.id.unpaid_re);

        findForPayAdapter=new FindForPayAdapter();
        unpaidRe.setLayoutManager(new LinearLayoutManager(FindForPayActivity.this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showErrorView();

    }
    //网络从断开变为已连接,重新加载数据
    @Override
    public void OnConnect() {
        Toast.makeText(this, "网络重新连接,重新加载数据", Toast.LENGTH_SHORT).show();
        httpPresenter.getForPayData();
    }

    @Override
    public void DisConnect() {

    }

    @Override
    public void getFindForPayData(FindForPayBean findForPayBean) {
        if (findForPayBean.getCode().equals("200")){

            List<FindForPayBean.ResultBean> result = findForPayBean.getResult();
            for (int i = 0; i < result.size(); i++) {
                 orderInfo = (String) result.get(ThisPoi).getOrderInfo();
                 outTradeNo = (String) result.get(ThisPoi).getTradeNo();
            }

            findForPayAdapter.updateData(findForPayBean.getResult());
            unpaidRe.setAdapter(findForPayAdapter);

        }
    }

    @Override
    public void onConfirmServerPayResultOk(ConfirmServerPayResultBean bean) {

    }

    public void payV2() {
        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(FindForPayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
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

//            daoMaster = MessageManger.getInstance().getDaoMaster();

            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    String payMsg="";
                    if (TextUtils.equals(resultStatus, "9000")) {
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,true);
                        payMsg="支付成功";
                        Toast.makeText(FindForPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        resultBean.setOrderInfo(orderInfo);
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        resultBean.setStatus(payMsg);
                        List<FindForPayBean.ResultBean> paySussList = ShopCacheManger.getInstance().getPaySussList();
                        paySussList.add(resultBean);

                    } else {

                        payMsg="支付失败";
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,false);
                        Toast.makeText(FindForPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        resultBean.setOrderInfo(orderInfo);
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        resultBean.setStatus(payMsg);
                        List<FindForPayBean.ResultBean> payFailList = ShopCacheManger.getInstance().getPayFailList();
                        payFailList.add(resultBean);

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


}