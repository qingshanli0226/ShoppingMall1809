package com.example.shoppingmallsix.message;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.greendao.CacheMessage;
import com.example.framework.manager.MessageManager;
import com.example.shoppingmallsix.R;

import java.util.List;

public class MessageActivity extends BaseActivity {

    private RecyclerView messageRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {
        List<CacheMessage> message = MessageManager.getInstance().getMessage();

        MessageAdapter messageAdapter = new MessageAdapter();
        messageAdapter.updateData(message);

        messageRv.setAdapter(messageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        messageRv.setLayoutManager(linearLayoutManager);

        messageAdapter.setiRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MessageActivity.this, "已确认消息", Toast.LENGTH_SHORT).show();

                CacheMessage messageTable = message.get(position);
                messageTable.setIsRead(false);

                MessageManager.getInstance().subCount();
                MessageManager.getInstance().upDataMessage(messageTable);

                messageAdapter.notifyItemChanged(position);
            }

            @Override
            public void onItwmLongClick(int position) {

            }

        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        messageRv = (RecyclerView) findViewById(R.id.rv_message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
