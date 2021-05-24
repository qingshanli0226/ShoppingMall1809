package com.example.shoppingmallsix.fragment.shoppingcarfragment;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShoppingCarBean;
import com.example.shoppingmallsix.R;
import com.example.user.login.LoginActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCarFragment extends BaseFragment<ShoppingPresenter> implements IShopping, CacheUserManager.ILoginChange {

    private TextView shopText;
    private String bianji = "编辑";
    private String wancheng = "完成";
    private TextView shopTotal;
    private TextView shopDoaller;
    private TextView shopMoney;
    private Button buyBt;
    private Button deleteBt;
    private Button shouCangBt;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        shopText = mBaseView.findViewById(R.id.shopText);
        shopTotal = mBaseView.findViewById(R.id.shopTotal);
        shopDoaller = mBaseView.findViewById(R.id.shopDoaller);
        shopMoney = mBaseView.findViewById(R.id.shopMoney);
        buyBt = mBaseView.findViewById(R.id.buyBt);
        deleteBt = mBaseView.findViewById(R.id.deleteBt);
        shouCangBt = mBaseView.findViewById(R.id.shouCangBt);

        shopText.setText(bianji);
        shopText.setTag(false);
        shopText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = (boolean) shopText.getTag();
                if (!flag) {
                    shopText.setText(wancheng);
                    shopText.setTag(true);
                    shopTotal.setVisibility(View.GONE);
                    shopDoaller.setVisibility(View.GONE);
                    shopMoney.setVisibility(View.GONE);
                    buyBt.setVisibility(View.GONE);
                    deleteBt.setVisibility(View.VISIBLE);
                    shouCangBt.setVisibility(View.VISIBLE);
                } else {
                    shopText.setText(bianji);
                    shopText.setTag(false);
                    shopTotal.setVisibility(View.VISIBLE);
                    shopDoaller.setVisibility(View.VISIBLE);
                    shopMoney.setVisibility(View.VISIBLE);
                    buyBt.setVisibility(View.VISIBLE);
                    deleteBt.setVisibility(View.GONE);
                    shouCangBt.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void onShopping(ShoppingCarBean shoppingCarBean) {
        if (shoppingCarBean.getCode().equals("200")) {
            List<ShoppingCarBean.ResultBean> result = shoppingCarBean.getResult();

            ShoppingCarAdapter shoppingCarAdapter = new ShoppingCarAdapter(result);
            shoppingTrolleyRv.setLayoutManager(new LinearLayoutManager(getContext()));
            shoppingTrolleyRv.setAdapter(shoppingCarAdapter);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }
}
