package com.example.user.addresslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.user.R;

@Route(path = "/address/AddressListActivity")
public class AddressListActivity extends BaseActivity {

    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView addressListRv;
    private android.widget.TextView defaultl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        addressListRv = (RecyclerView) findViewById(R.id.addressList_rv);
        defaultl = (TextView) findViewById(R.id.defaultl);
    }

    @Override
    public void onRightTitle() {
        super.onRightTitle();
        ARouter.getInstance().build(ShopConstants.SHOP_ADDRESS).navigation();
    }
}