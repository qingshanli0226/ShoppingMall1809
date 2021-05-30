package com.example.myapplication.findforpay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.framework.BaseActivity;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.PaySendCacheManager;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.payorder.OrderActivity;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.example.pay.PayActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindPayMainActivity extends BaseActivity<FindpayPresenter> implements IFindPayView {


    private androidx.recyclerview.widget.RecyclerView rv;
    private FindPayAdapter findPayAdapter;
    private List<FindForPayBean.ResultBean> list=new ArrayList<>();

    @Override
    protected int bandLayout() {
        return R.layout.activity_find_pay_main;
    }


    @Override
    public void initView() {
        rv = findViewById(R.id.rv);
        findPayAdapter = new FindPayAdapter();

    }

    @Override
    public void initPresenter() {
        findPayAdapter.setRecyclerItemClickListener(new BaseRecyclerViewAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FindPayMainActivity.this);
                builder.setMessage("确认支付该订单");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FindPayMainActivity.this, PayActivity.class);
                        startActivity(intent);
                        list.remove(position);
                        findPayAdapter.notifyDataSetChanged();
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
    public void initData() {
        mPresenter = new FindpayPresenter(this);
        mPresenter.getFindPay();

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
    public void onFindPay(FindForPayBean findForPayBean) {
        list.addAll(findForPayBean.getResult());
        findPayAdapter.updataData(findForPayBean.getResult());
        rv.setAdapter(findPayAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFindSend(FindForSendBean findForSendBean) {

    }




}
