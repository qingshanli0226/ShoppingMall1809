package com.example.myapplication.particulars;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.common.type.ToLoginType;
import com.example.common.type.TypeString;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CacheUserManager;
import com.example.myapplication.R;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;


public class ParticularsActivity extends BaseActivity<AddShoppingCartPresenter> implements IAddShoppingCartView {

    private ImageView particularsCommodityImage;
    private TextView particularsCommodityName;
    private TextView particularsCommodityPresenter;
    private TextView particularsCommodityPrice;
    private android.widget.RadioButton particularsCommodityCollect;
    private android.widget.RadioButton particularsCommodityShoppingCart;
    private android.widget.Button particularsCommodityAddShoppingCart;
    private boolean isLogin;
    private PopupWindow popupWindow;
    private TextView popName;
    private TextView popPrice;
    private TextView popNum;
    private ImageView popImage;
    private ImageView popSub;
    private ImageView popAdd;
    private RelativeLayout popCencel;
    private RelativeLayout popConfirm;
    private View inflate;
    private String pic;
    private String price;
    private String name;
    private String id;

    @Override
    public int bandLayout() {
        return R.layout.activity_particulars;
    }

    @Override
    public void initView() {
        particularsCommodityImage = (ImageView) findViewById(R.id.particularsCommodityImage);
        particularsCommodityName = (TextView) findViewById(R.id.particularsCommodityName);
        particularsCommodityPresenter = (TextView) findViewById(R.id.particularsCommodityPrice);
        particularsCommodityPrice = (TextView) findViewById(R.id.particularsCommodityPrice);
        particularsCommodityCollect = (RadioButton) findViewById(R.id.particularsCommodityCollect);
        particularsCommodityShoppingCart = (RadioButton) findViewById(R.id.particularsCommodityShoppingCart);
        particularsCommodityAddShoppingCart = (Button) findViewById(R.id.particularsCommodityAddShoppingCart);
        isLogin = CacheUserManager.getInstance().getIsLogin();//获取登陆状态

        //设置pop
        popupWindow = new PopupWindow();
        inflate = LayoutInflater.from(this).inflate(R.layout.particulars_pop, null);
        popupWindow.setContentView(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //给pop赋值
        popImage = inflate.findViewById(R.id.popImage);
        popName = inflate.findViewById(R.id.popName);
        popPrice = inflate.findViewById(R.id.popPrice);
        popSub = inflate.findViewById(R.id.popSub);
        popAdd = inflate.findViewById(R.id.popAdd);
        popNum = inflate.findViewById(R.id.popNum);
        popCencel = inflate.findViewById(R.id.popCencel);
        popConfirm = inflate.findViewById(R.id.popConfirm);
    }

    @Override
    public void initPresenter() {
        mPresenter = new AddShoppingCartPresenter(this);
    }

    @Override
    public void initData() {
        //获取传过来的值
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            pic = extras.getString("pic");
            name = extras.getString("name");
            price = extras.getString("price");
            id = extras.getString("id");
            //赋值
            Glide.with(this).load("http://49.233.0.68:8080" + "/atguigu/img" + pic).into(particularsCommodityImage);
            particularsCommodityName.setText(name);
            particularsCommodityPrice.setText(price);
            //给pop赋值
            Glide.with(this).load("http://49.233.0.68:8080" + "/atguigu/img" + pic).into(popImage);
            popName.setText(name);
            popPrice.setText(price);
        }
        //收藏点击
        particularsCommodityCollect.setOnClickListener(v -> {
            if (!isLogin) {
                //详情页面跳转
                Bundle bundle = new Bundle();
                bundle.putString("falg", "2");
                ToLoginType.getInstance().setActivityType(TypeString.PARTICALARS_TYPE);
                CaCheArote.getInstance().getUserInterface().openLoginActivity(this, bundle);
            } else {
            }
        });
        //购物车点击
        particularsCommodityShoppingCart.setOnClickListener(v -> {
            if (!isLogin) {
                //详情页面跳转
                Bundle bundle = new Bundle();
                bundle.putString("falg", "2");
                ToLoginType.getInstance().setActivityType(TypeString.PARTICALARS_TYPE);
                CaCheArote.getInstance().getUserInterface().openLoginActivity(this, bundle);
            } else {

            }
        });
        //加入购物车点击
        particularsCommodityAddShoppingCart.setOnClickListener(v -> {
            if (!isLogin) {
                //详情页面跳转
                Bundle bundle = new Bundle();
                bundle.putString("falg", "2");
                ToLoginType.getInstance().setActivityType(TypeString.PARTICALARS_TYPE);
                CaCheArote.getInstance().getUserInterface().openLoginActivity(this, bundle);
            } else {
                //显示pop
                popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
            }
        });
        //pop加号点击
        popAdd.setOnClickListener(v -> {
            int num = Integer.parseInt(popNum.getText().toString());
            num++;
            popNum.setText(num + "");
        });
        //pop减号点击
        popSub.setOnClickListener(v -> {
            int num = Integer.parseInt(popNum.getText().toString());
            int a = num;
            if (a-- <= 1) {
                Toast.makeText(ParticularsActivity.this, getString(R.string.notLessThanOne), Toast.LENGTH_SHORT).show();
            } else {
                num--;
            }
            popNum.setText(num + "");
        });
        //pop取消
        popCencel.setOnClickListener(v -> popupWindow.dismiss());
        //pop确定
        popConfirm.setOnClickListener(v -> {
            //刷新加入购物车数量
            int num = Integer.parseInt(popNum.getText().toString());
            //直接调用购物车
            mPresenter.getAddShoppingCart(id, num + "", name, pic, price);
        });
    }

    /**
     * 每次修改登陆状态都获取最新的用户登陆状态
     *
     * @param loginBean
     */
    @Override
    public void onLoginChange(boolean loginBean) {
        super.onLoginChange(loginBean);
        isLogin = loginBean;
    }

    //获取返回的加入购物车请求
    @Override
    public void onAddShoppingCart(ShoppingCartBean shoppingCartBean) {
        String code = shoppingCartBean.getCode();
        if (code.equals("200")) {
            Toast.makeText(this, getString(R.string.addShoppingSucceed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.addShoppingeError), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onIsInventory(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            int num = Integer.parseInt(popNum.getText().toString());
            mPresenter.getAddShoppingCart(id, num + "", name, pic, price);
        } else {
            Toast.makeText(this, getString(R.string.inventoryNot), Toast.LENGTH_SHORT).show();
        }
    }
}