package com.example.electricityproject.person;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PersonFragment extends BaseFragment {


    private ToolBar toolbar;
    private TextView pleaseLogin;
    private LogBean logBean;

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);

        pleaseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BusinessUserManager.getInstance().getIsLog()!=null){
                    LogBean isLog = BusinessUserManager.getInstance().getIsLog();
                    pleaseLogin.setText(isLog.getResult().getName()+"");
                    Toast.makeText(getContext(), "当前"+isLog.getResult().getName()+"用户已经登陆", Toast.LENGTH_SHORT).show();
                }else {
                    BusinessARouter.getInstance().getUserManager().OpenLogActivity(getContext(),null);
                }

            }
        });

    }

    @Subscribe
    public void EvenBus(String eve){
        if (eve.equals("acc_username")){
            Toast.makeText(getContext(), "123456", Toast.LENGTH_SHORT).show();
            logBean = BusinessUserManager.getInstance().getIsLog();
            pleaseLogin.setText(""+logBean.getResult().getName());
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        pleaseLogin = (TextView) findViewById(R.id.please_login);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
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
    public void onLoginChange(LogBean isLog) {
        logBean = isLog;
    }




}