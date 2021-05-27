package com.example.shoppingmall1809.main.particulars;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
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

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.ShopmallGlide;
import com.example.framework.view.ToolBar;
import com.example.net.model.CategoryBean;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.addOneProduct.AddOnrProductPresenter;
import com.example.shoppingcar.addOneProduct.IAddOneProduct;
import com.example.shoppingmall1809.R;

import java.util.List;

public class ParticularsActivity extends BaseActivity<AddOnrProductPresenter> implements IAddOneProduct, ShoppingCarManager.IShoppingCar {
    private ToolBar toolbar;
    private ImageView particularsImg;
    private TextView particularsText;
    private TextView particularsPrice;
    private LinearLayout toShopCar;
    private TextView particularsAdd;
    private View tou;
    private TextView textView;

    private String productId;
    private String productName;
    private String productPrice;
    private String url;
    private int num = 1;
    private RelativeLayout particularsRe;
    private TextView particularsLabel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();

        ShoppingCarManager.getInstance().register(this);
        List<ShoppingTrolleyBean.ResultBean> result = ShoppingCarManager.getInstance().getResult();
        if (result != null) {
            showLabel(result);
        }

        int code = intent.getIntExtra("code", 0);
        if (code == 1) {
            HoemBean.ResultBean.RecommendInfoBean recommend = (HoemBean.ResultBean.RecommendInfoBean) intent.getSerializableExtra("recommend");
            setString(recommend.getProduct_id(), recommend.getName(), recommend.getCover_price(), recommend.getFigure());
        } else if (code == 2) {
            HoemBean.ResultBean.HotInfoBean hotInfo = (HoemBean.ResultBean.HotInfoBean) intent.getSerializableExtra("hotInfo");
            setString(hotInfo.getProduct_id(), hotInfo.getName(), hotInfo.getCover_price(), hotInfo.getFigure());
        } else if (code == 3) {
            HoemBean.ResultBean.SeckillInfoBean.ListBean seckill_info = (HoemBean.ResultBean.SeckillInfoBean.ListBean) intent.getSerializableExtra("seckill_info");
            setString(seckill_info.getProduct_id(), seckill_info.getName(), seckill_info.getCover_price(), seckill_info.getFigure());
        } else if (code == 4) {
            CategoryBean.ResultBean.HotProductListBean hotProductListBeans = (CategoryBean.ResultBean.HotProductListBean) intent.getSerializableExtra("hotProductListBeans");
            setString(hotProductListBeans.getProduct_id(), hotProductListBeans.getName(), hotProductListBeans.getCover_price(), hotProductListBeans.getFigure());
        }

        Glide.with(this).load(url).into(particularsImg);
        particularsText.setText(productName);
        particularsPrice.setText("￥" + productPrice);

        toShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ShopConstants.SHOP_CAR).navigation();
            }
        });

        particularsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginBean loginBean = ShopeUserManager.getInstance().getLoginBean();
                if (loginBean == null) {
                    CacheManager.getInstance().decideARoutPage = ShopConstants.AROUT_PARTICULARS;
                    ARouter.getInstance().build(ShopConstants.LOGIN_PATH).navigation();
                    return;
                }

                num = 1;

                PopupWindow popupWindow = new PopupWindow();

                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(true);

                View inflate = LayoutInflater.from(ParticularsActivity.this).inflate(R.layout.item_particulars_pop, null);
                popupWindow.setContentView(inflate);

                ImageView popImg = inflate.findViewById(R.id.pop_img);
                TextView popText = inflate.findViewById(R.id.pop_text);
                TextView popPrice = inflate.findViewById(R.id.pop_price);
                ImageView popSub = inflate.findViewById(R.id.pop_sub);
                ImageView popAdd = inflate.findViewById(R.id.pop_add);
                TextView popNum = inflate.findViewById(R.id.pop_num);
                TextView popCancel = inflate.findViewById(R.id.pop_cancel);
                TextView popConfirm = inflate.findViewById(R.id.pop_confirm);

                popNum.setText(num + "");
                ShopmallGlide.with(ParticularsActivity.this)
                        .load(url)
                        .into(popImg);
                popText.setText(productName);
                popPrice.setText(productPrice);

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

                        ShoppingTrolleyBean.ResultBean resultBean = ShoppingCarManager.getInstance().selectResultBean(productId);
                        if (resultBean != null) {
                            num += Integer.parseInt(resultBean.getProductNum());
                            httpPresenter.UpDateProductNum(productId, num + "", productName, url, productPrice);
                            popupWindow.dismiss();
                            return;
                        }
                        httpPresenter.CheckOneProductInventory(productId, num + "");
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(particularsAdd, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new AddOnrProductPresenter(ParticularsActivity.this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        particularsImg = (ImageView) findViewById(R.id.particulars_img);
        particularsText = (TextView) findViewById(R.id.particulars_text);
        particularsPrice = (TextView) findViewById(R.id.particulars_price);
        toShopCar = (LinearLayout) findViewById(R.id.toShopCar);

        particularsAdd = (TextView) findViewById(R.id.particulars_add);
        tou = (View) findViewById(R.id.tou);
        particularsRe = (RelativeLayout) findViewById(R.id.particulars_re);
        particularsLabel = (TextView) findViewById(R.id.particulars_label);
    }

    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        PopupWindow popupWindow = new PopupWindow();

        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        tou.setVisibility(View.VISIBLE);

        View inflate = LayoutInflater.from(this).inflate(R.layout.item_pop, null);
        popupWindow.setContentView(inflate);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tou.setVisibility(View.GONE);
            }
        });

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

    public void setString(String productId, String productName, String productPrice,
                          String url) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.url = Constants.BASE_URl_IMAGE + url;
    }

    //添加商品
    @Override
    public void onAddOneProduct(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            Toast.makeText(this, "添加购物车成功", Toast.LENGTH_SHORT).show();

            ShoppingCarManager.getInstance().insertResultBean(upDate());

            path();
        } else {
            Toast.makeText(this, "添加购物车失败", Toast.LENGTH_SHORT).show();
        }
    }

    //贝塞尔曲线
    private void path() {
        textView = new TextView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(50,50);
        textView.setLayoutParams(layoutParams);
        textView.setText("1");
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setBackgroundResource(R.drawable.text_background);
        textView.setGravity(Gravity.CENTER);

        particularsRe.addView(textView);

        int[] startLocation = new int[2];
        startLocation[0]=800;
        startLocation[1]=1680;

        int[] endLocation = new int[2];
        endLocation[0]=550;
        endLocation[1]=1680;

        int[] controlLocation=new int[2];
        controlLocation[0]=700;
        controlLocation[1]=500;

        Path path = new Path();
        path.moveTo(startLocation[0],startLocation[1]);
        path.quadTo(controlLocation[0],controlLocation[1],endLocation[0],endLocation[1]);

        //计算控件下一次要移动到的位置坐标
        PathMeasure pathMeasure = new PathMeasure(path, false);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());

        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();

                float[] nextLocation=new float[2];

                pathMeasure.getPosTan(value,nextLocation,null);
                textView.setTranslationX(nextLocation[0]);
                textView.setTranslationY(nextLocation[1]);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                particularsRe.removeView(textView);
                textView=null;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        valueAnimator.start();
    }

    //判断商品库存
    @Override
    public void onCheckOneProductInventory(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            if (Integer.parseInt(registerBean.getResult()) >= num) {
                httpPresenter.AddOneProduct(productId, num + "", productName, url, productPrice);
            } else {
                Toast.makeText(this, "该商品库存不足", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //修改商品数量
    @Override
    public void onUpDateProductNum(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            Toast.makeText(this, "商品数量修改成功", Toast.LENGTH_SHORT).show();

            ShoppingCarManager.getInstance().upDataResultBean(upDate());

            path();
        }
    }

    public ShoppingTrolleyBean.ResultBean upDate() {
        ShoppingTrolleyBean.ResultBean resultBean = new ShoppingTrolleyBean.ResultBean();

        resultBean.setProductId(productId);
        resultBean.setProductName(productName);
        Log.i("123", "upDate: " + num);
        resultBean.setProductNum(num + "");
        resultBean.setProductPrice(productPrice);
        resultBean.setProductSelected(false);
        resultBean.setUrl(url);

        return resultBean;
    }

    private void showLabel(List<ShoppingTrolleyBean.ResultBean> result) {
        if (result.size()<=0){
            particularsLabel.setVisibility(View.GONE);
        }else {
            particularsLabel.setVisibility(View.VISIBLE);
            particularsLabel.setText(result.size()+"");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShoppingCar(List<ShoppingTrolleyBean.ResultBean> result) {
        showLabel(result);
    }

    @Override
    public void onShoppingCarAdapter(List<ShoppingTrolleyBean.ResultBean> result) {

    }

    @Override
    public void destroy() {
        super.destroy();
        ShoppingCarManager.getInstance().unregister(this);
    }
}
