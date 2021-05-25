package com.example.shoppingmallsix.welcomeactivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commened.FiannceContants;
import com.example.commened.SpUtil;
import com.example.framework.BaseActivity;
import com.example.net.bean.HomeBean;
import com.example.shoppingmallsix.R;
import com.example.user.service.AutoService;


public class WelcomeActivity extends BaseActivity {

    private TextView coundDownTv;
    private int countDown = 3;

    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean advertistFinsh = false;
    private Intent intent;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        intent = new Intent(this, AutoService.class);
        startService(intent);
    }


    @Override
    protected void initView() {
        coundDownTv = findViewById(R.id.countDownTv);
        setTheme(R.style.AppTheme);
        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
        coundDownTv.setText(countDown + getString(R.string.miao));

        if (!SpUtil.getString(this, FiannceContants.TOKEN_KEY).equals("")) {
            Intent intent = new Intent(this, AutoService.class);
            startService(intent);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0) {
                        coundDownTv.setText(countDown + getString(R.string.miao));
                        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
                    } else {
                        coundDownTv.setText(countDown + getString(R.string.miao));
                        advertistFinsh = true;
                        ARouter.getInstance().build("/main/MainActivity").navigation();
                        finish();
                    }
                    break;
            }
        }
    };


    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }
}
