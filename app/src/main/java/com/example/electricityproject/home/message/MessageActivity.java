package com.example.electricityproject.home.message;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.electricityproject.R;
import com.example.electricityproject.db.DBManger;
import com.example.electricityproject.db.DaoMaster;
import com.example.electricityproject.db.MessageTable;
import com.example.framework.BaseActivity;
import com.example.view.ToolBar;

import java.util.List;

public class MessageActivity extends BaseActivity {

    private MessageAdapter adapter;

    private com.example.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView messageRv;
    private DaoMaster daoMaster;

    @Override
    protected void initData() {

        daoMaster = DBManger.getInstance().getDaoMaster(MessageActivity.this);
        if (daoMaster!=null){
            List<MessageTable> messageTables = daoMaster.newSession().loadAll(MessageTable.class);
            LogUtils.i(messageTables.toString());

            adapter = new MessageAdapter();
            adapter.updateData(messageTables);
            messageRv.setAdapter(adapter);
        }


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        messageRv = (RecyclerView) findViewById(R.id.message_rv);
        messageRv.setLayoutManager(new LinearLayoutManager(this));
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