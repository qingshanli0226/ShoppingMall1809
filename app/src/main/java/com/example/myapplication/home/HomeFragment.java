package com.example.myapplication.home;

import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.myapplication.R;
import com.example.myapplication.home.homeadapter.HomeAdapter;
import com.example.net.bean.HomeBean;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import mvp.CaCheMannager;

public class HomeFragment extends BaseFragment {
    private EditText etDevOpsPassWord;
    //    private Banner homeBanner;
    private RecyclerView homeRec;
    private HomeAdapter homeAdapter;
    private List<Object> objectList = new ArrayList<>();

    @Override
    public int bandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        etDevOpsPassWord = (EditText) findViewById(R.id.et_devOps_passWord);
//        homeBanner = (Banner) findViewById(R.id.homeBanner);
        homeRec = (RecyclerView) findViewById(R.id.homeRec);


        HomeBean homeBean = CaCheMannager.getInstance().getHomeBean();

//        Toast.makeText(getActivity(), homeBean.toString(), Toast.LENGTH_SHORT).show();
        HomeBean.ResultBean result = homeBean.getResult();

        objectList.add(result.getBanner_info());
        objectList.add(result.getChannel_info());
        objectList.add(result.getAct_info());
        objectList.add(result.getSeckill_info().getList());
        objectList.add(result.getRecommend_info());
        objectList.add(result.getHot_info());


        homeAdapter = new HomeAdapter();
        homeAdapter.updataData(objectList);
        homeRec.setAdapter(homeAdapter);

        homeRec.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}