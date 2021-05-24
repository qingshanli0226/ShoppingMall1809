package com.example.shoppingmallsix.fragment.shoppingcarfragment;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.LoginBean;
import com.example.shoppingmallsix.R;
import com.example.user.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCarFragment extends BaseFragment {

    private TextView shopText;
    private String bianji = getString(R.string.compile);
    private String wancheng =  getString(R.string.accomplish);
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

}
