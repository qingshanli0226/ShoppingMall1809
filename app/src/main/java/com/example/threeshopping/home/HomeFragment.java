package com.example.threeshopping.home;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheHomeManager;
import com.example.framework.manager.CacheMessageManager;
import com.example.threeshopping.R;
import com.example.threeshopping.home.adapter.HomeAdapter;
import com.fiannce.sql.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements CacheMessageManager.IMessageListener{


    private ImageView imgSearch;
    private EditText edSearch;
    private ImageView imgCom;
    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;
    private TextView homeNum;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        edSearch = (EditText) findViewById(R.id.edSearch);
        imgCom = (ImageView) findViewById(R.id.imgCom);
        homeRv = (RecyclerView) findViewById(R.id.homeRv);
        homeAdapter = new HomeAdapter();
        homeNum = (TextView) findViewById(R.id.homeNum);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        //添加数据
        homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Object> objects = new ArrayList<>();

        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getBanner_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getChannel_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getAct_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getSeckill_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getRecommend_info());
        objects.add(CacheHomeManager.getInstance().getHomeBean().getResult().getHot_info());

        homeAdapter.getData().addAll(objects);
        homeRv.setAdapter(homeAdapter);


        imgCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_MESSAGE).navigation();
            }
        });

        //更改
        CacheMessageManager.getInstance().register(this);
        CacheMessageManager.getInstance().searchMessage();
        setHomeNum("");

    }
    @Subscribe
    public void setHomeNum(String num){
        List<MessageBean> messageBeans = CacheMessageManager.getInstance().getMessageBeans();
        LogUtil.d("zyba"+messageBeans);
        int count = 0;
        for (MessageBean messageBean : messageBeans) {
            if(messageBean.getIsRead()){
                count++;
            }
        }
        if(count >0){
            homeNum.setText(count+"");
            homeNum.setVisibility(View.VISIBLE);
        } else{
            homeNum.setText(count+"");
            homeNum.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void destroy() {
        super.destroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        CacheMessageManager.getInstance().unRegister(this);

    }

    @Override
    public void onAddRefresh(int position) {

    }

    @Override
    public void onAllRefresh(List<MessageBean> messageBeans) {
        setHomeNum("");
    }
}