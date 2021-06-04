package com.example.pay.shipments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.AwaitPaymentBean;
import com.example.net.bean.ShipmentBean;
import com.example.pay.R;
import com.example.pay.awaitpayment.AwaitPaymentActivity;
import com.example.pay.awaitpayment.AwaitPaymentAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShipmentsActivity extends BaseActivity implements  CacheAwaitPaymentManager.IShip {


    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView shipmentsRv;
    ShipmentAdapter shipmentAdapter;
    List<ShipmentBean.ResultBean> shipment = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_shipments;
    }

    @Override
    public void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setToolbarOnClickLisenter(this);
        shipmentsRv = (RecyclerView) findViewById(R.id.shipmentsRv);
        CacheAwaitPaymentManager.getInstance().registerShip(this);
    }

    @Override
    public void initPresenter() {

    }

//    @Override
//    public void initPresenter() {
//        mPresenter = new ShipmentPresenter(this);
//        if (CacheConnectManager.getInstance().isConnect()) {
//            mPresenter.getpay();
//        } else {
//            Toast.makeText(this, "网络走丢了", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void initData() {
        shipmentAdapter = new ShipmentAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        shipmentsRv.setAdapter(shipmentAdapter);
        shipmentsRv.setLayoutManager(linearLayoutManager);

        shipment = CacheAwaitPaymentManager.getInstance().getShipment();
        shipmentAdapter.getData().addAll(shipment);
        shipmentAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        Bundle bundle = new Bundle();
        bundle.putInt("page",4);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
    }

    @Override
    public void onClickRight() {

    }

//    @Override
//    public void onShipment(ShipmentBean shipmentBean) {
//
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void showError(String error) {
//
//    }

    @Override
    public void onConect() {
        super.onConect();
//        mPresenter.getpay();
        Toast.makeText(this, "正在缓冲...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        super.onDisConnect();
    }

    @Override
    public void onShip(List<ShipmentBean.ResultBean> shipment) {
        this.shipment = shipment;
        shipmentAdapter.updata(shipment);
    }

    @Override
    public void onAddShip(int position) {
        shipmentAdapter.notifyItemChanged(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheAwaitPaymentManager.getInstance().unRegisterShip(this);
    }
}
