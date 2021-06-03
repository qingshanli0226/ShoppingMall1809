package com.example.user.user;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.EventBean;
import com.example.user.R;
import com.example.user.user.login.LoginFragment;
import com.example.user.user.register.RegisterFragment;
import com.fiannce.sql.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends BaseActivity implements ToolBar.OnClickListener {


    private android.widget.LinearLayout userLL;
    private RegisterFragment registerFragment;
    private LoginFragment loginFragment;


    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public void initView() {
        userLL = (LinearLayout) findViewById(R.id.userLL);
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        fragments.add(loginFragment);
        fragments.add(registerFragment);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.userLL, fragments.get(0));
        fragmentTransaction.add(R.id.userLL, fragments.get(1));
        fragmentTransaction.commit();
        fragmentTransaction.show(loginFragment);
        fragmentTransaction.hide(registerFragment);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe
    public void getEventBus(EventBean eventBean) {

        if(eventBean.getType() == 0){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (eventBean.getFlag()) {
                case 0:
                    fragmentTransaction.show(fragments.get(0));
                    fragmentTransaction.hide(fragments.get(1));
                    fragmentTransaction.commit();
                    break;
                case 1:
                    fragmentTransaction.show(fragments.get(1));
                    fragmentTransaction.hide(fragments.get(0));
                    fragmentTransaction.commit();
                    break;
            }
        }



    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
}
