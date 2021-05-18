package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import com.example.user.R;
import com.example.user.frag.FragmentAdapter;
import com.example.user.frag.LoginFragment;
import com.example.user.frag.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

import mvp.view.BaseActivity;

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

        vp = findViewById(R.id.vp);
        list.add(new RegisterFragment());
        list.add(new LoginFragment());
        fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(fragmentPagerAdapter);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initDataw() {

    }
}