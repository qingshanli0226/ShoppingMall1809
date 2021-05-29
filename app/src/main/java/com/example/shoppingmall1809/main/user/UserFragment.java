package com.example.shoppingmall1809.main.user;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commom.ShopConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.FindForBean;
import com.example.net.model.LoginBean;
import com.example.shoppingmall1809.R;

public class UserFragment extends BaseFragment implements ShopeUserManager.IUserLoginChanged, IUserView {


    private LinearLayout fragUserFukuan;
    private LinearLayout fragUserFahuo;
    private ToolBar toolbar;
    private LinearLayout toShoppingAddress;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personalcenter;
    }

    @Override
    protected void initData() {


        ShopeUserManager.getInstance().register(this::onLoginChange);


        if (ShopeUserManager.getInstance().getLoginBean() != null) {

        }

        fragUserFukuan.setOnClickListener(view -> {
            if (ShopeUserManager.getInstance().getLoginBean() != null) {
                ARouter.getInstance().build(ShopConstants.SHOP_PAY).navigation();
            } else {
                Toast.makeText(getActivity(), "" + getResources().getString(R.string.loginFirst), Toast.LENGTH_SHORT).show();
            }
        });

        fragUserFahuo.setOnClickListener(view -> {
            if (ShopeUserManager.getInstance().getLoginBean() != null) {
                ARouter.getInstance().build(ShopConstants.SHOP_PAY).navigation();
            } else {
                Toast.makeText(getActivity(), "" + getResources().getString(R.string.loginFirst), Toast.LENGTH_SHORT).show();
            }
        });

        toShoppingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ShopConstants.SHOP_ADDRESS).navigation();
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        fragUserFukuan = (LinearLayout) findViewById(R.id.frag_user_fukuan);
        fragUserFahuo = (LinearLayout) findViewById(R.id.frag_user_fahuo);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toShoppingAddress = (LinearLayout) findViewById(R.id.toShoppingAddress);
    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void destroy() {
        super.destroy();
        ShopeUserManager.getInstance().unregister(this::onLoginChange);
    }


}
