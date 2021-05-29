package com.example.electricityproject.home;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.HomeBean;
import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.electricityproject.home.message.MessageActivity;
import com.example.framework.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenter> implements CallHomeData {
    private RecyclerView mainRe;
    private HomeAdapter homeAdapter;
    private List<Object> objectList;
    private ImageView imgSearch;
    private EditText editMessage;
    private ImageView btnMessage;

    @Override
    protected void initData() {
        httpPresenter.getHomeBannerData();

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new HomePresenter(this);
    }

    @Override
    protected void initView() {
        homeAdapter = new HomeAdapter();

        mainRe = mView.findViewById(R.id.main_re);
        mainRe.setLayoutManager(new LinearLayoutManager(getContext()));

        imgSearch = (ImageView) findViewById(R.id.img_search);
        editMessage = (EditText) findViewById(R.id.edit_message);
        btnMessage = (ImageView) findViewById(R.id.btn_message);
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
        loadingPage.showLoadingView();
    }


    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }


    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }


    @Override
    public void onHomeBanner(HomeBean homeBean) {

        objectList = new ArrayList<>();
        objectList.add(homeBean.getResult().getBanner_info());
        objectList.add(homeBean.getResult().getChannel_info());
        objectList.add(homeBean.getResult().getAct_info());
        objectList.add(homeBean.getResult().getSeckill_info());
        objectList.add(homeBean.getResult().getRecommend_info());
        objectList.add(homeBean.getResult().getHot_info());
        homeAdapter.updateData(objectList);
        mainRe.setAdapter(homeAdapter);

    }

    //网络从断开变为连接,重新加载数据
    @Override
    public void OnConnect() {
        Toast.makeText(getContext(), "网络重新连接,重新获取数据", Toast.LENGTH_SHORT).show();
        httpPresenter.getHomeBannerData();
        homeAdapter.notifyDataSetChanged();

    }

    @Override
    public void DisConnect() {
        Toast.makeText(getContext(), "已断开网络", Toast.LENGTH_SHORT).show();
    }
}