package com.example.threeshopping.particulars;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.UserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.CartBean;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.SelectBean;
import com.example.threeshopping.MainActivity;
import com.example.threeshopping.R;
import com.example.threeshopping.particulars.detail.DetailPresenter;
import com.example.threeshopping.particulars.detail.IDetailView;
import com.example.user.user.UserActivity;
import com.fiannce.sql.SqlBean;
import com.fiannce.sql.UtileSql;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.http.HEAD;


public class ParticularsActivity extends BaseActivity<DetailPresenter> implements IDetailView {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView paricularsImg;
    private android.widget.TextView paricularsName;
    private android.widget.TextView paricularsPrice;
    private android.widget.ImageView shopcar;
    private android.widget.Button particularsJoin;
    private LoginBean loginBean;
    private Button popYes;
    private Button popNo;
    private ImageView popAdd;
    private TextView popNum;
    private ImageView popSub;
    private TextView popPrice;
    private TextView popTitle;
    private ImageView popPic;
    View view;
    private ProductBean productBean;
    int num = 1;//购买数量
    int count = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        paricularsImg = (ImageView) findViewById(R.id.pariculars_img);
        paricularsName = (TextView) findViewById(R.id.pariculars_name);
        paricularsPrice = (TextView) findViewById(R.id.pariculars_price);
        shopcar = (ImageView) findViewById(R.id.shopcar);
        particularsJoin = (Button) findViewById(R.id.particulars_join);
        toolbar.setToolbarOnClickLisenter(this);

        view = LayoutInflater.from(ParticularsActivity.this).inflate(R.layout.pop_layout, null);
        popPic = (ImageView) view.findViewById(R.id.pop_pic);
        popTitle = (TextView) view.findViewById(R.id.pop_title);
        popPrice = (TextView) view.findViewById(R.id.pop_price);
        popSub = (ImageView) view.findViewById(R.id.pop_sub);
        popNum = (TextView) view.findViewById(R.id.pop_num);
        popAdd = (ImageView) view.findViewById(R.id.pop_add);
        popNo = (Button) view.findViewById(R.id.pop_no);
        popYes = (Button) view.findViewById(R.id.pop_yes);
    }


    @Override
    public void initPresenter() {
        mPresenter = new DetailPresenter(this);
    }

    private String title;
    private String pic;
    private String price;
    private String id;

    @Override
    public void initData() {
        Bundle bundle = CommonArouter.getInstance().getBundle();
        title = bundle.getString("title");
        pic = bundle.getString("pic");
        price = bundle.getString("price");
        id = bundle.getString("id");
        Glide.with(this).load(pic).into(paricularsImg);
        paricularsName.setText("" + title);
        paricularsPrice.setText("￥" + price);

        loginBean = UserManager.getInstance().getLoginBean();


        particularsJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginBean != null) {
                    PopupWindow popupWindow = new PopupWindow(ParticularsActivity.this);
                    popupWindow.setContentView(view);
                    popupWindow.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
                    int height = ScreenUtils.getScreenHeight() * 1 / 3;
                    popupWindow.setHeight(height);
                    popPrice.setText("" + price);
                    popTitle.setText("" + title);
                    Glide.with(ParticularsActivity.this).load(pic).into(popPic);
                    popSub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (num > 1) {
                                num--;
                                popNum.setText("" + num);
                            }

                        }
                    });
                    popAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            num++;
                            popNum.setText("" + num);
                        }
                    });
                    popYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();

                            CartBean.ResultBean result = new CartBean.ResultBean();
                            result.setProductId(id);
                            result.setProductNum(""+num);
                            mPresenter.inventory(result);



                            //数据库
                            List<SqlBean> sqlBeans = UtileSql.getInstance().getDaoSession().loadAll(SqlBean.class);
                            LogUtil.i(sqlBeans.toString());

                            for (int i = 0; i <sqlBeans.size() ; i++) {
                                if (id.equals(sqlBeans.get(i).getProductId())){
                                    Long autoincrementid = sqlBeans.get(i).getId();
                                    SqlBean sqlBean = new SqlBean();
                                    sqlBean.setId(autoincrementid);
                                    sqlBean.setProductId(ParticularsActivity.this.id);
                                    sqlBean.setProductName(title);
                                    sqlBean.setProductNum(num);
                                    sqlBean.setUrl(pic);
                                    sqlBean.setProductPrice(price);
                                    UtileSql.getInstance().getDaoSession().update(sqlBean);
                                }else {
                                    count++;
                                }
                            }
                            if (count == sqlBeans.size()){
                                SqlBean sqlBean = new SqlBean();
                                sqlBean.setProductId(id);
                                sqlBean.setProductName(title);
                                sqlBean.setProductNum(num);
                                sqlBean.setUrl(pic);
                                sqlBean.setProductPrice(price);
                                UtileSql.getInstance().getDaoSession().insert(sqlBean);
                            }
                            LogUtils.json(sqlBeans);
                        }
                    });

                    popNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                    popupWindow.showAsDropDown(particularsJoin, 0, 200);
                } else {
//                    CommonArouter.getInstance().build(Constants.PATH_USER).with(bundle).navigation();
                    Intent intent = new Intent(ParticularsActivity.this, UserActivity.class);
                    startActivityForResult(intent,101);
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("name","Particulars");
//                    CommonArouter.getInstance().build(Constants.PATH_USER).with(bundle1).startActivityForResult();
                }
            }
        });

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onAddCart(SelectBean selectBean) {
        if (selectBean.getCode().equals("200")) {
            Toast.makeText(this, "添加购物车成功", Toast.LENGTH_SHORT).show();
            //添加数据
            CartBean.ResultBean resultBean = new CartBean.ResultBean();
            resultBean.setProductId(productBean.getProductId());
            resultBean.setProductNum(productBean.getProductNum()+"");
            resultBean.setProductPrice(productBean.getProductPrice());
            resultBean.setUrl(productBean.getUrl());
            resultBean.setProductName(productBean.getProductName());
            CacheShopManager.getInstance().addData(resultBean);
            //再次更新小远点
            EventBus.getDefault().post("");


        }
    }

    @Override
    public void onInventory(SelectBean selectBean) {
        if (selectBean.getCode().equals("200")){
            productBean = new ProductBean();
            productBean.setProductId(id);
            productBean.setProductName(title);
            productBean.setProductNum(num);
            productBean.setUrl(pic);
            productBean.setProductPrice(price);
            mPresenter.addProduct(productBean);
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

    @Override
    public void onUserChange(LoginBean loginBean) {
        super.onUserChange(loginBean);
        this.loginBean = loginBean;
    }


}
