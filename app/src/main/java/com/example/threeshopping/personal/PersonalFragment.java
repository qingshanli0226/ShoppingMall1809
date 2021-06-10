package com.example.threeshopping.personal;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.common.Constants;
import com.example.common.SpUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.bean.EventBean;
import com.example.threeshopping.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class PersonalFragment extends BaseFragment {


    private ToolBar toolbar;
    private ImageView message;
    private ImageView payment;
    private ImageView shipments;
    private LinearLayout personAddr;
    private ImageView setting;
    private ImageView head;
    private LinearLayout call;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) rootView.findViewById(R.id.toolbar);
        message = (ImageView) rootView.findViewById(R.id.message);//消息
        payment = (ImageView) rootView.findViewById(R.id.payment);//待付款
        shipments = (ImageView) rootView.findViewById(R.id.shipments);//待发货
        personAddr = (LinearLayout) findViewById(R.id.personAddr);
        setting = (ImageView) findViewById(R.id.setting);
        head = (ImageView) findViewById(R.id.head);
        call = (LinearLayout) findViewById(R.id.call);
    }

    @Override
    protected void initPrensenter() {

    }

    @Subscribe
    public void getEventBus(String path) {
        Glide.with(this).load(path).transform(new CircleCrop()).into(head);
    }

    @Override
    protected void initData() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        String getpath = SpUtil.getpath(getActivity());
        Glide.with(this).load(getpath).transform(new CircleCrop()).into(head);


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_MESSAGE).navigation();
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_AWAITPAYMENT).navigation();
            }
        });
        shipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_SHIPMENTS).navigation();
            }
        });
        //收获地址管理
        personAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_ADDRMANAGER).navigation();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                CommonArouter.getInstance().build(Constants.PATH_SETTING).navigation();
            }
        });
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}