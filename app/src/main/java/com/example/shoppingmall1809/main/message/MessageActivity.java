package com.example.shoppingmall1809.main.message;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.db.MessageTable;
import com.example.framework.manager.MessageManager;
import com.example.framework.view.BaseRVAdapter;
import com.example.framework.view.ToolBar;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.adapter.MessageAdapter;

import java.util.List;

@Route(path = "/main/home/MessageActivity")
public class MessageActivity extends BaseActivity {
    private ToolBar toolbar;
    private RecyclerView messageRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {
        MessageManager.getInstance().getMessage(message -> {
            LogUtils.json(message);
            MessageAdapter messageAdapter = new MessageAdapter();
            messageAdapter.updateDate(message);
            messageRv.setAdapter(messageAdapter);

            messageAdapter.setRecyclerItemClickListener(new BaseRVAdapter.IRecyclerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(MessageActivity.this, getResources().getString(R.string.confirmedMessage), Toast.LENGTH_SHORT).show();

                    MessageTable messageTable = message.get(position);
                    messageTable.setIsRead(false);

                    MessageManager.getInstance().subCount();
                    MessageManager.getInstance().upDataMessage(messageTable);

                    messageAdapter.notifyItemChanged(position);
                }

                @Override
                public void onItemLongClick(int position) {

                }
            });
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        messageRv = (RecyclerView) findViewById(R.id.message_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        messageRv.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}