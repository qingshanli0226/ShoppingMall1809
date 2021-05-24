package com.example.shoppingmallsix.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.framework.greendao.CacheMessage;
import com.example.shoppingmallsix.R;

import java.util.List;

public class MessageActivity extends BaseActivity {

    private RecyclerView rvMessage;
    private MessageAdapter adapter;
    private List<CacheMessage> messageBeans;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
//        messageBeans = MSGManager.getInstance().getMessageBeanList();
//        adapter.updataData(messageBeans);
    }

    @Override
    protected void initView() {
        rvMessage = (RecyclerView) findViewById(R.id.rv_message);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MessageAdapter();
        rvMessage.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }
}
