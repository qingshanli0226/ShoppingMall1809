package com.example.electricityproject.home;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.HomeBean;
import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenter> implements CallHomeData{
    private RecyclerView mainRe;

    @Override
    protected void initData() {
        httpPresenter.getHomeBannerData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new HomePresenter(this);
    }

    @Override
    protected void initView() {
        mainRe = mView.findViewById(R.id.main_re);
        mainRe.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }

    @Override
    public void onHomeBanner(HomeBean homeBean) {
        Log.i("zx", "onHomeBanner: "+homeBean.toString());
        HomeAdapter homeAdapter = new HomeAdapter();

        List<Object> objectList=new ArrayList<>();
        objectList.add(homeBean.getResult().getBanner_info());
        objectList.add(homeBean.getResult().getChannel_info());
        objectList.add(homeBean.getResult().getAct_info());
        objectList.add(homeBean.getResult().getSeckill_info());
        objectList.add(homeBean.getResult().getRecommend_info());
        objectList.add(homeBean.getResult().getHot_info());
        homeAdapter.updateData(objectList);
        mainRe.setAdapter(homeAdapter);

    }
}