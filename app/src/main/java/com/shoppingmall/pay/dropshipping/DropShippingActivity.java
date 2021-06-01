package com.shoppingmall.pay.dropshipping;

import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.shoppingmall.R;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.FindForSendBean;

public class DropShippingActivity extends BaseActivity<DropShippingPresenter> implements IDropShippingView {

    private android.widget.ImageView detailBack;
    private android.widget.ImageView detailMenu;
    private androidx.recyclerview.widget.RecyclerView dropRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_drop_shipping;
    }

    @Override
    public void initView() {

        detailBack = (ImageView) findViewById(R.id.detailBack);
        detailMenu = (ImageView) findViewById(R.id.detailMenu);
        dropRv = (RecyclerView) findViewById(R.id.dropRv);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new DropShippingPresenter(this);
    }

    @Override
    public void initData() {
        httpPresenter.getFindForSend();
    }

    @Override
    public void getFindForSend(FindForSendBean findForSendBean) {

    }
}