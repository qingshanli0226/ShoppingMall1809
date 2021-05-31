package com.example.threeshopping.cart;

import android.os.Bundle;
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

import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.CartBean;
import com.example.net.bean.CheckNumAll;
import com.example.net.bean.LoginBean;
import com.example.net.bean.PayBean;
import com.example.threeshopping.R;
import com.example.threeshopping.cart.adapter.CartAdapter;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CartFragment extends BaseFragment<CartPresenter> implements CacheShopManager.ICartChange, ICartView {


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
    private PayBean payBean;

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
        mPresenter = new CartPresenter(this);

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
                        ImageView itemImageView = (ImageView) view;
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
        cartdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delectProduct();
            }
        });


        //判断是否全选
        isCheck();
        //更改价格
        priceCount();

        //点击支付
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                payBean = new PayBean();
                payBean.setBody(new ArrayList<>());
                payBean.setSubject("购买");
                List<PayBean.BodyBean> body = payBean.getBody();

                payBean.setTotalPrice(paymentprice.getText().toString());
                for (int i = 0; i < cartAdapter.getData().size(); i++) {
                    if (cartAdapter.getData().get(i).isProductSelected()) {
                        body.add(new PayBean.BodyBean(cartAdapter.getData().get(i).getProductName(),
                                cartAdapter.getData().get(i).getProductId(),
                                cartAdapter.getData().get(i).getProductNum(),
                                cartAdapter.getData().get(i).getUrl(),
                                cartAdapter.getData().get(i).getProductPrice()+""));
                    }
                }
                LoginBean.ResultBean result = CacheUserManager.getInstance().getLoginBean().getResult();
                LogUtil.d("zyb"+result.getPhone()+"   "+result.getAddress());
                //判断是否选中
                if (body.size() >= 1) {
                    //选中一个
                    //判断是否绑定信息
                    if(result.getPhone() != null && result.getAddress() !=null ){

                        mPresenter.checkNumAll(body);


                    } else{
                        //跳转绑定页面
                        CommonArouter.getInstance().build(Constants.PATH_BIND).navigation();
                        getActivity().finish();
                    }
                } else {
                    Toast.makeText(getActivity(), "没有选中", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void delectProduct() {
        int position = -1;
        ArrayList<CartBean.ResultBean> resultBeans = new ArrayList<>();
        for (int i = 0; i < cartAdapter.getData().size(); i++) {
            if (cartAdapter.getData().get(i).isProductSelected()) {
                resultBeans.add(cartAdapter.getData().get(i));
                position = i;
            }
        }

        if (resultBeans.size() == 1) {
            Toast.makeText(getActivity(), "shanc", Toast.LENGTH_SHORT).show();
            //选中一个
            mPresenter.removeOneProduct(position, resultBeans.get(0));
        } else if (resultBeans.size() > 1) {
            //选中多个
            mPresenter.removeMany(resultBeans);
        } else {
            Toast.makeText(getActivity(), "没有选中", Toast.LENGTH_SHORT).show();
        }
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
        CacheShopManager.getInstance().destory();
    }

    //购物车数据
    @Override
    public void onShowCart(List<CartBean.ResultBean> carts) {
        this.carts = carts;
        cartAdapter.updata(carts);
        EventBus.getDefault().post("");
        //判断是否全选
        isCheck();
    }

    //单选
    @Override
    public void onCheck(int position, boolean isCheck) {

        cartAdapter.notifyItemChanged(position);
        //反选
        isCheck();

        //更改价格
        priceCount();
    }

    private void isCheck() {
        int count = 0;
        for (CartBean.ResultBean datum : cartAdapter.getData()) {
            if (datum.isProductSelected()) {
                count++;
            }
        }
        if (count == cartAdapter.getData().size() && count != 0) {
            isAll = true;
        } else {
            isAll = false;
            paymentprice.setText("0");
        }
        checkpayment.setChecked(isAll);

    }

    //更改价格
    private void priceCount() {
        int priceCount = 0;
        for (CartBean.ResultBean datum : cartAdapter.getData()) {
            if (datum.isProductSelected()) {
                priceCount += Integer.parseInt(datum.getProductNum()) * Float.parseFloat(datum.getProductPrice() + "");
            }
        }
        paymentprice.setText(priceCount + "");
    }

    //全选
    @Override
    public void onCheckAll(boolean isChcekAll) {
        this.isAll = isChcekAll;
        checkpayment.setChecked(isAll);

        cartAdapter.notifyDataSetChanged();

        //更改价格
        priceCount();
    }

    //增加数量
    @Override
    public void onNum(int position) {
        cartAdapter.notifyItemChanged(position);

        //更改价格
        priceCount();

    }

    //删除一个
    @Override
    public void onRemoveProduct(int position) {
        cartAdapter.getData().remove(position);
        cartAdapter.notifyItemRemoved(position);
        //再次更新小远点
        EventBus.getDefault().post("");
        //判断是否全选
        isCheck();
    }
    @Override
    public void onCheckNumAll(CheckNumAll checkNumAll) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) payBean);
        CommonArouter.getInstance().build(Constants.PATH_ORDERINFOACTIVITY).with(bundle).navigation();
        getActivity().finish();

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