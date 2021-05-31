package com.shoppingmall.main.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.message.MessageActivity;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.pay.unpaid.PayDealActivity;


public class MineFragment extends BaseFragment implements ShopMallUserManager.IUserLoginChanged {

    private TextView textname;
    private ImageView isLogin;
    private ImageView message;
    private TextView pay;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        textname = mView.findViewById(R.id.textname);
        isLogin = (ImageView) mView.findViewById(R.id.isLogin);
        message = mView.findViewById(R.id.message);
        pay = mView.findViewById(R.id.pay);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        LoginBean loginBean = ShopMallUserManager.getInstance().getLoginBean();
        if (loginBean != null) {
            textname.setText(loginBean.getResult().getName());
        } else {
            textname.setText("未登录");
        }
        isLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ExitUserActivity.class);
                startActivity(intent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PayDealActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onLoginChanged(LoginBean loginBean) {

    }
}
