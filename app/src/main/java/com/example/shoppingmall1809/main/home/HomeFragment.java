package com.example.shoppingmall1809.main.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commom.ShopConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.MessageManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.home.adapter.HomeAdapter;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, MessageManager.IMessage {
    private LinearLayout homeMessage;
    private RecyclerView fragHomeRv;
    private ToolBar toolbar;
    private TextView homeMessageNum;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        MessageManager.getInstance().register(this::onMessage);

        httpPresenter.getHomeData();

        homeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ShopConstants.MAIN_HOME_MESSAGE).navigation();
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new HomePresenter(this);
    }

    @Override
    protected void initView() {
        fragHomeRv = (RecyclerView) findViewById(R.id.frag_home_rv);
        fragHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeMessage = (LinearLayout) findViewById(R.id.home_message);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        homeMessageNum = (TextView) findViewById(R.id.home_message_num);
    }

    @Override
    public void onHomeData(HoemBean hoemBean) {
        HoemBean.ResultBean result = hoemBean.getResult();

        MessageManager.getInstance().getCount(count -> {
            setCount(count);
        });

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

    @Override
    public void onMessage(int count) {
       setCount(count);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MessageManager.getInstance().unregister(this::onMessage);
    }

    public void setCount(int count){
        if (count<=0){
            homeMessageNum.setText(getResources().getString(R.string.message));
        }else {
            homeMessageNum.setText(count+"");
        }
    }
}