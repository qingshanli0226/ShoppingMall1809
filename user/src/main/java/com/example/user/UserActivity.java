package com.example.user;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.framework.manager.CaCheArote;

import com.example.user.frag.FragmentAdapter;
import com.example.user.login.LoginFragment;
import com.example.user.register.RegisterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import com.example.framework.BaseActivity;


public class UserActivity extends BaseActivity {


    private androidx.viewpager.widget.ViewPager vp;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> list = new ArrayList<>();

    @Override
    public int bandLayout() {
        return R.layout.activity_main5;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        vp = findViewById(R.id.vp);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String aa) {
        int a = Integer.parseInt(aa);
        if (a == 0) {
            //注册
            vp.setCurrentItem(0);
        } else if (a== 1){
            //登陆
            vp.setCurrentItem(1);
        }else {
            //返回App 主页面
            Bundle bundle=new Bundle();
            bundle.putString("page","0");
            CaCheArote.getInstance().getAppInterface().openMainActivity(this,bundle);
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //Fragment嵌套
        list.add(new RegisterFragment());
        list.add(new LoginFragment());
        fragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(fragmentPagerAdapter);

        //获取传过来的参数  查看是注册还是登陆
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String falg = extras.getString("falg");
            if (falg.equals("1")) {
                Toast.makeText(this, getString(R.string.toRegister), Toast.LENGTH_SHORT).show();
            } else {
                vp.setCurrentItem(1);
                Toast.makeText(this, getString(R.string.toLogin), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        destroy();
    }

}