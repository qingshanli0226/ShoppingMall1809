package com.example.threeshopping.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheHomeManager;
import com.example.threeshopping.R;


public class HomeFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        Toast.makeText(getActivity(), "aaa"+ CacheHomeManager.getInstance().getHomeBean(), Toast.LENGTH_SHORT).show();
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
}