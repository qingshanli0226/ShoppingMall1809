package com.shoppingmall.detail;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.fiance.user.AutoService;
import com.finance.detail.R;
import com.shoppingmall.detail.bean.ProductGoodBean;
import com.shoppingmall.detail.greendao.GoodsTable;
import com.shoppingmall.detail.greendao.TableManager;
import com.shoppingmall.detail.messagedao.DaoSession;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.glide.ShopMallGlide;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.SelectBean;
import com.shoppingmall.net.bean.ShopCarBean;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.List;

@Route(path = Constants.TO_DETAIL_ACTIVITY)
public class DetailActivity extends BaseActivity<DetailPresenter> implements IDetailView {

    private ImageView detailBack;
    private ImageView detailMenu;
    private ImageView detailImg;
    private TextView detailTitle;
    private TextView detailPrice;
    private PopupWindow popupWindow;
    private PopupWindow popupWindow2;
    private ProductGoodBean productGoodBean;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView toShopMallCarFragment;
    private android.widget.Button addShopMallCar;
    private LoginBean loginBean;
    private DaoSession daoSession;
    private TextView popRemoveNum;
    private TextView popNum;
    private TextView popAddNum;
    private Button no;
    private Button yes;
    private int GoodsNum=1;
    private RelativeLayout relat;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {

        detailBack = (ImageView) findViewById(R.id.detailBack);
        detailMenu = (ImageView) findViewById(R.id.detailMenu);
        detailImg = (ImageView) findViewById(R.id.detailImg);
        detailTitle = (TextView) findViewById(R.id.detailTitle);
        detailPrice = (TextView) findViewById(R.id.detailPrice);
        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        toShopMallCarFragment = (TextView) findViewById(R.id.toShopMallCarFragment);
        addShopMallCar = (Button) findViewById(R.id.addShopMallCar);
        relat = findViewById(R.id.relat);
    }
    private void showBezier(){
        int [] location=new int[2];//获取起点控件位置
        detailImg.getLocationOnScreen(location);


        int [] location1=new int[2];//获取终点控件位置
        toShopMallCarFragment.getLocationOnScreen(location1);

        ImageView imageView = new ImageView(this);//创建图片
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(layoutParams);//设置图片长宽
        ShopMallGlide.with(this).load(Constants.IMG_HTTPS+productGoodBean.getFigure()).into(imageView);
        relat.addView(imageView);//加入当前布局

        int[] startLoacation = new int[2];//开始
        startLoacation[0] =location[0]+300;
        startLoacation[1] =location[1]+300;
        int[] endLoacation = new int[2];//结束
        endLoacation[0] =location1[0];
        endLoacation[1] =location1[1];
        int[] controlLoacation = new int[2];//过程
        controlLoacation[0]=0;
        controlLoacation[1]=0;
        Path path = new Path();
        path.moveTo(startLoacation[0],startLoacation[1]);//开始位置

        path.quadTo(controlLoacation[0],controlLoacation[1],endLoacation[0],endLoacation[1]);//过程和结束位置
        PathMeasure pathMeasure = new PathMeasure(path, false);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(2*1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float)animation.getAnimatedValue();//获取动画进度
                float[] nextLocation = new float[2];
                pathMeasure.getPosTan(value,nextLocation,null);
                imageView.setTranslationX(nextLocation[0]);
                imageView.setTranslationY(nextLocation[1]);
                float percent=value/pathMeasure.getLength();
                imageView.setAlpha(1-percent);//渐显
            }
        });
        valueAnimator.start();

    }

    @Override
    public void initPresenter() {
        httpPresenter = new DetailPresenter(this);
    }

    @Override
    public void initData() {
        loginBean = ShopMallUserManager.getInstance().getLoginBean();

        daoSession = TableManager.getInstance().getDaoSession();

        //退出当前页面
        detailBack.setOnClickListener(v->{
            backActivity();
        });
        //打开popWindow
        detailMenu.setOnClickListener(v->{
            openPopWindow();
        });
        //获取数据
        Intent intent = getIntent();
        Serializable goods = intent.getSerializableExtra("goods");
        String type = intent.getStringExtra("type");
        if (goods!=null&&type!=null){
            switch (type){
                case "seckill":
                    HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = (HomeBean.ResultBean.SeckillInfoBean.ListBean) goods;
                    productGoodBean = new ProductGoodBean(listBean.getName(), listBean.getCover_price(), listBean.getFigure(), listBean.getProduct_id());
                    break;
                case "recommend":
                    HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = (HomeBean.ResultBean.RecommendInfoBean) goods;
                    productGoodBean = new ProductGoodBean(recommendInfoBean.getName(), recommendInfoBean.getCover_price(), recommendInfoBean.getFigure(), recommendInfoBean.getProduct_id());
                    break;
                case "hot":
                    HomeBean.ResultBean.HotInfoBean hotInfoBean = (HomeBean.ResultBean.HotInfoBean) goods;
                    productGoodBean = new ProductGoodBean(hotInfoBean.getName(), hotInfoBean.getCover_price(), hotInfoBean.getFigure(), hotInfoBean.getProduct_id());
                    break;
            }
            setUI(productGoodBean);
        }

        //跳转购物车
        toShopMallCarFragment.setOnClickListener(v->{
            if (loginBean==null){
                ARouter.getInstance().build(Constants.TO_USER_ACTIVITY).withInt("toDetail",1).navigation();
                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            }else {
                ARouter.getInstance().build(Constants.TO_MAIN_ACTIVITY).withInt("shopMallPosition",3).navigation();
                finish();
            }
        });

        //加入购物车
        addShopMallCar.setOnClickListener(v->{
            if (loginBean==null){
                ARouter.getInstance().build(Constants.TO_USER_ACTIVITY).withInt("addDetail",1).navigation();
                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            }else {
                setPopupWindow();
            }
        });

    }
    private ProductBean productBean;
    public void setPopupWindow(){
        popupWindow = new PopupWindow();
        View inflate = LayoutInflater.from(DetailActivity.this).inflate(R.layout.detail_add_shop_mall_car_pop_wiondow_layout, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(RadioGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(inflate,Gravity.BOTTOM,0,0);

        popRemoveNum = (TextView) inflate.findViewById(R.id.popRemoveNum);
        popNum = (TextView) inflate.findViewById(R.id.popNum);
        int trim = Integer.parseInt(popNum.getText().toString().trim());
        popAddNum = (TextView) inflate.findViewById(R.id.popAddNum);
        no = (Button) inflate.findViewById(R.id.no);
        yes = (Button) inflate.findViewById(R.id.yes);
        int text = Integer.parseInt(popNum.getText().toString().trim());
        GoodsNum = text;
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                LogUtils.json(productGoodBean);
                ShopCarBean.ResultBean result = new ShopCarBean.ResultBean();
                result.setProductId(productGoodBean.getProduct_id());
                result.setProductNum(""+productGoodBean.getNumber());
                httpPresenter.checkProduct(result);
                showBezier();

                //数据库存储
                List<GoodsTable> goodsTables = daoSession.loadAll(GoodsTable.class);
                for (GoodsTable goodsTable : goodsTables) {
                    if ( goodsTable.getGoodName()==productGoodBean.getName()){

                    }else {
                        daoSession.insert(new GoodsTable(null,productGoodBean.getFigure(),
                                productGoodBean.getName(),productGoodBean.getCover_price(),
                                trim));
                        Toast.makeText(DetailActivity.this, "加入了一条数据", Toast.LENGTH_SHORT).show();
                    }
                }
                //服务端存储
                productBean = new ProductBean();
                productBean.setProductId(productGoodBean.getProduct_id());
                productBean.setProductNum(productGoodBean.getNumber());
                productBean.setProductName(productGoodBean.getName());
                productBean.setUrl(productGoodBean.getFigure());
                productBean.setProductPrice(productGoodBean.getCover_price());
                httpPresenter.addProduct(productBean);
            }
        });
        popRemoveNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GoodsNum<=1){
                    Toast.makeText(DetailActivity.this, "数量已经为一，不能在减少了", Toast.LENGTH_SHORT).show();
                }else {
                    GoodsNum--;
                }
                popNum.setText(""+GoodsNum);
                productGoodBean.setNumber(GoodsNum);
            }
        });
        popAddNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsNum++;
                popNum.setText(""+GoodsNum);
                productGoodBean.setNumber(GoodsNum);
            }
        });
    }

    @Override
    public void addProduct(SelectBean selectBean) {
        if (selectBean.getCode().equals("200")){
            ShopCarBean.ResultBean resultBean = new ShopCarBean.ResultBean();
            resultBean.setProductId(productGoodBean.getProduct_id());
            resultBean.setProductNum(String.valueOf(productGoodBean.getNumber()));
            resultBean.setProductPrice(productGoodBean.getCover_price());
            resultBean.setUrl(productGoodBean.getFigure());
            resultBean.setProductName(productGoodBean.getName());

            CacheShopManager.getInstance().addData(resultBean);

            Toast.makeText(this, ""+selectBean.getResult(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, ""+selectBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkProduct(SelectBean selectBean) {
        if (selectBean.getCode().equals("200")){
//            httpPresenter.addProduct(productBean);
        }
    }

    private void setUI(ProductGoodBean productGoodBean) {
        //加载数据
        ShopMallGlide.with(this).load(Constants.IMG_HTTPS+productGoodBean.getFigure()).into(detailImg);
        detailTitle.setText(""+productGoodBean.getName());
        detailPrice.setText(""+productGoodBean.getCover_price());
    }

    //打开popWindow
    private void openPopWindow() {
        detailMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow2 = new PopupWindow();
                View inflate = LayoutInflater.from(DetailActivity.this).inflate(R.layout.detail_pop_wiondow_layout, null);
                popupWindow2.setContentView(inflate);
                popupWindow2.setWidth(RadioGroup.LayoutParams.MATCH_PARENT);
                popupWindow2.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
                popupWindow2.setOutsideTouchable(true);
                popupWindow2.showAtLocation(inflate,Gravity.TOP,0,190);
            }
        });
    }
    //退出当前Activity
    private void backActivity(){
        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}