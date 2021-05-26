package com.example.shoppingmallsix.shopcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.example.shoppingmallsix.R;

public class ShoppingCarActivity extends BaseActivity {

    private String bianji = "编辑";
    private String wancheng = "完成";
    private TextView shopcarText;
    private androidx.recyclerview.widget.RecyclerView shopRv;
    private android.widget.CheckBox shopcarCheck;
    private TextView shopcarTotal;
    private TextView shopcarDoaller;
    private TextView shopcarMoney;
    private android.widget.Button buycarBt;
    private android.widget.Button deleteCarBt;
    private android.widget.Button shouCangCarBt;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        shopcarText = findViewById(R.id.shopcarText);
        shopRv = findViewById(R.id.shopRv);
        shopcarCheck = findViewById(R.id.shopcarCheck);
        shopcarTotal = findViewById(R.id.shopcarTotal);
        shopcarDoaller = findViewById(R.id.shopcarDoaller);
        shopcarMoney = findViewById(R.id.shopcarMoney);
        buycarBt = findViewById(R.id.buycarBt);
        deleteCarBt = findViewById(R.id.deleteCarBt);
        shouCangCarBt = findViewById(R.id.shouCangCarBt);

        shopcarText.setText(bianji);
        shopcarText.setTag(false);
        shopcarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = (boolean) shopcarText.getTag();
                if (!flag) {
                    shopcarText.setText(wancheng);
                    shopcarText.setTag(true);
                    shopcarTotal.setVisibility(View.GONE);
                    shopcarDoaller.setVisibility(View.GONE);
                    shopcarMoney.setVisibility(View.GONE);
                    buycarBt.setVisibility(View.GONE);
                    deleteCarBt.setVisibility(View.VISIBLE);
                    shouCangCarBt.setVisibility(View.VISIBLE);
                } else {
                    shopcarText.setText(bianji);
                    shopcarText.setTag(false);
                    shopcarTotal.setVisibility(View.VISIBLE);
                    shopcarDoaller.setVisibility(View.VISIBLE);
                    shopcarMoney.setVisibility(View.VISIBLE);
                    buycarBt.setVisibility(View.VISIBLE);
                    deleteCarBt.setVisibility(View.GONE);
                    shouCangCarBt.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_car;
    }
}
