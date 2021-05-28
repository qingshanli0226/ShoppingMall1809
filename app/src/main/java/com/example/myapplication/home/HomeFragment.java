package com.example.myapplication.home;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.log.LogUtil;
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
        homeRec = (RecyclerView) findViewById(R.id.homeRec);
        //获取缓存的首页值
        HomeBean homeBean = CaCheMannager.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        LogUtil.d(result.toString());


        objectList.add(result.getBanner_info());
        objectList.add(result.getChannel_info());
        objectList.add(result.getAct_info());
        objectList.add(result.getSeckill_info().getList());
        objectList.add(result.getRecommend_info());
        objectList.add(result.getHot_info());
        //适配器
        homeAdapter = new HomeAdapter();
        homeAdapter.updataData(objectList);
        homeRec.setAdapter(homeAdapter);
        homeRec.setLayoutManager(new LinearLayoutManager(getActivity()));

        msg = (ImageView) findViewById(R.id.msg);
        msg.setOnClickListener(v -> {
            CaCheArote.getInstance().getMsgInterface().openMsgActivity(getActivity(),null);
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}