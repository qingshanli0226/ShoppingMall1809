package com.example.electricityproject.shopp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.LogBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;
import com.example.manager.BusinessARouter;
import com.example.view.ToolBar;

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView {


    private ToolBar toolbar;
    private Button goHome;
    private ImageView shoppingImg;
    private ShoppingAdapter shoppingAdapter;
    private RecyclerView buyCarRv;

    @Override
    protected void initData() {
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessARouter.getInstance().getAppManager().OpenMainActivity(getContext(), null);
            }
        });

        httpPresenter.getShortProductsData();

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        goHome = (Button) findViewById(R.id.go_home);
        shoppingImg = (ImageView) findViewById(R.id.shopping_img);
        buyCarRv = (RecyclerView) findViewById(R.id.buy_car_rv);
        buyCarRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void getShortProductData(ShortcartProductBean shortcartProductBean) {
        if (shortcartProductBean.getCode().equals("200")) {
            shoppingAdapter = new ShoppingAdapter();
            shoppingAdapter.updateData(shortcartProductBean.getResult());
            buyCarRv.setAdapter(shoppingAdapter);

        }else{
            shoppingImg.setVisibility(View.GONE);
            goHome.setVisibility(View.GONE);
        }
    }
}