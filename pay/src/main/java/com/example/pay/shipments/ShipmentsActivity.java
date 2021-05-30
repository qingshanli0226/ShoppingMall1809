package com.example.pay.shipments;

import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.view.ToolBar;
import com.example.net.bean.AwaitPaymentBean;
import com.example.net.bean.ShipmentBean;
import com.example.pay.R;
import com.example.pay.awaitpayment.AwaitPaymentActivity;
import com.example.pay.awaitpayment.AwaitPaymentAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShipmentsActivity extends BaseActivity<ShipmentPresenter> implements IShipmentView {


    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView shipmentsRv;
    ShipmentAdapter shipmentAdapter;
    List<ShipmentBean.ResultBean> list = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_shipments;
    }

    @Override
    public void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setToolbarOnClickLisenter(this);
        shipmentsRv = (RecyclerView) findViewById(R.id.shipmentsRv);
    }

    @Override
    public void initPresenter() {
        mPresenter = new ShipmentPresenter(this);
        mPresenter.getpay();
    }

    @Override
    public void initData() {
        shipmentAdapter = new ShipmentAdapter();
        shipmentsRv.setAdapter(shipmentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        shipmentsRv.setLayoutManager(linearLayoutManager);

        shipmentAdapter.setRvItemOnClickListener(new BaseRvAdapter.IRvItemOnClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShipmentsActivity.this);
                builder.setTitle("确认支付该订单");
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
            @Override
            public boolean onLongItemClick(int position, View view) {
                return false;
            }
        });

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onShipment(ShipmentBean shipmentBean) {
        List<ShipmentBean.ResultBean> result = shipmentBean.getResult();
        list.addAll(result);
        shipmentAdapter.getData().addAll(list);

        shipmentAdapter.notifyDataSetChanged();
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
}
