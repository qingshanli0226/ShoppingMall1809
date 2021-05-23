package com.shoppingmall.main.mine;

import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.net.bean.LoginBean;


public class MineFragment extends BaseFragment implements ShopMallUserManager.IUserLoginChanged {

    private TextView textname;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        textname = mView.findViewById(R.id.textname);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLoginChanged(LoginBean loginBean) {
        if (loginBean!=null){
            textname.setText(loginBean.getResult().getName());
        }else{
            textname.setText("未登录");
        }
    }
}
