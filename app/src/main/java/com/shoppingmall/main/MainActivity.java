package com.shoppingmall.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.view.CircleView;
import com.shoppingmall.main.bean.CustomBean;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.main.adapter.ComAdapter;
import com.shoppingmall.main.find.FindFragment;
import com.shoppingmall.main.home.HomeFragment;
import com.shoppingmall.main.mine.MineFragment;
import com.shoppingmall.main.shopcar.ShopCarFragment;
import com.shoppingmall.main.sort.SortFragment;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.ShopCarBean;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constants.TO_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements ShopMallUserManager.IUserLoginChanged  {

    private int VpPosition;
    private ComAdapter commonAdapter;
    private ViewPager mainVp;
    private CommonTabLayout mainCommon;
    private com.shoppingmall.framework.view.CircleView mainCircle;
    private LoginBean loginBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainVp = (ViewPager) findViewById(R.id.mainVp);
        mainCommon = (CommonTabLayout) findViewById(R.id.mainCommon);
        mainCircle = (CircleView) findViewById(R.id.mainCircle);
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);


        loginBean = ShopMallUserManager.getInstance().getLoginBean();
        LogUtils.json(loginBean);
        if(loginBean != null){
            if(CacheShopManager.getInstance().getCarts().size() > 0){
                mainCircle.setText(CacheShopManager.getInstance().getCarts().size()+"");
                mainCircle.setVisibility(View.VISIBLE);
            }else {
                mainCircle.setVisibility(View.GONE);
            }
        }else {
            mainCircle.setVisibility(View.GONE);
        }

        Intent intent = getIntent();
        int shopMallPosition = intent.getIntExtra("shopMallPosition", -1);
        VpPosition = intent.getIntExtra("position",-1);

        int intExtra = intent.getIntExtra("4", -1);
        if (intExtra!=-1){
            mainVp.setCurrentItem(intExtra);
        }
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SortFragment());
        list.add(new FindFragment());
        list.add(new ShopCarFragment());
        list.add(new MineFragment());
        commonAdapter = new ComAdapter(getSupportFragmentManager(),list);
        mainVp.setAdapter(commonAdapter);

        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_home),R.drawable.main_home_press,R.drawable.main_home));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_sort),R.drawable.main_type_press,R.drawable.main_type));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_find),R.drawable.main_community_press,R.drawable.main_community));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_shopCar),R.drawable.main_cart_press,R.drawable.main_cart));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_mine),R.drawable.main_user_press,R.drawable.main_user));
        mainCommon.setTabData(customTabEntities);

        mainCommon.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (ShopMallUserManager.getInstance().getLoginBean()==null&&position!=0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getString(R.string.Tips));
                    builder.setMessage(getString(R.string.TipsMessage));
                    builder.setPositiveButton(getString(R.string.welcomeActivity_alert_button_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ARouter.getInstance().build(Constants.TO_USER_ACTIVITY).withString("toShopCar","toShopCar").navigation();
                        }
                    });
                    builder.show();
                }else {
                    mainVp.setCurrentItem(position);
                }
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
        mainVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                VpPosition = position;
                mainCommon.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (VpPosition!=-1){
            mainVp.setCurrentItem(VpPosition);
        }
        if (shopMallPosition!=-1){
            mainVp.setCurrentItem(shopMallPosition);
        }
    }

    @Subscribe
    public void ShopCarNum(String string){
        if (string.equals("ShopCarNum")){
            if(CacheShopManager.getInstance().getCarts().size() > 0){
                mainCircle.setText(CacheShopManager.getInstance().getCarts().size()+"");
                mainCircle.setVisibility(View.VISIBLE);
            }else {
                mainCircle.setVisibility(View.GONE);
            }
        }

    }

    //双击退出
    private long i;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - i >2000){
                Toast.makeText(this, getString(R.string.mianActivity_exit_the_program_toast), Toast.LENGTH_SHORT).show();
                i =System.currentTimeMillis();
                return true;
            }else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLoginChanged(LoginBean loginBean) {
        Toast.makeText(this, ""+getString(R.string.login_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}