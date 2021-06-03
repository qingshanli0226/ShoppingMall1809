package com.shoppingmall.main.home;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shoppingmall.R;
import com.shoppingmall.detail.messagedao.MessageManager;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.MainActivity;
import com.shoppingmall.main.home.adapter.HomeAdapter;
import com.shoppingmall.main.message.MessageActivity;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.LoginBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {


    private EditText homeFragmentSearch;
    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;
    private ImageView message1;
    private TextView textmessage;
    private LoginBean loginBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        homeFragmentSearch = (EditText) mView.findViewById(R.id.homeFragmentSearch);
        homeRv = (RecyclerView) mView.findViewById(R.id.homeRv);
        message1 = mView.findViewById(R.id.message1);
        textmessage = mView.findViewById(R.id.textmessage);
         loginBean = ShopMallUserManager.getInstance().getLoginBean();
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (loginBean!=null){
            if (messageCount>0){
                textmessage.setText(messageCount+"");
            }else {
                textmessage.setText("消息");
            }
        }

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
        if (homeBean != null) {
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

        message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginBean==null){
                    Toast.makeText(getContext(), "用户未登录", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), MessageActivity.class);
                    startActivity(intent);
                }

            }
        });


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ispay(String m){
        if (m.equals("payback")){
            textmessage.setText(MessageManager.getInstance().getMessageCount()+"");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}