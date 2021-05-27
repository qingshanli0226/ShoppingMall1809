package com.example.shoppingmallsix.sendgoods;

import android.view.View;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.shoppingmallsix.R;

public class SendGoodsActivity extends BaseActivity {

    private ToolBar toolbar;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);

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
}
