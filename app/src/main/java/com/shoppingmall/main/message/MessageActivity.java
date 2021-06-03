package com.shoppingmall.main.message;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shoppingmall.R;
import com.shoppingmall.detail.messagedao.MessageBean;
import com.shoppingmall.detail.messagedao.MessageManager;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.main.MainActivity;
import com.shoppingmall.net.bean.LoginBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity {

    private Toolbar bar;
    private ImageView back;
    private RecyclerView rv;
    private MessageAdapter messageAdapter;
    private List<MessageBean> list;
    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }
    @Override
    public void initData() {
        list = MessageManager.getInstance().getMessageBeanList();

    }
    @Override
    public void initView() {
        bar = findViewById(R.id.bar);
        back = findViewById(R.id.back);
        rv = findViewById(R.id.rv);
         messageAdapter = new MessageAdapter();
         rv.setAdapter(messageAdapter);
         rv.setLayoutManager(new LinearLayoutManager(this));
         messageAdapter.updateData(MessageManager.getInstance().getMessageBeanList());
         messageAdapter.setRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener() {
             @Override
             public void onItemClick(int position) {
                 MessageBean messageBean = list.get(position);
                 if (messageBean.getIsNew()){
                     messageBean.setIsNew(false);
                     MessageManager.getInstance().updateMessage(messageBean, new MessageManager.IMessageListener() {
                         @Override
                         public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                             if (isSuccess){
                                 Toast.makeText(MessageActivity.this, "确认消息", Toast.LENGTH_SHORT).show();
                                 messageAdapter.updateData(list);
                                 SharedPreferences msg = getSharedPreferences("msg", MODE_PRIVATE);
                                 int msgCount = msg.getInt("msgCount", 1);
                                 MessageManager.getInstance().updateMessageCount(msgCount-1);
                                 EventBus.getDefault().post("payback");
                             }
                         }
                     });
                 }else{
                     Toast.makeText(MessageActivity.this, "已确认消息", Toast.LENGTH_SHORT).show();
                 }
             }
             @Override
             public void onItemLongClick(int position) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
                 builder.setMessage("删除该消息");
                 builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         MessageManager.getInstance().deleteMessage(list.get(position), new MessageManager.IMessageListener() {
                             @Override
                             public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                                 messageAdapter.updateData(list);
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageActivity.this.finish();
                Intent intent = new Intent(MessageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initPresenter() {

    }


}