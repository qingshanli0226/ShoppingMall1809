package com.example.framework;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.ActivityManger;
import com.example.common.LogUtils;
import com.example.common.SpUtils;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessNetManager;
import com.example.view.LoadingPage;
import com.example.view.ToolBar;
import com.umeng.message.PushAgent;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView, ToolBar.IToolbarListener, BusinessNetManager.NetConnectListener{

    protected T httpPresenter;
    protected ToolBar toolBar;
    protected LoadingPage loadingPage;
    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;
    private String time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
//        setStatusBar();
        toolBar = findViewById(R.id.toolbar);
        initPresenter();
        initData();
        toolBar.setToolbarListener(this);
        BusinessNetManager.getInstance().RegisterConnect(this);
        ActivityManger.getInstance().register(this);
        PushAgent.getInstance(this).onAppStart();
    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        BusinessNetManager.getInstance().UnRegisterConnect(this);
        ActivityManger.getInstance().unregister(this);
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        time = System.currentTimeMillis()+"";
        SpUtils.putTime(FrameModel.context,System.currentTimeMillis()+"");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        String time = SpUtils.getTime(FrameModel.context);
        long newTime = Long.parseLong(time);
        if (System.currentTimeMillis() - newTime > 5 * 1000){
            LogUtils.i("当前时间超过5秒，弹出广告业");
            String times = System.currentTimeMillis() + "";
            SpUtils.putTime(FrameModel.context,times);

            BusinessARouter.getInstance().getAppManager().OpenAdvertActivity(FrameModel.context,null);

        }else {
            Toast.makeText(this, "时间未到5秒", Toast.LENGTH_SHORT).show();
        }

    }

    public void onLeftClick() {
        finish();
        destroy();
    }


    public void onRightImgClick() {

    }


    public void onRightTvClick() {

    }

    @Override
    public void OnConnect() {

    }

    @Override
    public void DisConnect() {

    }

}
