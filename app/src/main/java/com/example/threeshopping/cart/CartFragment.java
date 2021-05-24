package com.example.threeshopping.cart;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.BaseRvAdapter;
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
    private boolean isAll = false;

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
                    checkdelete.setChecked(isAll);

                }
            }
        });

        //注册
        CacheShopManager.getInstance().registerCart(this);
        carts = CacheShopManager.getInstance().getCarts();

        //获取数据
        cartAdapter = new CartAdapter();
        if (carts != null) {
            cartAdapter.updata(carts);
        }
        //数据
        cartRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartRv.setAdapter(cartAdapter);
        cartAdapter.setRvItemOnClickListener(new BaseRvAdapter.IRvItemOnClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }

            @Override
            public boolean onLongItemClick(int position, View view) {
                return false;
            }
        });

        cartAdapter.setCartItemListener(new CartAdapter.ICartItemListener() {
            @Override
            public void onItemChildClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.shopCartCheck:
                        //选中
                        CartBean.ResultBean resultBean = cartAdapter.getData().get(position);
                        //修改
                        ImageView imageView = (ImageView) view;

                        if (resultBean.isProductSelected()) {
                            resultBean.setProductSelected(false);
                            imageView.setImageResource(R.drawable.checkbox_unselected);
                        } else{
                            resultBean.setProductSelected(true);
                            imageView.setImageResource(R.drawable.checkbox_selected);
                        }
                        CacheShopManager.getInstance().updateProductSelect(resultBean);
                        //反选
                        int count = 0;
                        for (CartBean.ResultBean datum : cartAdapter.getData()) {
                            if(datum.isProductSelected()){
                                count++;
                            }
                        }
                        if(count == cartAdapter.getData().size()){
                            checkpayment.setChecked(true);
                        } else{
                            checkpayment.setChecked(false);
                            isAll = false;
                        }
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
        //全选
        checkpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAll();
            }
        });
        //编辑全选
        checkdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }

    private void selectAll() {
        if(isAll){
            isAll = false;
        } else{
            isAll = true;
        }
        for (CartBean.ResultBean datum : cartAdapter.getData()) {
            datum.setProductSelected(isAll);
        }
        CacheShopManager.getInstance().selectAll(isAll);
        cartAdapter.notifyDataSetChanged();
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
        this.carts = carts;
        cartAdapter.updata(carts);


    }
}