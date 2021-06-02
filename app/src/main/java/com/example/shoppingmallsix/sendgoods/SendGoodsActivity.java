package com.example.shoppingmallsix.sendgoods;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.find.FindForPayBean;
import com.example.net.bean.find.FindForSendbean;
import com.example.shoppingmallsix.R;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.List;

public class SendGoodsActivity extends BaseActivity<SendgooodsrPresneter> implements Isendgoods {

    private ToolBar toolbar;
    private  SendgooodsrPresneter sendgooodsrPresneter;
    private RecyclerView rv;
    private List<FindForSendbean.ResultBean> list = new ArrayList<>();
    private SendgoodsAdapter sendgoodsAdapter;
    @Override
    protected void initPresenter() {
        sendgooodsrPresneter = new SendgooodsrPresneter(this);

    }

    @Override
    protected void initData() {
        sendgooodsrPresneter.getFindForPay();
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        PushAgent.getInstance(this).onAppStart();
        sendgoodsAdapter = new SendgoodsAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(sendgoodsAdapter);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_goods;
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
    public void onfindForSend(FindForSendbean findForSendbean) {
        if (findForSendbean.getCode().equals("200")){

            list.addAll(findForSendbean.getResult());
            sendgoodsAdapter.notifyDataSetChanged();
        }
    }
}
