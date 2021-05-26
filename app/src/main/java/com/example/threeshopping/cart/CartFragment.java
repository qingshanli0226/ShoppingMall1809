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
import com.example.net.bean.LoginBean;
import com.example.threeshopping.R;
import com.example.threeshopping.cart.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends BaseFragment<CarPresenter> implements CacheShopManager.ICartChange, ICarView {


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
    private ImageView itemImageView;

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
        mPresenter = new CarPresenter(this);

    }

    private List<CartBean.ResultBean> carts;

    @Override
    protected void initData() {
        checkcompile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkcompile.setText("完成");
                    cartLLpayment.setVisibility(View.GONE);
                    cartLLfinish.setVisibility(View.VISIBLE);
                } else {
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

        cartAdapter.setCartItemListener(new CartAdapter.ICartItemListener() {
            @Override
            public void onItemChildClick(int position, View view) {
                CartBean.ResultBean resultBean = cartAdapter.getData().get(position);
                CartBean.ResultBean result = new CartBean.ResultBean();

                switch (view.getId()) {
                    case R.id.shopCartCheck:
                        //修改
                        result.setProductId(resultBean.getProductId());
                        result.setProductNum(resultBean.getProductNum());
                        result.setProductPrice(resultBean.getProductPrice());
                        itemImageView = (ImageView) view;
                        if (resultBean.isProductSelected()) {
                            result.setProductSelected(false);
                        } else {
                            result.setProductSelected(true);
                        }
                        mPresenter.updateProductSelect(position, result);


                        break;
                    case R.id.shopCartSub:
                        //判断库存

                        if (Integer.parseInt(resultBean.getProductNum()) > 0) {
                            result.setProductId(resultBean.getProductId());
                            result.setProductNum(Integer.parseInt(resultBean.getProductNum()) - 1 + "");
                            result.setProductPrice(resultBean.getProductPrice());
                            mPresenter.upDateNum(position, result);
                        }
                        break;
                    case R.id.shopCartAdd:
                        //判断库存
                        result.setProductId(resultBean.getProductId());
                        result.setProductNum(Integer.parseInt(resultBean.getProductNum()) + 1 + "");

                        result.setProductPrice(resultBean.getProductPrice());
                        mPresenter.upDateNum(position, result);


                        break;

                }
                cartAdapter.notifyDataSetChanged();
            }
        });
        //全选
        checkpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAll) {
                    isAll = false;
                } else {
                    isAll = true;
                }
                mPresenter.selectAll(isAll);
            }
        });
        //编辑全选
        checkdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //删除
        cartdelete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                int position = -1;
                ArrayList<CartBean.ResultBean> resultBeans = new ArrayList<>();
                for (int i = 0; i < cartAdapter.getData().size(); i++) {
                    if (cartAdapter.getData().get(i).isProductSelected()) {
                        resultBeans.add(cartAdapter.getData().get(i));
                        position = i;
                    }
                }

                if (resultBeans.size() == 1) {
                    //选中一个
                    mPresenter.removeOneProduct(position, resultBeans.get(0));
                } else if (resultBeans.size() > 1) {
                    //选中多个
                    mPresenter.removeMany(resultBeans);
                } else {
                    Toast.makeText(getActivity(), "没有选中", Toast.LENGTH_SHORT).show();

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
        this.carts = carts;
        cartAdapter.updata(carts);
        LogUtil.d("zyb" + carts);
    }

    //单选
    @Override
    public void onCheck(int position, boolean isCheck) {

        cartAdapter.notifyItemChanged(position);
        //反选
        int count = 0;
        for (CartBean.ResultBean datum : cartAdapter.getData()) {
            if (datum.isProductSelected()) {
                count++;
            }
        }
        if (count == cartAdapter.getData().size()) {
            checkpayment.setChecked(true);
            isAll = true;
        } else {
            checkpayment.setChecked(false);
            isAll = false;
        }

    }

    //全选
    @Override
    public void onCheckAll(boolean isChcekAll) {
        this.isAll = isChcekAll;
        cartAdapter.notifyDataSetChanged();
    }

    //增加数量
    @Override
    public void onNum(int position) {
        cartAdapter.notifyItemChanged(position);

    }

    //删除一个
    @Override
    public void removeProduct(int position) {
        cartAdapter.getData().remove(position);
        cartAdapter.notifyItemRemoved(position);
        LogUtil.d("positona" + position);
    }

    @Override
    public void removeMany(List<CartBean.ResultBean> resultBeans) {
        for (int i = cartAdapter.getData().size() - 1; i >= 0; i--) {
            CartBean.ResultBean bean = cartAdapter.getData().get(i);
            for (int i1 = resultBeans.size() - 1; i1 >= 0; i1--) {
                if (bean.getProductId().equals(resultBeans.get(i1).getProductId())) {
                    cartAdapter.getData().remove(i);
                    resultBeans.remove(i1);
                }
            }
        }
        LogUtil.d("removeMany");
        cartAdapter.notifyDataSetChanged();
    }

    //添加数据
    @Override
    public void onAddCart(int position) {
        if (position > cartAdapter.getData().size()) {
            cartAdapter.getData().add(CacheShopManager.getInstance().getCarts().get(position - 1));
            cartAdapter.notifyItemChanged(position - 1);
        } else {
            cartAdapter.getData().get(position).setProductNum(CacheShopManager.getInstance().getCarts().get(position).getProductNum());
            cartAdapter.notifyItemChanged(position);
        }
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


}