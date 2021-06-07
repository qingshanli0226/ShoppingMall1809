package com.example.user.removelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commom.ShopConstants;
import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.ShopeUserManager;
import com.example.net.model.RegisterBean;
import com.example.user.R;
import com.example.user.register.IRegisterView;

@Route(path = "/user/RemoveActivity")
public class RemoveActivity extends BaseActivity<RemovePresenter> implements IRemoveView {

    private TextView actUserBacklogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remove;
    }

    @Override
    protected void initData() {
        actUserBacklogin.setOnClickListener(view -> {
            httpPresenter.getUnlockData();
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RemovePresenter(this);
    }

    @Override
    protected void initView() {

        actUserBacklogin = (TextView) findViewById(R.id.act_user_backlogin);
    }

    @Override
    public void onUserData(RegisterBean unlockBean) {
        if (unlockBean.getCode().equals("200")){
            SpUtil.setString(this, ShopConstants.TOKEN_KEY, null);
            ShopeUserManager.getInstance().setLoginBean(null);
            finish();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }
}