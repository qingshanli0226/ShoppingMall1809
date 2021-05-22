package com.example.shoppingmall1809.main.home;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.home.adapter.HomeAdapter;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {

    private RecyclerView fragHomeRv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new HomePresenter(this);
    }

    @Override
    protected void initView() {
        fragHomeRv = (RecyclerView) findViewById(R.id.frag_home_rv);
        fragHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onHomeData(HoemBean hoemBean) {
        HoemBean.ResultBean result = hoemBean.getResult();

        ArrayList<Object> objects = new ArrayList<>();

        objects.add(result.getBanner_info());
        objects.add(result.getChannel_info());
        objects.add(result.getAct_info());
        objects.add(result.getSeckill_info());
        objects.add(result.getRecommend_info());
        objects.add(result.getHot_info());
        HomeAdapter homeAdapter = new HomeAdapter(objects);
        fragHomeRv.setAdapter(homeAdapter);
    }



    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSucessView();
    }

    @Override
    public void Error(String error) {
        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
    }
}