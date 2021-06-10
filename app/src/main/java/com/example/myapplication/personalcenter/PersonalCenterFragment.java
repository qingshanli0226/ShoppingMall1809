package com.example.myapplication.personalcenter;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.common.type.ToLoginType;
import com.example.common.type.TypeString;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.PaySendCacheManager;
import com.example.framework.view.MyToorbar;
import com.example.myapplication.R;
import com.example.myapplication.personalcenter.findforpay.FindPayMainActivity;
import com.example.myapplication.personalcenter.findforsend.FindsendMainActivity;
import com.example.myapplication.personalcenter.map.MapActivity;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.example.net.bean.LogOutBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderinfoBean;


public class PersonalCenterFragment extends BaseFragment<PersonalPresenter> implements IPersonalView {

    private MyToorbar toorBar;
    private ImageView userImage;

    private ImageView forpay;
    private ImageView forsend;
    private TextView paynum;
    private TextView sendnum;
    private RelativeLayout awaitPay;
    private RelativeLayout awaitDeliverGoods;
    private LinearLayout gaoDeMap;
    private PopupWindow popupWindow;

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

        paynum = findViewById(R.id.paynum);
        sendnum = findViewById(R.id.sendnum);
        awaitPay = findViewById(R.id.awaitPay);
        awaitDeliverGoods = findViewById(R.id.awaitDeliverGoods);

        gaoDeMap = (LinearLayout) findViewById(R.id.gaoDeMap);
        forpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CacheUserManager.getInstance().getIsLogin()) {//未登录
                    ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                    Bundle bundle = new Bundle();
                    bundle.putString("falg","2");
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    CaCheArote.getInstance().getUserInterface().openLoginActivity(getActivity(), bundle);
                } else {
                    Intent intent = new Intent(getContext(), FindPayMainActivity.class);
                    OrderinfoBean orderBean = PaySendCacheManager.getInstance().getOrderBean();
                    intent.putExtra("order", orderBean);
                    startActivity(intent);
                }
            }
        });
        forsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CacheUserManager.getInstance().getIsLogin()) {//未登录
                    ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                    Bundle bundle = new Bundle();
                    bundle.putString("falg","2");
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    CaCheArote.getInstance().getUserInterface().openLoginActivity(getActivity(), bundle);
                } else {
                    Intent intent1 = new Intent(getContext(), FindsendMainActivity.class);
                    startActivity(intent1);
                }
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
                if (!CacheUserManager.getInstance().getIsLogin()) {//未登录
                    ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                    Bundle bundle = new Bundle();
                    bundle.putString("falg","2");
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    CaCheArote.getInstance().getUserInterface().openLoginActivity(getActivity(), bundle);
                } else {
                    popupWindow = new PopupWindow(getActivity());
                    popupWindow.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
                    popupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
                    View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.personalpresenter_image_pop, null);
                    popupWindow.setContentView(inflate);
                    popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
                    inflate.findViewById(R.id.changeImage).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent,100);
                        }
                    });
                    inflate.findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "退出登录", Toast.LENGTH_SHORT).show();
                            mPresenter.getLogOut();
                        }
                    });
                }
            }
        });
        //获取待支付订单
        awaitPay.setOnClickListener(v -> mPresenter.getFindForPay());
        awaitDeliverGoods.setOnClickListener(v -> mPresenter.getFindSend());
        //高德地图
        gaoDeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CacheUserManager.getInstance().getIsLogin()) {//未登录
                    ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                    Bundle bundle = new Bundle();
                    bundle.putString("falg","2");
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    CaCheArote.getInstance().getUserInterface().openLoginActivity(getActivity(), bundle);
                } else {
                    startActivity(new Intent(getActivity(), MapActivity.class));
                }
            }
        });
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
        PaySendCacheManager.getInstance().setFindForPayBean((FindForPayBean.ResultBean) findForPayBean.getResult());
    }

    @Override
    public void onLoginChange(boolean loginBean) {
        super.onLoginChange(loginBean);
        if (loginBean) {//登陆成功
            userImage.setImageResource(R.drawable.community_default_user_icon);
        } else {//退出登陆
            userImage.setImageResource(R.drawable.email);
        }
    }

    //退出登陆回调
    @Override
    public void onLogOut(LogOutBean logOutBean) {
        if (logOutBean.getCode().equals("200")) {
            Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
            CacheUserManager.getInstance().setLoginBean(false);//修改登录状态
            popupWindow.dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== 100&&resultCode==Activity.RESULT_OK){
            Uri data1 = data.getData();
            Glide.with(this).load(data1).into(userImage);
            popupWindow.dismiss();
        }
    }
}
