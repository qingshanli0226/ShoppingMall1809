package com.example.threeshopping.setting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.example.net.bean.LogoutBean;
import com.example.threeshopping.R;

public class SettingActivity extends BaseActivity<SettingPresenter> implements ISettingView
{

    private android.widget.LinearLayout personalinformation;
    private android.widget.Button unlogin;
    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView head;
    private android.widget.TextView name;

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
        name = (TextView) findViewById(R.id.name);
    }

    @Override
    public void initPresenter() {
       mPresenter = new SettingPresenter(this);

    }

    @Override
    public void initData() {



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
                        finish();
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
        if (logoutBean.getCode().equals("200")){

            CacheAddrManager.getInstance().setAddrBeans(null);
            CacheAwaitPaymentManager.getInstance().setAwaitpayment(null);
            CacheAwaitPaymentManager.getInstance().setShipment(null);
            CacheMessageManager.getInstance().addMessage(null);
            CacheShopManager.getInstance().setCarts(null);
            CacheShopManager.getInstance().setCheck(0,false);
            CacheShopManager.getInstance().setChackAll(false);
            CacheShopManager.getInstance().setNum(0,null);
            CacheUserManager.getInstance().setLoginBean(null);
            SpUtil.clean(this);
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
}
