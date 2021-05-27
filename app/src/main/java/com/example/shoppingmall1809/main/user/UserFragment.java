package com.example.shoppingmall1809.main.user;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.commom.ShopConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.net.model.FindForBean;
import com.example.net.model.LoginBean;
import com.example.shoppingcar.user.findforpay.FindForPayActivity;
import com.example.shoppingmall1809.R;

public class UserFragment extends BaseFragment<UserPresenter> implements ShopeUserManager.IUserLoginChanged ,IUserView , ShoppingCarManager.IFindForBean {


    private LinearLayout fragUserFukuan;
    private LinearLayout fragUserFahuo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personalcenter;
    }

    @Override
    protected void initData() {

        ShoppingCarManager.getInstance().registerFindForBean(this::onFindForBean);

        ShopeUserManager.getInstance().register(this::onLoginChange);


        if (ShopeUserManager.getInstance().getLoginBean()!=null) {
            httpPresenter.getFindForPayData();
            httpPresenter.getFindForSendData();
        }

        fragUserFukuan.setOnClickListener(view -> {
            if (ShopeUserManager.getInstance().getLoginBean()!=null) {
                ARouter.getInstance().build(ShopConstants.SHOP_PAY).navigation();
            }else {
                Toast.makeText(getActivity(), ""+getResources().getString(R.string.loginFirst), Toast.LENGTH_SHORT).show();
            }
        });

        fragUserFahuo.setOnClickListener(view -> {
            if (ShopeUserManager.getInstance().getLoginBean()!=null) {
                ARouter.getInstance().build(ShopConstants.SHOP_PAY).navigation();
            }else {
                Toast.makeText(getActivity(), ""+getResources().getString(R.string.loginFirst), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserPresenter(this);
    }

    @Override
    protected void initView() {

        fragUserFukuan = (LinearLayout) findViewById(R.id.frag_user_fukuan);
        fragUserFahuo = (LinearLayout) findViewById(R.id.frag_user_fahuo);
    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        httpPresenter.getFindForPayData();
        httpPresenter.getFindForSendData();
    }

    @Override
    public void onFindForPayData(FindForBean findForBean) {
        if (findForBean.getCode().equals("200")) {
            ShoppingCarManager.getInstance().setFindForPayBean(findForBean);
        }
    }

    @Override
    public void onFindForSendData(FindForBean findForBean) {
        if (findForBean.getCode().equals("200")) {
            ShoppingCarManager.getInstance().setFindForSendBean(findForBean);
        }
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void destroy() {
        super.destroy();
        ShopeUserManager.getInstance().unregister(this::onLoginChange);
        ShoppingCarManager.getInstance().unRegisterFindForBean();
    }

    @Override
    public void onFindForBean() {
        httpPresenter.getFindForPayData();
        httpPresenter.getFindForSendData();
    }

}
