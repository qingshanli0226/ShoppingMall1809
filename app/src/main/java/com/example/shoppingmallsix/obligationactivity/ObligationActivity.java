package com.example.shoppingmallsix.obligationactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.shoppingmallsix.R;

public class ObligationActivity extends BaseActivity {
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
        return R.layout.activity_obligation;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
