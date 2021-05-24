package com.example.threeshopping.cart;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.CartBean;
import com.example.threeshopping.R;
import com.example.threeshopping.cart.adapter.CartAdapter;

import java.util.List;


public class CartFragment extends BaseFragment implements CacheShopManager.ICartChange {


    private ToolBar toolbar;
    private CheckBox checkcompile;
    private RecyclerView cartRv;
    private LinearLayout cartLLfinish;
    private CheckBox checkdelete;
    private Button cartdelete;
    private Button cartcollect;
    private LinearLayout cartLLpayment;
    private CheckBox checkpayment;
    private TextView paymentprice;
    private Button payment;
    private CartAdapter cartAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        checkcompile = (CheckBox) findViewById(R.id.checkcompile);
        cartRv = (RecyclerView) findViewById(R.id.cartRv);
        cartLLfinish = (LinearLayout) findViewById(R.id.cartLLfinish);
        checkdelete = (CheckBox) findViewById(R.id.checkdelete);
        cartdelete = (Button) findViewById(R.id.cartdelete);
        cartcollect = (Button) findViewById(R.id.cartcollect);
        cartLLpayment = (LinearLayout) findViewById(R.id.cartLLpayment);
        checkpayment = (CheckBox) findViewById(R.id.checkpayment);
        paymentprice = (TextView) findViewById(R.id.paymentprice);
        payment = (Button) findViewById(R.id.payment);
    }

    @Override
    protected void initPrensenter() {

    }
    private List<CartBean.ResultBean> carts;

    @Override
    protected void initData() {
        checkcompile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkcompile.setText("完成");
                    cartLLpayment.setVisibility(View.GONE);
                    cartLLfinish.setVisibility(View.VISIBLE);
                }else {
                    checkcompile.setText("编辑");
                    cartLLpayment.setVisibility(View.VISIBLE);
                    cartLLfinish.setVisibility(View.GONE);
                }
            }
        });

        //注册
        CacheShopManager.getInstance().registerCart(this);
        carts = CacheShopManager.getInstance().getCarts();


        cartAdapter = new CartAdapter();
        if (carts != null) {
            cartAdapter.updata(carts);
        }
        //数据
        cartRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartRv.setAdapter(cartAdapter);

        cartAdapter.setCartItemListener(new CartAdapter.ICartItemListener() {
            @Override
            public void onItemChildClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.shopCartCheck:
                        Toast.makeText(getActivity(), "你好", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.shopCartSub:
                        Toast.makeText(getActivity(), "你好2", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.shopCartAdd:
                        Toast.makeText(getActivity(), "你好3", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });


    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void destroy() {
        super.destroy();
        CacheShopManager.getInstance().unRegisterCart(this);
    }

    //购物车数据
    @Override
    public void onShowCart(List<CartBean.ResultBean> carts) {
        Log.i("zybee", "initData: "+carts);
        this.carts = carts;
        cartAdapter.updata(carts);
    }
}