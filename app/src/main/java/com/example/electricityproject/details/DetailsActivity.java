package com.example.electricityproject.details;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.glide.ShopGlide;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView{
    private ToolBar toolbar;
    private WebView detailsWeb;
    private TextView detailsName;
    private TextView detailsPrice;
    private LinearLayout addShop;
    private Button btnAdd;
    private String name;
    private String img;
    private String price;
    private Map<String,String> map = new HashMap<>();
    private Map<String,String> map2 = new HashMap<>();
    private String productId;
    private String productPrice;
    private int productNum;
    private String url;
    private Intent intent;
    private List<ShortcartProductBean.ResultBean> resultBeans = new ArrayList<>();
    private ImageView buyCar;
    private LinearLayout linLin;
    private LinearLayout detailsLin;
    private PopupWindow popupWindow;
    private int prod_num = 1;
    private RelativeLayout liner;

    @Override
    protected void initData() {

        intent = getIntent();
        name = intent.getStringExtra("name");
        img = intent.getStringExtra("img");
        price = intent.getStringExtra("price");
        productNum = prod_num;
        url = "http://www.baidu.com";


        productId = intent.getStringExtra("productId");
        productPrice = intent.getStringExtra("productPrice");
        map.put("productId",productId);
        map2.put("productId",productId);
        map.put("url",url);
        map.put("productPrice",price);
        map.put("productName",name);


        if (img!=null){
            detailsWeb.loadUrl(Constants.BASE_URl_IMAGE+img);
            detailsWeb.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    detailsWeb.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);

                }
            });
        }
        if (name!=null){
            detailsName.setText(name+"");
        }
        if (price!=null){
            detailsPrice.setText("￥"+price);
        }

        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightImgClick() {
                PopupWindow popupWindow = new PopupWindow(DetailsActivity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(200);
                View inflate = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_more,null);
                popupWindow.setContentView(inflate);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(toolbar,0,0);
            }

            @Override
            public void onRightTvClick() {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogBean isLog = BusinessUserManager.getInstance().getIsLog();
                if (isLog!=null){
                    linLin.setVisibility(View.GONE);
                    popupWindow = new PopupWindow();
                    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    View inflate = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.pop_products, null);
                    ImageView image = inflate.findViewById(R.id.pop_image);
                    TextView names = inflate.findViewById(R.id.pop_name);
                    TextView num = inflate.findViewById(R.id.pop_num);
                    TextView price = inflate.findViewById(R.id.pop_price);
                    ImageView pop_add = inflate.findViewById(R.id.pop_add);
                    ImageView pop_sub = inflate.findViewById(R.id.pop_sub);
                    Button pop_conf = inflate.findViewById(R.id.pop_conf);
                    ShopGlide.getInstance().with(DetailsActivity.this).load(Constants.BASE_URl_IMAGE+img).init(image);
                    names.setText(""+name);
                    num.setText("1");
                    price.setText("￥"+productPrice);

                    pop_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prod_num = prod_num+1;
                            num.setText(""+prod_num);
                        }
                    });

                    pop_sub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (prod_num < 0){
                                Toast.makeText(DetailsActivity.this, "库存不可为0", Toast.LENGTH_SHORT).show();
                            }else {
                                prod_num = prod_num-1;
                                num.setText(""+prod_num);
                            }
                        }
                    });

                    pop_conf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            linLin.setVisibility(View.VISIBLE);
                            map.put("productNum", String.valueOf(prod_num));
                            map2.put("productNum", String.valueOf(prod_num));

                            httpPresenter.postAddOneProduct(map);
//                            httpPresenter.checkOneProductInventory(map2);
                        }
                    });

                    popupWindow.setContentView(inflate);
                    popupWindow.showAsDropDown(btnAdd,0,0);
                }else {
                    Toast.makeText(DetailsActivity.this, "当前用户未登录", Toast.LENGTH_SHORT).show();
                    BusinessARouter.getInstance().getUserManager().OpenLogActivity(DetailsActivity.this,null);
                }

            }
        });

        buyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("notify","go_buyCar");
                BusinessARouter.getInstance().getAppManager().OpenMainActivity(DetailsActivity.this,bundle);
            }
        });

        detailsLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow!=null){
                    popupWindow.dismiss();
                    linLin.setVisibility(View.VISIBLE);
                }
            }
        });

    }



    @Override
    protected void initPresenter() {
        httpPresenter = new DetailsPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        detailsWeb = (WebView) findViewById(R.id.details_web);
        detailsName = (TextView) findViewById(R.id.details_name);
        detailsPrice = (TextView) findViewById(R.id.details_price);
        addShop = (LinearLayout) findViewById(R.id.add_shop);
        btnAdd = (Button) findViewById(R.id.btn_add);
        buyCar = (ImageView) findViewById(R.id.buy_car);
        linLin = (LinearLayout) findViewById(R.id.lin_lin);
        detailsLin = (LinearLayout) findViewById(R.id.details_lin);
        liner = (RelativeLayout) findViewById(R.id.relative);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

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
    public void getAddOneProduct(AddOneProductBean addOneProductBean) {
        if (addOneProductBean.getCode().equals("200")){
            showBezierAnim(Constants.BASE_URl_IMAGE+img);
            String result = addOneProductBean.getResult();
            Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void checkOneProductInventory(RegBean checkInventoryBean) {
        if (checkInventoryBean.getCode().equals("200")){
            Toast.makeText(this, "1231232", Toast.LENGTH_SHORT).show();
        }
    }

    //贝塞尔曲线
    private void showBezierAnim(String url){
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(params);
//        imageView.setImageDrawable(getDrawable(R.drawable.aaa));
        Glide.with(DetailsActivity.this).load(url).into(imageView);
        liner.addView(imageView);

        //起始点坐标
        int[] startLoa = new int[2];
//        startLoa[0] = 600;
//        startLoa[1] = -1100;

        detailsWeb.getLocationInWindow(startLoa);

        //终点坐标
        int[] endLoa = new int[2];
        endLoa[0] = 600;
        endLoa[1] = 600;
        buyCar.getLocationInWindow(endLoa);

        //控制点坐标
        int[] controlLoa = new int[2];
        controlLoa[0] = 200;
        controlLoa[1] = 500;

        Path path = new Path();
        path.moveTo(startLoa[0],startLoa[1]);
        path.quadTo(controlLoa[0],controlLoa[1],endLoa[0],endLoa[1]);
        PathMeasure pathMeasure = new PathMeasure(path,false);

        //使用属性动画
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,pathMeasure.getLength());
        valueAnimator.setDuration(3 * 1000);
        valueAnimator.setInterpolator(new LinearInterpolator());

        //动画更新监听,监听动画的绘制过程
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float[] nextLocation = new float[2];
                pathMeasure.getPosTan(value,nextLocation,null);
                imageView.setTranslationX(nextLocation[0]);
                imageView.setTranslationY(nextLocation[1]);

                float v = value / pathMeasure.getLength();
                imageView.setAlpha(1-v);

                imageView.setScaleX(2*v);
                imageView.setScaleY(2*v);

            }
        });

        valueAnimator.start();


    }


}