package com.example.myapplication.home;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.log.LogUtil;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.MsgManager;
import com.example.myapplication.R;
import com.example.myapplication.home.homeadapter.HomeAdapter;
import com.example.net.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements MsgManager.IMessageListenner{
    private EditText etDevOpsPassWord;
    private RecyclerView homeRec;
    private HomeAdapter homeAdapter;
    private List<Object> objectList = new ArrayList<>();
    private ImageView msg;
    private TextView msgCount;

    @Override
    public int bandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        MsgManager.getInstance().registerIMsg(this);//注册
        etDevOpsPassWord = (EditText) findViewById(R.id.et_devOps_passWord);
        homeRec = (RecyclerView) findViewById(R.id.homeRec);
        msgCount = (TextView) findViewById(R.id.msgCount);
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
            CaCheArote.getInstance().getMsgInterface().openMsgActivity(getActivity(), null);
        });

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        msgCount.setText(MsgManager.getInstance().getMsgCount()+"");
    }

    @Override
    public void onShowMsg(int count) {
        msgCount.setText(count+"");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MsgManager.getInstance().unregisterIMsg(this);//注册
        destroy();
    }
}