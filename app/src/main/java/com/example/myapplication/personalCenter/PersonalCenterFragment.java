package com.example.myapplication.personalCenter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.framework.view.MyToorbar;
import com.example.myapplication.R;
import com.example.myapplication.findforpay.FindPayMainActivity;
import com.example.myapplication.findforsend.FindsendMainActivity;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

public class PersonalCenterFragment extends BaseFragment<PersonalPresenter> implements IPersonalView {

    private MyToorbar toorBar;
    private ImageView userImage;
    private LinearLayout awaitPay;
    private LinearLayout awaitDeliverGoods;
    private ImageView forpay;
    private ImageView forsend;

    @Override
    public int bandLayout() {
        return R.layout.fragment_personalcenter;
    }

    @Override
    public void initView() {
        toorBar = (MyToorbar) findViewById(R.id.toorBar);
        userImage = (ImageView) findViewById(R.id.userImage);
        awaitPay = (LinearLayout) findViewById(R.id.awaitPay);
        awaitDeliverGoods = (LinearLayout) findViewById(R.id.awaitDeliverGoods);
        forpay = findViewById(R.id.forpay);
        forsend = findViewById(R.id.forsend);
        forpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindPayMainActivity.class);
                startActivity(intent);
            }
        });
        forsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), FindsendMainActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter = new PersonalPresenter(this);
    }

    @Override
    public void initData() {
        toorBar.setToorbarListener(new MyToorbar.IToorbarListener() {
            @Override
            public void onleftClick() {

            }

            @Override
            public void onrightClick() {

            }

            @Override
            public void ontextClick() {

            }
        });
        //头像点击
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //获取待支付订单
        awaitPay.setOnClickListener(v -> mPresenter.getFindForPay());
        awaitDeliverGoods.setOnClickListener(v-> mPresenter.getFindSend());
    }

    @Override
    public void onShoppingPay(FindForPayBean findForPayBean) {
        if (findForPayBean.getCode().equals("200")) {
            Toast.makeText(getActivity(), "获取待支付订单成功", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), findForPayBean.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onShoppingSend(FindForSendBean findForSendBean) {
               if (findForSendBean.getCode().equals("200")){
                   Toast.makeText(getContext(), "获取待发货订单成功", Toast.LENGTH_SHORT).show();
               }
    }

}
