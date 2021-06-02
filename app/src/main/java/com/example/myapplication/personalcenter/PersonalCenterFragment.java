package com.example.myapplication.personalcenter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.framework.manager.PaySendCacheManager;
import com.example.framework.view.MyToorbar;
import com.example.myapplication.R;
import com.example.myapplication.findforpay.FindPayMainActivity;
import com.example.myapplication.findforsend.FindsendMainActivity;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;


public class PersonalCenterFragment extends BaseFragment<PersonalPresenter> implements IPersonalView {

    private MyToorbar toorBar;
    private ImageView userImage;

    private ImageView forpay;
    private ImageView forsend;
    private TextView paynum;
    private TextView sendnum;
    private RelativeLayout awaitPay;
    private RelativeLayout awaitDeliverGoods;

    @Override
    public int bandLayout() {
        return R.layout.fragment_personalcenter;
    }

    @Override
    public void initView() {


        toorBar = (MyToorbar) findViewById(R.id.toorBar);
        userImage = (ImageView) findViewById(R.id.userImage);

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
        paynum = findViewById(R.id.paynum);
        sendnum = findViewById(R.id.sendnum);
        awaitPay = findViewById(R.id.awaitPay);
        awaitDeliverGoods = findViewById(R.id.awaitDeliverGoods);

    }

    @Override
    public void onLoginChange(boolean loginBean) {
        super.onLoginChange(loginBean);
        if (loginBean) {
            mPresenter.onFindPay();
        } else {

        }
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
        awaitDeliverGoods.setOnClickListener(v -> mPresenter.getFindSend());
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
        if (findForSendBean.getCode().equals("200")) {
            Toast.makeText(getContext(), "获取待发货订单成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ondend(FindForPayBean findForPayBean) {
        PaySendCacheManager.getInstance().setFindForPayBean(findForPayBean);

        paynum.setText(PaySendCacheManager.getInstance().getOneIndex() + "");
    }

}
