package com.example.shoppingmall1809.main.shoppingtrolley;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.LoginBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.adapter.ShoppingCarAdapter;

import java.util.List;

public class ShoppingTrolleyFragment extends BaseFragment<ShoppingPresenter> implements IShopping, FiannceUserManager.IUserLoginChanged {
    private ToolBar toolbar;
    private RecyclerView shoppingTrolleyRv;
    private CheckBox checkAll;
    private TextView price;
    private TextView accout;

    private boolean isCheck = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppingtrolley;
    }

    @Override
    protected void initData() {
        LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
        if (loginBean!=null){
            httpPresenter.getShoppingData();
        }

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCheck) {
                    checkAll.setBackgroundResource(R.drawable.checkbox_selected);
                    isCheck = true;
                } else {
                    checkAll.setBackgroundResource(R.drawable.checkbox_unselected);
                    isCheck = false;
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        shoppingTrolleyRv = (RecyclerView) findViewById(R.id.shoppingTrolley_rv);
        checkAll = (CheckBox) findViewById(R.id.checkAll);
        price = (TextView) findViewById(R.id.price);
        accout = (TextView) findViewById(R.id.accout);

        FiannceUserManager.getInstance().register(this);
    }

    @Override
    public void onShopping(ShoppingTrolleyBean shoppingTrolleyBean) {
        if (shoppingTrolleyBean.getCode().equals("200")) {
            List<ShoppingTrolleyBean.ResultBean> result = shoppingTrolleyBean.getResult();

            ShoppingCarAdapter shoppingCarAdapter = new ShoppingCarAdapter(result);
            shoppingTrolleyRv.setLayoutManager(new LinearLayoutManager(getContext()));
            shoppingTrolleyRv.setAdapter(shoppingCarAdapter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FiannceUserManager.getInstance().unregister(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        if (loginBean != null) {
            httpPresenter.getShoppingData();
        }
    }
}
