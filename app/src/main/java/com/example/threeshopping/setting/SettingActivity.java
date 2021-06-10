package com.example.threeshopping.setting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.common.Constants;
import com.example.common.SpUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheAddrManager;
import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheMessageManager;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.ShopCrashHandler;
import com.example.framework.view.ToolBar;
import com.example.net.bean.EventBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.LogoutBean;
import com.example.threeshopping.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SettingActivity extends BaseActivity<SettingPresenter> implements ISettingView {

    private android.widget.LinearLayout personalinformation;
    private android.widget.Button unlogin;
    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView head;
    private android.widget.TextView nickname;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        personalinformation = (LinearLayout) findViewById(R.id.personalinformation);
        unlogin = (Button) findViewById(R.id.unlogin);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        head = (ImageView) findViewById(R.id.head);
        nickname = (TextView) findViewById(R.id.nickname);

        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        LoginBean.ResultBean result = loginBean.getResult();
        String name = result.getName();
        if (loginBean != null) {
            nickname.setText("" + name);
        }

    }

    @Override
    public void initPresenter() {
        mPresenter = new SettingPresenter(this);
    }

    @Subscribe
    public void getEventBus(String path) {
        Glide.with(this).load(path).transform(new CircleCrop()).into(head);
    }

    @Override
    public void initData() {

        String getpath = SpUtil.getpath(this);
        Glide.with(this).load(getpath).transform(new CircleCrop()).into(head);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        personalinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_INFORMATION).navigation();
            }
        });

        unlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("确定退出登录");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.getLogout();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public void onLogout(LogoutBean logoutBean) {
        if (logoutBean.getCode().equals("200")) {
            Toast.makeText(this, "logout" + logoutBean.getMessage(), Toast.LENGTH_SHORT).show();

            CacheUserManager.getInstance().setLoginBean(null);
            SpUtil.clean(this);

            Bundle bundle = new Bundle();
            bundle.putInt("page", 0);
            CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
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
    public void showError(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
