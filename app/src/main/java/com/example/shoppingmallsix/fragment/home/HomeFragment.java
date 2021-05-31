package com.example.shoppingmallsix.fragment.home;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.manager.LogUtil;
import com.example.framework.manager.MessageManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.HomeBean;
import com.example.shoppingmallsix.BuildConfig;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.adapter.HomeAdapter;
import com.example.shoppingmallsix.message.MessageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView,MessageManager.IMessage {
    private ToolBar toolbar;
    private EditText edtname;
    private RecyclerView rv;
    private HomeAdapter homeAdapter;
    private HomePresenter homePresenter;
    private List<Object> list = new ArrayList<>();
    private LinearLayout homeMessage;
    private TextView messageTv;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {
        homePresenter = new HomePresenter(this);
    }

    @Override
    protected void initData() {

        MessageManager.getInstance().register(this::onMessage);

        homePresenter.getHomeDData();

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) mBaseView.findViewById(R.id.toolbar);
        edtname = (EditText) mBaseView.findViewById(R.id.edtname);
        rv = (RecyclerView) mBaseView.findViewById(R.id.rv);
        homeMessage = mBaseView.findViewById(R.id.homeMessage);
        messageTv = mBaseView.findViewById(R.id.messageTv);

        homeAdapter = new HomeAdapter(list, getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(homeAdapter);

        homeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });
}

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void getHomeData(HomeBean homeBean) {
        HomeBean.ResultBean result = homeBean.getResult();

        int count = MessageManager.getInstance().getCount();
        setCount(count);

        if (BuildConfig.DEBUG) Log.d("HomeFragment", "homeBean:" + homeBean);
        list.add(result.getBanner_info());
        list.add(result.getChannel_info());
        list.add(result.getAct_info());
        list.add(result.getSeckill_info().getList());
        list.add(result.getRecommend_info());
        list.add(result.getHot_info());

        loadingPage.showSuccessView();

        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showToast(String msg) {
        loadingPage.showError(msg);
    }

    @Override
    public void onMessage(int count) {
        setCount(count);
    }

    public void setCount(int count){
        if (count<=0){
            messageTv.setText("消息");
        }else {
            messageTv.setText(count+"");
        }
    }
}
