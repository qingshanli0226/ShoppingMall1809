package com.example.myapplication.home.msg;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.framework.BaseActivity;
import com.example.framework.db.DbTable;
import com.example.framework.manager.MsgManager;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MsgMainActivity extends BaseActivity implements MsgManager.IMessageListenner{

    private androidx.recyclerview.widget.RecyclerView rv;
    private List<DbTable> list=new ArrayList<>();
    private MsgAdapter adapter;

    @Override
    protected int bandLayout() {
        return R.layout.activity_msg_main;
    }

    @Override
    public void initView() {
        rv = findViewById(R.id.rv);
        adapter=new MsgAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        List<DbTable> messageBean = MsgManager.getInstance().getMessageBean();
        list.clear();
        list.addAll(messageBean);
        adapter.updataData(list);
        adapter.notifyDataSetChanged();
    }

    //刷新数据
    @Override
    public void onShowMsg(int count) {
        List<DbTable> messageBean = MsgManager.getInstance().getMessageBean();
        list.clear();
        list.addAll(messageBean);
        adapter.notifyDataSetChanged();
    }
}