package com.shoppingmall.detail;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fiance.user.AutoService;
import com.finance.detail.R;
import com.shoppingmall.detail.bean.ProductGoodBean;
import com.shoppingmall.detail.greendao.GoodsTable;
import com.shoppingmall.detail.greendao.TableManager;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.glide.ShopMallGlide;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.sp.SpUtil;
import com.yoho.greendao.gen.DaoSession;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

@Route(path = Constants.TO_DETAIL_ACTIVITY)
public class DetailActivity extends BaseActivity {

    private ImageView detailBack;
    private ImageView detailMenu;
    private ImageView detailImg;
    private TextView detailTitle;
    private TextView detailPrice;
    private PopupWindow popupWindow;
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
    }

    @Override
    public void initPresenter() {

    }

    @Subscribe
    public void getEvenBus(String str){
        if (str.equals("startAutoService")){
            startAutoService();
        }
    }

    @Override
    public void initData() {
        startAutoService();

        daoSession = TableManager.getInstance().getDaoSession();

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
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
                ARouter.getInstance().build(Constants.TO_USER_ACTIVITY).withString("addDetail","addDetail").navigation();
                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            }else {
                PopupWindow popupWindow = new PopupWindow();
                View inflate = LayoutInflater.from(DetailActivity.this).inflate(R.layout.detail_add_shop_mall_car_pop_wiondow_layout, null);
                popupWindow.setContentView(inflate);
                popupWindow.setWidth(RadioGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(inflate,Gravity.BOTTOM,0,0);

                popRemoveNum = (TextView) inflate.findViewById(R.id.popRemoveNum);
                popNum = (TextView) inflate.findViewById(R.id.popNum);
                popAddNum = (TextView) inflate.findViewById(R.id.popAddNum);
                no = (Button) inflate.findViewById(R.id.no);
                yes = (Button) inflate.findViewById(R.id.yes);

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                popRemoveNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                popAddNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
//                daoSession.insert(new GoodsTable(null,productGoodBean.getFigure(),
//                        productGoodBean.getName(),productGoodBean.getCover_price(),
//                        productGoodBean.getNumber()));
//                Toast.makeText(this, "加入了一条数据", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void startAutoService() {
        Intent intent1 = new Intent(DetailActivity.this, AutoService.class);
        startService(intent1);
        loginBean = ShopMallUserManager.getInstance().getLoginBean();
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
                popupWindow = new PopupWindow();
                View inflate = LayoutInflater.from(DetailActivity.this).inflate(R.layout.detail_pop_wiondow_layout, null);
                popupWindow.setContentView(inflate);
                popupWindow.setWidth(RadioGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(inflate,Gravity.TOP,0,190);
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
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
//        if (popupWindow.isShowing()||popupWindow!=null){
//            popupWindow.dismiss();
//        }
    }
}