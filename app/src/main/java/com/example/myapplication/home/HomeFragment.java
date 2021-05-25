package com.example.myapplication.home;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.type.ToLoginType;
import com.example.common.type.TypeString;
import com.example.framework.BaseFragment;

import com.example.framework.manager.CaCheArote;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.home.homeadapter.HomeAdapter;
import com.example.myapplication.msg.MsgMainActivity;
import com.example.net.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import mvp.CaCheMannager;

public class HomeFragment extends BaseFragment {
    private EditText etDevOpsPassWord;
    //    private Banner homeBanner;
    private RecyclerView homeRec;
    private HomeAdapter homeAdapter;
    private List<Object> objectList = new ArrayList<>();
    private ImageView msg;

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

        msg = (ImageView) findViewById(R.id.msg);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("falg","2");
                CaCheArote.getInstance().getUserInterface().openLoginActivity(getContext(),bundle);
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}