package com.example.user;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;


import android.widget.Toast;

import com.example.user.R;

import com.example.user.frag.FragmentAdapter;
import com.example.user.frag.LoginFragment;
import com.example.user.frag.RegisterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import com.example.framework.BaseActivity;

public class UserActivity extends BaseActivity {


    private androidx.viewpager.widget.ViewPager vp;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> list=new ArrayList<>();

    @Override
    public int bandLayout() {
        return R.layout.activity_main5;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        vp = findViewById(R.id.vp);
        list.add(new RegisterFragment());
        list.add(new LoginFragment());
        fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(fragmentPagerAdapter);


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String aa){
        Toast.makeText(this, "123"+aa, Toast.LENGTH_SHORT).show();
        int a = Integer.parseInt(aa);
        if (a==1){
            vp.setCurrentItem(0);
        }else {
            vp.setCurrentItem(1);
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     EventBus.getDefault().unregister(this);
    }

}