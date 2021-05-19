package com.example.electricityproject.home;

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
        httpPresenter=new HomePresenter(this);
        httpPresenter.getHomeBannerData();
    }

    @Override
    protected void initPresenter() {

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
        HomeAdapter homeAdapter = new HomeAdapter();
        List<HomeBean.ResultBean.BannerInfoBean> banner_info = homeBean.getResult().getBanner_info();
        List<HomeBean.ResultBean.ChannelInfoBean> channel_info = homeBean.getResult().getChannel_info();
        List<Object> objectList=new ArrayList<>();
        objectList.add(banner_info);
        objectList.add(channel_info);
        homeAdapter.updateData(objectList);
        mainRe.setAdapter(homeAdapter);

    }
}