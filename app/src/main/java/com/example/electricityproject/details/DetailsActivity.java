package com.example.electricityproject.details;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.db.MessageDataBase;
import com.example.common.db.MessageTable;
import com.example.electricityproject.R;
import com.example.electricityproject.view.CircleView;
import com.example.framework.BaseActivity;
import com.example.glide.ShopGlide;
import com.example.manager.AllSelectManager;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessUserManager;
import com.example.manager.MessageManager;
import com.example.manager.SPMessageNum;
import com.example.manager.ShopCacheManger;
import com.example.view.ToolBar;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView,ToolBar.IToolbarListener,ShopCacheManger.iShopBeanChangeListener{
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
    private ScrollView detailsLin;
    private PopupWindow popupWindow;
    private int prod_num = 1;
    private RelativeLayout liner;
    private RelativeLayout relitive;
    private com.example.electricityproject.view.CircleView detailsBuyCarNum;
    private String isStorage="";


    @Override
    protected void initData() {
        intent = getIntent();
        name = intent.getStringExtra("name");
        img = intent.getStringExtra("img");
        price = intent.getStringExtra("price");
        productNum = prod_num;
        url = intent.getStringExtra("url");

        productId = intent.getStringExtra("productId");
        productPrice = intent.getStringExtra("productPrice");
        map.put("productId",productId);
        map.put("url",url);
        map.put("productPrice",price);
        map.put("productName",name);
        detailsWeb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(DetailsActivity.this,PlayVideoActivity.class));
                return true;
            }
        });

        //当img为不能null时为WebView添加图片网址
        if (img!=null){
            detailsWeb.loadUrl(Constants.BASE_URl_IMAGE+img);
            //在webView加载数据
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
                //点击pop外的地方，把pop隐藏
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
                if (BusinessUserManager.getInstance().getIsLog()!=null){
                    popupWindow = new PopupWindow();
                    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.setOutsideTouchable(true);
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
                    //数量加一
                    pop_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prod_num = prod_num+1;
                            num.setText(""+prod_num);
                        }
                    });
                    //数量减一
                    pop_sub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //判断数量不能小于0
                            if (prod_num < 0){
                                Toast.makeText(DetailsActivity.this, getResources().getString(R.string.details_num), Toast.LENGTH_SHORT).show();
                            }else {
                                prod_num = prod_num-1;
                                num.setText(""+prod_num);
                            }
                        }
                    });
                    //点击确定后
                    pop_conf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            linLin.setVisibility(View.VISIBLE);
                            map.put("productNum", String.valueOf(prod_num));
                            httpPresenter.checkOneProductInventory(productId, String.valueOf(prod_num));
                            ShopCacheManger.getInstance().addShopMessageNum(productId,name,productNum+"",url,productPrice,AllSelectManager.getInstance().isSelect());
                        }
                    });

                    popupWindow.setContentView(inflate);
                    popupWindow.showAsDropDown(detailsWeb,0,500 );

                }else {
                    Toast.makeText(DetailsActivity.this, getResources().getString(R.string.details_personNoLogin), Toast.LENGTH_SHORT).show();
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
        //监听购物车数量
        if (ShopCacheManger.getInstance().getShortBeanList()!=null){
            if (ShopCacheManger.getInstance().getShortBeanList().size()>0) {
                detailsBuyCarNum.setVisibility(View.VISIBLE);
                detailsBuyCarNum.setCurrentNum("" + ShopCacheManger.getInstance().getShortBeanList().size());
            }else {
                detailsBuyCarNum.setVisibility(View.GONE);
            }
        }

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
        relitive = (RelativeLayout) findViewById(R.id.relitive);
        detailsBuyCarNum = (CircleView) findViewById(R.id.details_buyCarNum);
        ShopCacheManger.getInstance().registerShopBeanChange(this);
        //读取内存的动态权限
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList =new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void showLoading() {

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
        if (isStorage.equals("")) {
            isStorage="succeed";
            //数据库数量加一
            SPMessageNum.getInstance().addShopNum(1);
            MessageDataBase.getInstance().payInsert(new MessageTable(null, "分享信息","分享成功", System.currentTimeMillis(), false));
            MessageManager.getInstance().addMessage(new MessageTable(null, "分享信息","分享成功", System.currentTimeMillis(), false));
        }

    }
    @Override
    public void onRightImgClick() {
        isStorage="";
        PopupWindow popupWindow = new PopupWindow(DetailsActivity.this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(200);
        View inflate = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_more,null);
        ImageView viewById = inflate.findViewById(R.id.more_share);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(DetailsActivity.this, Constants.BASE_URl_IMAGE+img);//网络图片
                new ShareAction(DetailsActivity.this).withMedia(image).withText("hello").setDisplayList(SHARE_MEDIA.QZONE,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(DetailsActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                if (isStorage.equals("")){
                                    isStorage="error";
                                    SPMessageNum.getInstance().addShopNum(1);
                                    MessageDataBase.getInstance().payInsert(new MessageTable(null,"分享信息","分享失败 错误信息:"+throwable.getMessage(),System.currentTimeMillis(),false));
                                    MessageManager.getInstance().addMessage(new MessageTable(null,"分享信息","分享失败 错误信息:"+throwable.getMessage(),System.currentTimeMillis(),false));
                                }
                            }
                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(toolbar,0,0);
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
            //ShopCacheManger.getInstance().requestShortProductData();
        }
    }

    @Override
    public void checkOneProductInventory(RegBean checkInventoryBean) {
        if (checkInventoryBean.getCode().equals("200")){
            String result = checkInventoryBean.getResult();
            if (!result.equals("0")){
                httpPresenter.postAddOneProduct(map);
            }
        }
    }

    //贝塞尔曲线
    private void showBezierAnim(String url){
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(params);
        Glide.with(DetailsActivity.this).load(url).into(imageView);
        relitive.addView(imageView);

        //起始点坐标
        int[] startLoa = new int[2];
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

    @Override
    public void destroy() {
        super.destroy();
        ShopCacheManger.getInstance().unregisterShopBeanChange(this);
    }

    //购物车数量发生改成时
    @Override
    public void OnShopBeanChange() {
        if (ShopCacheManger.getInstance().getShortBeanList()!=null){
            if (ShopCacheManger.getInstance().getShortBeanList().size()>0) {
                detailsBuyCarNum.setVisibility(View.VISIBLE);
                detailsBuyCarNum.setCurrentNum("" + ShopCacheManger.getInstance().getShortBeanList().size());
            }else {
                detailsBuyCarNum.setVisibility(View.GONE);
            }
        }
    }

}