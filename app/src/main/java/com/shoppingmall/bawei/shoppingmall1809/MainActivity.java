package com.shoppingmall.bawei.shoppingmall1809;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.fiannce.bawei.common.LogUtil;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.mode.ShopmallHomeBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.shoppingmall.bawei.shoppingmall1809.exception.ExceptionActivity;
import com.shoppingmall.bawei.shoppingmall1809.glide.GlideActivity;
import com.shoppingmall.bawei.shoppingmall1809.home.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {
    private  HomeAdapter homeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }



    @Override
    protected void initView() {
        LogUtil.d("initView");
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        homeAdapter = new HomeAdapter();

        rv.setAdapter(homeAdapter);

        OkGo.<String>get("http://49.233.0.68:8080/atguigu/json/HOME_URL.json").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ShopmallHomeBean shopmallHomeBean = new Gson().fromJson(response.body(),ShopmallHomeBean.class);
                List<Object> objects = new ArrayList<>();
                objects.add(shopmallHomeBean.getResult().getBanner_info());
                objects.add(shopmallHomeBean.getResult().getChannel_info());
                objects.add(shopmallHomeBean.getResult().getAct_info());
                homeAdapter.updateData(objects);
            }
        });

        findViewById(R.id.btnGlide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GlideActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ExceptionActivity.class);
                startActivity(intent);
            }
        });



    }
}
