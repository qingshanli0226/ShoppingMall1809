package com.example.shoppingmallsix.goods;

import android.animation.ValueAnimator;
import android.content.Intent;

import android.os.Handler;
import android.os.Message;

import android.graphics.Path;
import android.graphics.PathMeasure;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.ShopmallGlide;
import com.example.framework.manager.ShoppingCartMemoryDataManager;
import com.example.framework.view.ToolBar;

import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.CheckOneInventoryBean;
import com.example.net.bean.business.UpdateProductNumBean;
import com.example.net.bean.store.GoodAdapterBean;
import com.example.net.bean.user.LoginBean;
import com.example.shoppingmallsix.goods.adapter.GoodsAdapter;

import com.example.net.bean.business.GetShortcartProductsBean;

import com.example.net.constants.Constants;

import com.example.shoppingmallsix.R;


import com.example.shoppingmallsix.shopcar.ShoppingCarActivity;
import com.example.user.login.LoginActivity;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends BaseActivity<GoodsPresenter> implements IGoodsView, ShoppingCartMemoryDataManager.IShoppingDateChange {


    private com.example.framework.view.ToolBar toolbar;
    private RecyclerView moreRv;
    private List<GoodAdapterBean> list = new ArrayList<>();
    private GoodsAdapter goodsAdapter;
    private String name;
    private String figure;
    private String price;
    private LinearLayout shopcar;
    private android.widget.Button insertShopcar;
    private int num = 1;
    private String id;
    private GoodAdapterBean goodAdapterBean;
    private List<GetShortcartProductsBean.ResultBean> resultBeans = new ArrayList<>();
    private GetShortcartProductsBean.ResultBean resultBean = null;
    private int index;
    private TextView goodsSign;
    private RelativeLayout rootView;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void initPresenter() {
        httpPresenter = new GoodsPresenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        figure = intent.getStringExtra("figure");
        price = intent.getStringExtra("price");
        goodAdapterBean = new GoodAdapterBean(id, name, figure, price);
        list.add(goodAdapterBean);
        list.add(goodAdapterBean);
        goodsAdapter.notifyDataSetChanged();
        //注册接口
        ShoppingCartMemoryDataManager.getInstance().registerHoppingCartMemory(this);
        handler.sendEmptyMessageDelayed(1, 1000);

        LoginBean loginBean1 = CacheUserManager.getInstance().getLoginBean();
        if (loginBean1 != null) {
            List<GetShortcartProductsBean.ResultBean> result = ShoppingCartMemoryDataManager.getResultBean().getResult();
            if (result.size() != 0) {
                goodsSign.setVisibility(View.VISIBLE);
                goodsSign.setText(result.size() + "");
            }
        }
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        moreRv = (RecyclerView) findViewById(R.id.moreRv);
        shopcar = findViewById(R.id.shopcar);
        insertShopcar = findViewById(R.id.insertShopcar);
        goodsSign = (TextView) findViewById(R.id.goodsSign);
        rootView = findViewById(R.id.rootView);
        textView = findViewById(R.id.centerTv);
        imageView = findViewById(R.id.shopCar);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginBean loginBean2 = CacheUserManager.getInstance().getLoginBean();
                if (loginBean2 != null){
                    Intent intent = new Intent(GoodsActivity.this, ShoppingCarActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(GoodsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        goodsAdapter = new GoodsAdapter(list);
        moreRv.setAdapter(goodsAdapter);
        moreRv.setLayoutManager(new LinearLayoutManager(this));

        insertShopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginBean loginBean1 = CacheUserManager.getInstance().getLoginBean();
                if (loginBean1 != null) {
                    List<GetShortcartProductsBean.ResultBean> result = ShoppingCartMemoryDataManager.getResultBean().getResult();
                    if (result.size() != 0) {
                        goodsSign.setVisibility(View.VISIBLE);
                        goodsSign.setText(result.size() + "");
                    }
                    initPopupWindow();
                } else {
                    Toast.makeText(GoodsActivity.this, "请先登录账户", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GoodsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initPopupWindow() {
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        View inflate = LayoutInflater.from(GoodsActivity.this).inflate(R.layout.item_particulars_pop, null);

        ImageView popImg = inflate.findViewById(R.id.pop_img);
        TextView popText = inflate.findViewById(R.id.pop_text);
        TextView popPrice = inflate.findViewById(R.id.pop_price);
        ImageView popSub = inflate.findViewById(R.id.pop_sub);
        ImageView popAdd = inflate.findViewById(R.id.pop_add);
        TextView popNum = inflate.findViewById(R.id.pop_num);
        TextView popCancel = inflate.findViewById(R.id.pop_cancel);
        TextView popConfirm = inflate.findViewById(R.id.pop_confirm);
        popupWindow.setContentView(inflate);

        popNum.setText(num + "");
        ShopmallGlide.with(GoodsActivity.this)
                .load(Constants.BASE_URl_IMAGE + figure)
                .into(popImg);
        popText.setText(name);
        popPrice.setText(price);

        popAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                popNum.setText(num + "");
            }
        });
        popSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num--;
                if (num <= 0) {
                    num = 1;
                }
                popNum.setText(num + "");
            }
        });
        popCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpPresenter.checkInventory(id, num + "");
                popupWindow.dismiss();
                showBeisaierAnim();
            }
        });
        popupWindow.showAsDropDown(toolbar, 0, 900, Gravity.BOTTOM);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods;
    }


    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        PopupWindow popupWindow = new PopupWindow();

        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);


        View inflate = LayoutInflater.from(this).inflate(R.layout.item_pop, null);
        ImageView share = inflate.findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //分享

                UMImage image = new UMImage(GoodsActivity.this,Constants.BASE_URl_IMAGE +figure );//网络图片

                new ShareAction(GoodsActivity.this)
                        .withMedia(image)
                        .withText("hello").setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(GoodsActivity.this, "throwable:" + throwable, Toast.LENGTH_SHORT).show() ;
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();
            }
        });

        ImageView video = inflate.findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //直播
                startActivity(new Intent(GoodsActivity.this,VideoActivity.class));
            }
        });
        popupWindow.setContentView(inflate);

        LinearLayout viewById = inflate.findViewById(R.id.toMain);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(toolbar);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddCart(AddOneProductBean productBean) {
        if (productBean.getCode().equals("200")) {
            Toast.makeText(this, getString(R.string.add), Toast.LENGTH_SHORT).show();
            GetShortcartProductsBean.ResultBean resultBean = new GetShortcartProductsBean.ResultBean();
            resultBean.setProductId(id);
            resultBean.setUrl(figure);
            resultBean.setProductName(name);
            resultBean.setProductNum(num + "");
            resultBean.setProductPrice(price);
            resultBeans.add(resultBean);
            ShoppingCartMemoryDataManager.setResultBean(resultBeans);
        } else {
            Toast.makeText(this, getString(R.string.dda), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckInventory(CheckOneInventoryBean bean) {
        if (bean.getCode().equals("200")) {
            Toast.makeText(this, "检查库存有", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < resultBeans.size(); i++) {
                if (name.equals(resultBeans.get(i).getProductName())) {
                    resultBean = resultBeans.get(i);
                    index = i;
                }
            }
            if (resultBean != null) {
                httpPresenter.updateProduceNum(id, (Integer.parseInt(resultBean.getProductNum()) + num) + "", name, figure, price);
            } else {
                httpPresenter.addOneProduct(id, num + "", name, figure, price);
            }

        } else {
            Toast.makeText(this, "检查库存没有", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateProductNum(UpdateProductNumBean updateProductNumBean) {
        if (updateProductNumBean.getCode().equals("200")) {
            Toast.makeText(this, "更新", Toast.LENGTH_SHORT).show();
            //更新
            resultBeans.get(index).setProductNum("" + (Integer.parseInt(resultBean.getProductNum()) + num));
            ShoppingCartMemoryDataManager.setResultBean(resultBeans);
        } else {
            Toast.makeText(this, "没有更新", Toast.LENGTH_SHORT).show();
        }
    }


    //子线程获取数据 实时刷新
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            GetShortcartProductsBean resultBean = ShoppingCartMemoryDataManager.getResultBean();
            if (resultBean != null) {
                List<GetShortcartProductsBean.ResultBean> result = resultBean.getResult();
                if (result != null && result.size() != 0) {
                    //获取内存数据
                    resultBeans.addAll(result);
                    goodsSign.setVisibility(View.VISIBLE);
                    goodsSign.setText(result.size() + "");
                }
            } else {
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    public void onShoppingDataChange(List<GetShortcartProductsBean.ResultBean> resultBeanList) {
        if (resultBeanList.size() != 0) {
            //红点刷新数量
            goodsSign.setVisibility(View.VISIBLE);
            goodsSign.setText(resultBeanList.size() + "");
        } else {
            goodsSign.setVisibility(View.GONE);
        }
    }

    private void showBeisaierAnim() {
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.sign);
        rootView.addView(imageView);

        int[] startLoacation = new int[2];//起始点坐标
        /**startLoacation[0] = 300;
         startLoacation[1] = 0;*/
        textView.getLocationInWindow(startLoacation);

        int[] endLocation = new int[2];//终点坐标
        endLocation[0] = 600;
        endLocation[1] = 1000;
        int[] controlLocation = new int[2];//控制点坐标
        controlLocation[0] = 0;
        controlLocation[1] = 300;
        int[] controlLocation2 = new int[2];//控制点坐标
        controlLocation2[0] = 500;
        controlLocation2[1] = 500;

        Path path = new Path();
        path.moveTo(startLoacation[0], startLoacation[1]);
        path.cubicTo(controlLocation[0], controlLocation[1], controlLocation2[0], controlLocation2[1], endLocation[0], endLocation[1]);
        PathMeasure pathMeasure = new PathMeasure(path, false);//是计算控件下一次要移动到的位置坐标

        //使用属性动画
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(3 * 1000);
        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();

                float[] nextLocation = new float[2];
                pathMeasure.getPosTan(value, nextLocation, null);
                imageView.setTranslationX(nextLocation[0]);//让控件移动到下一个X，Y坐标处
                imageView.setTranslationY(nextLocation[1]);

                float percent = value / pathMeasure.getLength();
                imageView.setAlpha(1 - percent);
                imageView.setScaleX(2 * percent);
                imageView.setScaleY(2 * percent);
            }
        });

        valueAnimator.start();

    }

    @Override
    public void onDisconnected() {
        loadingPage.showError("网络错误");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //销毁接口
        ShoppingCartMemoryDataManager.getInstance().unHoppingCartMemory(this);
    }


}
