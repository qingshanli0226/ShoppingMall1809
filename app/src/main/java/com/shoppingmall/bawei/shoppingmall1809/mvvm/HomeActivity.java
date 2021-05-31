package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import android.os.Bundle;

import com.fiannce.bawei.net.mode.HomeBean;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        HomeMode homeMode = new ViewModelProvider(this).get(HomeMode.class);
        homeMode.getLiveData().observe(this, new Observer<ViewModeBean<HomeBean>>() {
            @Override
            public void onChanged(ViewModeBean<HomeBean> homeBeanViewModeBean) {
                homeBeanViewModeBean.handle(new ViewModeBean.IHanleCallback<HomeBean>() {
                    @Override
                    public void onLoading() {

                    }

                    @Override
                    public void onSuccess(HomeBean data) {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });
    }
}
