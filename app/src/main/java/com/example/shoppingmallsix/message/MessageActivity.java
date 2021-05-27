package com.example.shoppingmallsix.message;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.greendao.CacheMessage;
import com.example.framework.manager.MSGManager;
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
    protected void initListener() {
        super.initListener();
        adapter.setiRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener(){

            @Override
            public void onItemClick(int position) {
                CacheMessage messageBean = messageBeans.get(position);
                if(messageBean.getIsNew()){
                    messageBean.setIsNew(false);
                    MSGManager.getInstance().updateMessage(messageBean, new MSGManager.IMessageListener() {
                        @Override
                        public void onResult(boolean isSuccess, List<CacheMessage> messageBeanList) {
                            if(isSuccess){
                                Toast.makeText(MessageActivity.this, "确认消息", Toast.LENGTH_SHORT).show();
                                adapter.updateData(messageBeans);
                            }
                        }
                    });
                }else {
                    Toast.makeText(MessageActivity.this, "已确认消息", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItwmLongClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
                builder.setMessage("删除该消息");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MSGManager.getInstance().deleteMessage(messageBeans.get(position), new MSGManager.IMessageListener() {
                            @Override
                            public void onResult(boolean isSuccess, List<CacheMessage> messageBeanList) {
                                adapter.updateData(messageBeans);
                            }
                        });
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void initData() {
        messageBeans = MSGManager.getInstance().getMessageBeanList();
        adapter.updateData(messageBeans);
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

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        ARouter.getInstance().build("/main/MainActivity").navigation();
    }
}
