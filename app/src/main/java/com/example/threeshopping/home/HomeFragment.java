package com.example.threeshopping.home;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheHomeManager;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;
import com.example.threeshopping.home.adapter.HomeAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {


    private ImageView imgSearch;
    private EditText edSearch;
    private ImageView imgCom;
    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        edSearch = (EditText) findViewById(R.id.edSearch);
        imgCom = (ImageView) findViewById(R.id.imgCom);
        homeRv = (RecyclerView) findViewById(R.id.homeRv);
        homeAdapter = new HomeAdapter();
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        //添加数据
        homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Object> objects = new ArrayList<>();

        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getBanner_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getChannel_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getAct_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getSeckill_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getRecommend_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getHot_info());

        homeAdapter.getData().addAll(objects);
        homeRv.setAdapter(homeAdapter);

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