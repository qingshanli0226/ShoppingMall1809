package com.example.electricityproject.shopp.orderdetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.common.LogUtils;
import com.example.common.bean.ConfirmServerPayResultBean;
import com.example.common.bean.FindForPayBean;
import com.example.common.bean.FindForSendBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.db.DaoMaster;
import com.example.common.db.MessageDataBase;
import com.example.common.db.MessageTable;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.manager.MessageManager;
import com.example.manager.SPMessageNum;
import com.example.manager.ShopCacheManger;
import com.example.pay.PayResult;
import com.example.view.ToolBar;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsActivityPresenter> implements OrderDetailsActivityIView,ToolBar.IToolbarListener, MessageDataBase.iMessageListener {

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String outTradeNo;
    private String orderInfo;
    private com.example.view.ToolBar toolbar;
    private android.widget.TextView username;
    private android.widget.TextView userPhone;
    private android.widget.TextView userAddress;
    private androidx.recyclerview.widget.RecyclerView orderRv;
    private android.widget.TextView allMoney;
    private TextView productPrice;
    private OrderDetailsAdapter orderDetailsAdapter;
    private List<ShortcartProductBean.ResultBean> list;
    private android.widget.Button goBuy;
    private DaoMaster daoMaster;

    public void payV2(View v) {
        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailsActivity.this);
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

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            daoMaster = MessageDataBase.getInstance().getDaoMaster();
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    String payMsg="";
                    LogUtils.i(resultStatus+"");
                    if (TextUtils.equals(resultStatus, "9000")) {
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,true);
                        payMsg = getResources().getString(R.string.orderDetails_pay_success);
                        Toast.makeText(OrderDetailsActivity.this,""+payMsg, Toast.LENGTH_SHORT).show();

                        FindForSendBean.ResultBean resultBean = new FindForSendBean.ResultBean();
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        resultBean.setStatus(payMsg);
                        ShopCacheManger.getInstance().addFindShop(resultBean);

                        //数据库数量加一
                        SPMessageNum.getInstance().addShopNum(1);
                        //添加到数据库
                        MessageDataBase.getInstance().payInsert(new MessageTable(null,payMsg,System.currentTimeMillis(),false));
                        //缓存数据添加
                        MessageManager.getInstance().addMessage(new MessageTable(null,payMsg,System.currentTimeMillis(),false));
                        EventBus.getDefault().post("del");
                        list.clear();
                    } else {
                        payMsg = getResources().getString(R.string.orderDetails_pay_fail);
                        httpPresenter.confirmServerPayResult(outTradeNo,payResult,false);
                        Toast.makeText(OrderDetailsActivity.this, ""+payMsg, Toast.LENGTH_SHORT).show();

                        FindForPayBean.ResultBean resultBean = new FindForPayBean.ResultBean();
                        resultBean.setOrderInfo(orderInfo);
                        resultBean.setTradeNo(outTradeNo);
                        resultBean.setTime(System.currentTimeMillis()+"");
                        resultBean.setStatus(payMsg);
                        List<FindForPayBean.ResultBean> payFailList = ShopCacheManger.getInstance().getPayFailList();
                        ShopCacheManger.getInstance().addFindPay(resultBean);

                        //添加到支付成功缓存
                        payFailList.add(resultBean);

                        //添加到消息缓存
                        ShopCacheManger.getInstance().setMessageList(payFailList);

                        //数据库数量加一
                        SPMessageNum.getInstance().addShopNum(1);
                        //添加到数据库
                        MessageDataBase.getInstance().payInsert(new MessageTable(null,payMsg+payResult.getMemo(),System.currentTimeMillis(),false));
                        //缓存数据添加
                        MessageManager.getInstance().addMessage(new MessageTable(null,payMsg+payResult.getMemo(),System.currentTimeMillis(),false));

                        EventBus.getDefault().post("del");
                        list.clear();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

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

        list = ShopCacheManger.getInstance().getSelectList();

        float sumPrice=0;
        for (ShortcartProductBean.ResultBean resultBean : list) {
            float productPrice=Float.parseFloat(resultBean.getProductPrice()+"");
            int productNum=Integer.parseInt(resultBean.getProductNum());
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
        MessageDataBase.getInstance().register(this);
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

    @Override
    public void onMessageNumListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageDataBase.getInstance().unregister(this);

    }
}