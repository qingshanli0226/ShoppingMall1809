package com.example.framework;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.ActivityManger;
import com.example.common.SpUtils;
import com.example.common.bean.LogBean;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessNetManager;
import com.example.manager.BusinessUserManager;
import com.example.view.LoadingPage;
import com.example.view.ToolBar;
import com.umeng.message.PushAgent;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView, ToolBar.IToolbarListener, BusinessNetManager.NetConnectListener, BusinessUserManager.IUserLoginChanged {

    protected T httpPresenter;
    protected ToolBar toolBar;
    protected LoadingPage loadingPage;
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
        // 隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolBar = findViewById(R.id.toolbar);
        initPresenter();
        initData();
        toolBar.setToolbarListener(this);
        BusinessNetManager.getInstance().RegisterConnect(this);
        ActivityManger.getInstance().register(this);
        PushAgent.getInstance(this).onAppStart();
        BusinessUserManager.getInstance().Register(this);
    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        BusinessNetManager.getInstance().UnRegisterConnect(this);
        ActivityManger.getInstance().unregister(this);
        BusinessUserManager.getInstance().UnRegister(this);
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
            String times = System.currentTimeMillis() + "";
            SpUtils.putTime(FrameModel.context,times);
            BusinessARouter.getInstance().getAppManager().OpenAdvertActivity(FrameModel.context,null);
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

    @Override
    public void onLoginChange(LogBean isLog) {

    }
}
