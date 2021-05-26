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

import com.example.common.LogUtil;
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
                CartBean.ResultBean resultBean = cartAdapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.shopCartCheck:
                        //修改
                        ImageView imageView = (ImageView) view;

                        if (resultBean.isProductSelected()) {
                            imageView.setImageResource(R.drawable.checkbox_unselected);
                            resultBean.setProductSelected(false);
                        } else{
                            imageView.setImageResource(R.drawable.checkbox_selected);
                            resultBean.setProductSelected(true);

                        }
                        CacheShopManager.getInstance().updateProductSelect(position,resultBean);
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
                        //判断库存
                        if(Integer.parseInt(resultBean.getProductNum()) ==0){
                            return;
                        }
                        break;
                    case R.id.shopCartAdd:
                        //判断库存
                        CartBean.ResultBean result = new CartBean.ResultBean();
                        result.setProductId(resultBean.getProductId());
                        result.setProductNum(Integer.parseInt(resultBean.getProductNum())+1+"");
                        CacheShopManager.getInstance().inventory(position,result);
                        break;
                }
                cartAdapter.notifyDataSetChanged();
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
    //添加数据
    @Override
    public void onAddCart(int position) {
        if(position > cartAdapter.getData().size()){
            cartAdapter.getData().add(CacheShopManager.getInstance().getCarts().get(position));
        } else{
            cartAdapter.getData().get(position).setProductNum(CacheShopManager.getInstance().getCarts().get(position).getProductNum());
        }
        cartAdapter.notifyItemChanged(position);
    }

    //单选
    @Override
    public void onCheck(int position, boolean isCheck) {
        cartAdapter.getData().get(position).setProductSelected(isCheck);
        cartAdapter.notifyItemChanged(position);
    }
    //全选
    @Override
    public void onCheckAll(boolean isChcekAll) {
        for (CartBean.ResultBean datum : cartAdapter.getData()) {
            datum.setProductSelected(isChcekAll);
        }
        cartAdapter.notifyDataSetChanged();
    }

    //增加数量

    @Override
    public void onNum(int position) {
//        CartBean.ResultBean bean = cartAdapter.getData().get(position);
//        bean.setProductNum(Integer.parseInt(bean.getProductNum())+1+"");
//        cartAdapter.notifyItemChanged(position);
    }
}