package com.example.electricityproject.home.message;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.BaseAdapter;
import com.example.common.db.DaoMaster;
import com.example.common.db.MessageDataBase;
import com.example.common.db.MessageTable;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.view.ToolBar;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class MessageActivity extends BaseActivity {

    private DaoMaster daoMaster = MessageDataBase.getInstance().getDaoMaster();
    private List<MessageTable> messageTables;
    private ToolBar toolbar;
    private RecyclerView messageRv;
    private MessageAdapter messageAdapter;

    @Override
    protected void initData() {
        messageTables = MessageDataBase.getInstance().getDaoSession().loadAll(MessageTable.class);

        messageRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageAdapter.updateData(messageTables);
        messageRv.setAdapter(messageAdapter);
        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
            @Override
            public void onLeftClick() {
                //返回
                finish();
            }

            @Override
            public void onRightImgClick() {

            }

            @Override
            public void onRightTvClick() {

            }
        });

        //adapter 点击
        messageAdapter.setRecyclerItemClickListener(new BaseAdapter.iRecyclerItemClickListener() {
            //点击
            @Override
            public void OnItemClick(int position) {
                //更改数据库
                MessageDataBase.getInstance().getDaoSession().update(new MessageTable(Long.decode(messageTables.get(position).getId()+""),messageTables.get(position).getIsSucceed(),messageTables.get(position).getMessageTime(),true));
                messageTables.get(position).setIsShow(true);
                messageAdapter.notifyItemChanged(position);
                Toast.makeText(MessageActivity.this, "已确认消息", Toast.LENGTH_SHORT).show();
            }
            //长按
            @Override
            public void OnItemLongClick(int position) {
                //长按 弹出AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
                builder.setTitle("是否删除?");
                //点击是
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //把当前点击的数据，从数据库中删除
                        MessageDataBase.getInstance().getDaoSession().delete(new MessageTable(Long.decode(messageTables.get(position).getId()+""),messageTables.get(position).getIsSucceed(),messageTables.get(position).getMessageTime(),messageTables.get(position).getIsShow()));
                        messageTables.remove(position);

                        messageAdapter.updateData(MessageDataBase.getInstance().getDaoMaster().newSession().loadAll(MessageTable.class));
                        messageAdapter.notifyDataSetChanged();
                        //删除后发送EventBus
                        EventBus.getDefault().post("num");

                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        messageRv = (RecyclerView) findViewById(R.id.message_rv);

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