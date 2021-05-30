package com.example.electricityproject.home.message;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.electricityproject.R;
import com.example.common.db.DaoMaster;
import com.example.electricityproject.R;
import com.example.electricityproject.db.MessageManger;
import com.example.framework.BaseActivity;

import java.util.List;

public class MessageActivity extends BaseActivity {

    private MessageAdapter adapter;

    private DaoMaster daoMaster;

    @Override
    protected void initData() {

//        daoMaster = DBManger.getInstance().getDaoMaster(MessageActivity.this);
//        if (daoMaster!=null){
//            List<MessageTable> messageTables = daoMaster.newSession().loadAll(MessageTable.class);
//            LogUtils.i(messageTables.toString());
//
//            adapter = new MessageAdapter();
//            adapter.updateData(messageTables);
//            messageRv.setAdapter(adapter);
//        }
//        daoMaster = MessageManger.getInstance().getDaoMaster();


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_messafe;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}