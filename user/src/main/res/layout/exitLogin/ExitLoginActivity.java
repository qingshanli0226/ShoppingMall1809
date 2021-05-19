package com.fiannce.user.exitLogin;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.commond.CommonConstant;
import com.fiannce.commond.SpUtil;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheUserManager;
import com.fiannce.user.R;

public class ExitLoginActivity extends BaseActivity {


    private android.widget.ImageView ivUserIcon;
    private android.widget.TextView tvUserChange;
    private android.widget.Button btnUserLogout;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        btnUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CacheUserManager.getInstance().setLoginBean(null);
                SpUtil.putString(ExitLoginActivity.this, CommonConstant.SP_TOKEN, "");
//                FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
                ARouter.getInstance().build(getString(R.string.main_mainActivity)).navigation();
            }
        });
    }

    @Override
    protected void initView() {
        ivUserIcon = findViewById(R.id.iv_user_icon);
        tvUserChange = findViewById(R.id.tv_user_change);
        btnUserLogout = findViewById(R.id.btn_user_logout);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exit_login;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
