package com.shoppingmall.main.home;

import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shoppingmall.R;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.home.adapter.HomeAdapter;
import com.shoppingmall.net.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {


    private EditText homeFragmentSearch;
    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        homeFragmentSearch = (EditText) mView.findViewById(R.id.homeFragmentSearch);
        homeRv = (RecyclerView) mView.findViewById(R.id.homeRv);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

        homeAdapter = new HomeAdapter();
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRv.setAdapter(homeAdapter);
        //得到数据
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        //添加数据
        List<Object> objects = new ArrayList<>();
        objects.add(homeBean.getResult().getBanner_info());
        objects.add(homeBean.getResult().getChannel_info());
        objects.add(homeBean.getResult().getAct_info());
        objects.add(homeBean.getResult().getSeckill_info());
        objects.add(homeBean.getResult().getRecommend_info());
        objects.add(homeBean.getResult().getHot_info());
        homeAdapter.updateData(objects);
    }

}