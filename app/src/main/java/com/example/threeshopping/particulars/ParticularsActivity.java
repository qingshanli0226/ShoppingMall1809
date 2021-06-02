package com.example.threeshopping.particulars;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.CircleView;
import com.example.net.bean.CartBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.SelectBean;
import com.example.threeshopping.R;
import com.example.threeshopping.particulars.detail.DetailPresenter;
import com.example.threeshopping.particulars.detail.IDetailView;
import com.example.user.service.AutoService;
import com.example.user.user.UserActivity;
import com.fiannce.sql.bean.SqlBean;
import com.fiannce.sql.manager.SqlManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class ParticularsActivity extends BaseActivity<DetailPresenter> implements IDetailView {

    private RelativeLayout rootview;
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
    private CircleView detailCircle;
    CartBean.ResultBean result;
    @Override
    public int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    public void initView() {
        rootview = findViewById(R.id.particulars);

        paricularsImg = (ImageView) findViewById(R.id.pariculars_img);
        paricularsName = (TextView) findViewById(R.id.pariculars_name);
        paricularsPrice = (TextView) findViewById(R.id.pariculars_price);
        shopcar = (ImageView) findViewById(R.id.shopcar);
        particularsJoin = (Button) findViewById(R.id.particulars_join);


        view = LayoutInflater.from(ParticularsActivity.this).inflate(R.layout.pop_layout, null);
        popPic = (ImageView) view.findViewById(R.id.pop_pic);
        popTitle = (TextView) view.findViewById(R.id.pop_title);
        popPrice = (TextView) view.findViewById(R.id.pop_price);
        popSub = (ImageView) view.findViewById(R.id.pop_sub);
        popNum = (TextView) view.findViewById(R.id.pop_num);
        popAdd = (ImageView) view.findViewById(R.id.pop_add);
        popNo = (Button) view.findViewById(R.id.pop_no);
        popYes = (Button) view.findViewById(R.id.pop_yes);
        detailCircle = (CircleView) findViewById(R.id.detailCircle);
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

        loginBean = CacheUserManager.getInstance().getLoginBean();


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

                            result = new CartBean.ResultBean();
                            result.setProductId(id);
                            result.setProductNum("" + num);

                            if (CacheConnectManager.getInstance().isConnect()) {
                                mPresenter.inventory(result);
                            } else {
                                Toast.makeText(ParticularsActivity.this, "网络走丢了", Toast.LENGTH_SHORT).show();
                            }



                            //数据库
                            List<SqlBean> sqlBeans = SqlManager.getInstance().getDaoSession().loadAll(SqlBean.class);
                            LogUtil.i(sqlBeans.toString());

                            for (int i = 0; i < sqlBeans.size(); i++) {
                                if (id.equals(sqlBeans.get(i).getProductId())) {
                                    Long autoincrementid = sqlBeans.get(i).getId();
                                    SqlBean sqlBean = new SqlBean();
                                    sqlBean.setId(autoincrementid);
                                    sqlBean.setProductId(ParticularsActivity.this.id);
                                    sqlBean.setProductName(title);
                                    sqlBean.setProductNum(num);
                                    sqlBean.setUrl(pic);
                                    sqlBean.setProductPrice(price);
                                    SqlManager.getInstance().getDaoSession().update(sqlBean);
                                }else {
                                    count++;
                                }
                            }
                            if (count == sqlBeans.size()) {
                                SqlBean sqlBean = new SqlBean();
                                sqlBean.setProductId(id);
                                sqlBean.setProductName(title);
                                sqlBean.setProductNum(num);
                                sqlBean.setUrl(pic);
                                sqlBean.setProductPrice(price);
                                SqlManager.getInstance().getDaoSession().insert(sqlBean);
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

                    Intent intent = new Intent(ParticularsActivity.this, UserActivity.class);
                    startActivityForResult(intent, 101);

                }
            }
        });

        shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginBean != null) {
                    finish();
                    CommonArouter.getInstance().build(Constants.PATH_SHOPACTIVITY).navigation();
                }
            }
        });


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
            resultBean.setProductNum(productBean.getProductNum() + "");
            resultBean.setProductPrice(productBean.getProductPrice());
            resultBean.setUrl(productBean.getUrl());
            resultBean.setProductName(productBean.getProductName());
            LogUtils.json("resultBean" + resultBean.getProductPrice());

            CacheShopManager.getInstance().addData(resultBean);
            //再次更新小远点
            EventBus.getDefault().post("");
            showAnima();
            //这个页面的小数点
            detailCircle.setVisibility(View.VISIBLE);
            detailCircle.setText(CacheShopManager.getInstance().getCarts().size() + "");

        }
    }


    @Override
    public void onInventory(SelectBean selectBean) {
        if (selectBean.getCode().equals("200")) {
            productBean = new ProductBean();
            productBean.setProductId(id);
            productBean.setProductName(title);
            productBean.setProductNum(num);
            productBean.setUrl(pic);
            productBean.setProductPrice(price);
            LogUtils.json("priceaa" + productBean.getProductPrice());

            if (CacheConnectManager.getInstance().isConnect()) {
                mPresenter.addProduct(productBean);
            } else {
                Toast.makeText(this, "网络走丢了", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConect() {
        super.onConect();
        mPresenter.inventory(result);
        mPresenter.addProduct(productBean);
        Toast.makeText(this, "正在缓冲...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserChange(LoginBean loginBean) {
        super.onUserChange(loginBean);
        this.loginBean = loginBean;
    }


    private void showBezierAnim() {
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(layoutParams);
//        imageView.setImageResource(R.drawable.main_cart);
        Glide.with(this).load(pic).into(imageView);
        rootview.addView(imageView);

        int[] startLoacation = new int[2];
        startLoacation[0] = 300;
        startLoacation[1] = 300;
        int[] endLoacation = new int[2];
        endLoacation[0] = 800;
        endLoacation[1] = 1800;
        int[] controlLoacation = new int[2];
        controlLoacation[0] = 0;
        controlLoacation[1] = 500;
        Path path = new Path();
        path.moveTo(startLoacation[0], startLoacation[1]);

        path.quadTo(controlLoacation[0], controlLoacation[1], endLoacation[0], endLoacation[1]);
        PathMeasure pathMeasure = new PathMeasure(path, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
            valueAnimator.setDuration(2 * 1000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) animation.getAnimatedValue();
                    float[] nextLocation = new float[2];
                    pathMeasure.getPosTan(value, nextLocation, null);
                    imageView.setTranslationX(nextLocation[0]);
                    imageView.setTranslationY(nextLocation[1]);
                    float percent = value / pathMeasure.getLength();
                    imageView.setAlpha(1 - percent);
                }
            });
            valueAnimator.start();
        }

    }

    private void showAnima() {
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(layoutParams);
        Glide.with(this).load(pic).into(imageView);
        rootview.addView(imageView);
        //起点坐标
        int[] startLoacation = new int[2];
        startLoacation[0] = 800;
        startLoacation[1] = 300;
        //终点坐标
        int[] endLoacation = new int[2];
        shopcar.getLocationInWindow(endLoacation);
        //控制点
        int[] constrod = new int[2];
        constrod[0] = Math.abs(startLoacation[0] - endLoacation[0]);
        constrod[1] = Math.abs(startLoacation[1] - endLoacation[1]);

        int[] constrod1 = new int[2];
        constrod1[0] = constrod[0];
        constrod1[1] = constrod[1] - 100;
        Path path = new Path();
        path.moveTo(startLoacation[0], startLoacation[1]);
        //两个转折点
        path.cubicTo(constrod[0], constrod[1], constrod1[0], constrod1[1], endLoacation[0], endLoacation[1]);
        //一个转折点
//        path.quadTo(constrod[0],constrod[1],endLoacation[0],endLoacation[1]);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        //属性动画
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
            valueAnimator.setDuration(2000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float) animation.getAnimatedValue();
                    float[] floats = new float[2];
                    pathMeasure.getPosTan(animatedValue, floats, null);
                    imageView.setTranslationX(floats[0]);
                    imageView.setTranslationY(floats[1]);

                    float percent = animatedValue / pathMeasure.getLength();
                    imageView.setAlpha(1 - percent);


                }
            });

            valueAnimator.start();
        }


    }


}
