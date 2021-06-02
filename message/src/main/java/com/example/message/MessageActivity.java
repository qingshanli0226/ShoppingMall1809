package com.example.message;


import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.CacheMessageManager;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.view.ToolBar;
import com.example.message.adapter.MessageAdapter;
import com.fiannce.sql.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MessageActivity extends BaseActivity implements CacheMessageManager.IMessageListener {


    private RecyclerView messageRv;
    private MessageAdapter messageAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {

        messageRv = (RecyclerView) findViewById(R.id.messageRv);
        messageAdapter = new MessageAdapter();
        CacheMessageManager.getInstance().register(this);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        messageRv.setLayoutManager(linearLayoutManager);
        messageAdapter.updata(CacheMessageManager.getInstance().getMessageBeans());
        messageRv.setAdapter(messageAdapter);


        messageAdapter.setRvItemOnClickListener(new BaseRvAdapter.IRvItemOnClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                messageAdapter.getData().get(position).setIsRead(false);
                CacheMessageManager.getInstance().setRead(position,messageAdapter.getData().get(position));
            }

            @Override
            public boolean onLongItemClick(int position, View view) {
                return false;
            }
        });

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        super.onClickLeft();
        Bundle bundle = new Bundle();
        bundle.putInt("page",0);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onAddRefresh(int position) {
        messageAdapter.notifyItemChanged(position);
    }

    @Override
    public void onAllRefresh(List<MessageBean> messageBeans) {
        messageAdapter.updata(messageBeans);
    }

    @Override
    public void destroy() {
        super.destroy();
        CacheMessageManager.getInstance().unRegister(this);

    }
}
