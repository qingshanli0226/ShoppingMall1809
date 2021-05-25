package com.example.shoppingcar.shoppingtrolley;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.R;
import com.example.shoppingcar.shoppingtrolley.adapter.ShoppingCarAdapter;

import java.util.List;

public class ShoppingViewTrolleyFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView, ShopeUserManager.IUserLoginChanged {
    private ToolBar toolbar;
    private RecyclerView shoppingTrolleyRv;
    private CheckBox checkAll;
    private TextView price;
    private TextView accout;
    private ShoppingCarAdapter shoppingCarAdapter;
    private boolean isCheck = false;
    private List<ShoppingTrolleyBean.ResultBean> result;
    private CheckBox checkBox;
    private int position;
    private boolean isRequest = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppingtrolley;
    }

    @Override
    protected void initData() {


        ShopeUserManager.getInstance().register(this::onLoginChange);

        LoginBean loginBean = ShopeUserManager.getInstance().getLoginBean();
        if (loginBean != null) {
            httpPresenter.getShoppingData();
            isRequest =false;
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

    }

    @Override
    public void onShopping(ShoppingTrolleyBean shoppingTrolleyBean) {
        isRequest = true;
        if (shoppingTrolleyBean.getCode().equals("200")) {
            result = shoppingTrolleyBean.getResult();

            shoppingCarAdapter = new ShoppingCarAdapter(result);
            shoppingTrolleyRv.setLayoutManager(new LinearLayoutManager(getContext()));
            shoppingTrolleyRv.setAdapter(shoppingCarAdapter);

            shoppingCarAdapter.setShopItemListener((position, view) -> {
                int id = view.getId();
                if (id == R.id.shoppingTrolley_add) {
                } else if (id == R.id.shoppingTrolley_sub) {
                } else if (id == R.id.shoppingTrolley_check) {
                    checkBox = (CheckBox) view;
                    this.position = position;
                    ShoppingTrolleyBean.ResultBean resultBean = result.get(position);
                    httpPresenter.getUpDateSelected(resultBean.getProductId(), resultBean.isProductSelected(), resultBean.getProductName(), resultBean.getUrl(), resultBean.getProductPrice().toString());
                    isRequest = false;
                }
            });

        }
    }

    @Override
    public void onUpDateSelected(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            isRequest = true;
            result.get(position).setProductSelected(checkBox.isChecked());
            shoppingCarAdapter.notifyItemChanged(position);
        }
//        LogUtils.json(result.get(position));
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
    public void onLoginChange(LoginBean loginBean) {
        if (loginBean != null) {
            httpPresenter.getShoppingData();
            isRequest = false;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ShopeUserManager.getInstance().unregister(this::onLoginChange);
    }
}
