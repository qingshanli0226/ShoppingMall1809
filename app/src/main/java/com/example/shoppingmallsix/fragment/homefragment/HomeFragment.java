package com.example.shoppingmallsix.fragment.homefragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.bean.HomeBean;
import com.example.shoppingmallsix.BuildConfig;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {
    private ToolBar toolbar;
    private EditText edtname;
    private RecyclerView rv;
    private  HomeAdapter homeAdapter;
    private HomePresenter homePresenter;
    private List<Object> list = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {
        homePresenter = new HomePresenter(this);
    }

    @Override
    protected void initData() {
        homePresenter.getHomeDData();
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) mBaseView.findViewById(R.id.toolbar);
        edtname = (EditText) mBaseView.findViewById(R.id.edtname);
        rv = (RecyclerView) mBaseView.findViewById(R.id.rv);
        homeAdapter= new HomeAdapter(list);


        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(homeAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void getHomeData(HomeBean homeBean) {
        HomeBean.ResultBean result = homeBean.getResult();

        Toast.makeText(getContext(), "homeBean:" + homeBean, Toast.LENGTH_SHORT).show();
        if (BuildConfig.DEBUG) Log.d("HomeFragment", "homeBean:" + homeBean);
        list.add(result.getBanner_info());
        list.add(result.getChannel_info());
        list.add( result.getAct_info());


        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
