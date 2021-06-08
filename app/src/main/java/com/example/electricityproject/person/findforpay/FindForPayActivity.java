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
import com.example.common.db.MessageDataBase;
import com.example.common.db.MessageTable;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.manager.MessageManager;
import com.example.manager.SPMessageNum;
import com.example.manager.ShopCacheManger;
import com.example.pay.PayResult;

import java.util.List;
import java.util.Map;

public class FindForPayActivity extends BaseActivity<FindForPayPresenter> implements IFindForPayView,ShopCacheManger.iFindPayChangeListener{

    private FindForPayAdapter findForPayAdapter;
    private RecyclerView unpaidRe;
    private String orderInfo;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private  String outTradeNo;
    private int ThisPoi;
    private List<FindForPayBean.ResultBean> list;

    @Override
    protected void initData() {
        httpPresenter=new FindForPayPresenter(this);
        findForPayAdapter.setRecyclerItemClickListener(new BaseAdapter.iRecyclerItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                ThisPoi = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(FindForPayActivity.this);
                builder.setTitle(getResources().getString(R.string.findForPay_conPay));
                builder.setPositiveButton(getResources().getString(R.string.findForPay_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payV2();
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.findForPay_no), new DialogInterface.OnClickListener() {
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
        ShopCacheManger.getInstance().registerFindPayChange(this);
        findForPayAdapter=new FindForPayAdapter();
        unpaidRe.setLayoutManager(new LinearLayoutManager(FindForPayActivity.this));
        if (ShopCacheManger.getInstance().getFindPayList()!=null){
            list = ShopCacheManger.getInstance().getFindPayList();
            findForPayAdapter.updateData(list);
            for (FindForPayBean.ResultBean resultBean : list) {
                orderInfo= (String) resultBean.getOrderInfo();
                outTradeNo=resultBean.getTradeNo();
            }
        }else {
            ShopCacheManger.getInstance().getForPayData();
        }
        unpaidRe.setAdapter(findForPayAdapter);
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
        Toast.makeText(this, getResources().getString(R.string.home_network_connections), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DisConnect() {

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

            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    String payMsg="";
                    if (TextUtils.equals(resultStatus, "9000")) {
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,true);
                        payMsg = getResources().getString(R.string.orderDetails_pay_success);
                        Toast.makeText(FindForPayActivity.this, getResources().getString(R.string.orderDetails_pay_success), Toast.LENGTH_SHORT).show();

                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        resultBean.setOrderInfo(orderInfo);
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        resultBean.setStatus(payMsg);
                        List<FindForPayBean.ResultBean> paySussList = ShopCacheManger.getInstance().getPaySussList();
                        paySussList.add(resultBean);
                        //数据库数量加一
                        SPMessageNum.getInstance().addShopNum(1);
                        //添加到数据库
                        MessageDataBase.getInstance().payInsert(new MessageTable(null,payMsg,System.currentTimeMillis(),false));
                        //缓存数据
                        MessageManager.getInstance().addMessage(new MessageTable(null,payMsg,System.currentTimeMillis(),false));
                    } else {
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,false);
                        payMsg = getResources().getString(R.string.orderDetails_pay_fail);
                        Toast.makeText(FindForPayActivity.this, getResources().getString(R.string.orderDetails_pay_fail), Toast.LENGTH_SHORT).show();

                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        resultBean.setOrderInfo(orderInfo);
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        resultBean.setStatus(payMsg);
                        List<FindForPayBean.ResultBean> payFailList = ShopCacheManger.getInstance().getPayFailList();
                        payFailList.add(resultBean);
                        //数据库数量加一
                        SPMessageNum.getInstance().addShopNum(1);
                        //添加到数据库
                        MessageDataBase.getInstance().payInsert(new MessageTable(null,payMsg+payResult.getMemo(),System.currentTimeMillis(),false));
                        //缓存数据
                        MessageManager.getInstance().addMessage(new MessageTable(null,payMsg+payResult.getMemo(),System.currentTimeMillis(),false));
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShopCacheManger.getInstance().unregisterFindPayChange(this);
    }

    @Override
    public void OnFindPayChange() {
        list=ShopCacheManger.getInstance().getFindPayList();
        findForPayAdapter.updateData(list);
        findForPayAdapter.notifyDataSetChanged();
    }
}