package com.example.pay.shipments;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.ShipmentBean;
import com.example.pay.R;


public class ShipmentsActivity extends BaseActivity<ShipmentPresenter> implements IShipmentView {


    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView shipmentsRv;

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
        LogUtils.json("ship"+shipmentBean.getCode());
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
