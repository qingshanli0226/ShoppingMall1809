package com.example.shoppingmall1809.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commom.ShopConstants;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.ShopeUserManager;
import com.example.net.model.LoginBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.discover.DiscoverFragment;
import com.example.shoppingmall1809.main.home.HomeFragment;
import com.example.shoppingcar.shoppingtrolley.ShoppingViewTrolleyFragment;
import com.example.shoppingmall1809.main.type.TypeFragment;
import com.example.shoppingmall1809.main.user.UserFragment;

@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {

    private RadioButton actRadioCart;
    private RadioButton actRadioUser;
    private RadioButton actRadioHome;
    private RadioButton actRadioType;
    private RadioButton actRadioCommunity;
    private RadioButton actRadioRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        HomeFragment homeFragment = new HomeFragment();
        TypeFragment typeFragment = new TypeFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        ShoppingViewTrolleyFragment shoppingTrolleyFragment = new ShoppingViewTrolleyFragment();
        UserFragment userFragment = new UserFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.act_home_ll, homeFragment);
        fragmentTransaction.add(R.id.act_home_ll, typeFragment);
        fragmentTransaction.add(R.id.act_home_ll, discoverFragment);
        fragmentTransaction.add(R.id.act_home_ll, shoppingTrolleyFragment);
        fragmentTransaction.add(R.id.act_home_ll, userFragment);

        fragmentTransaction.hide(typeFragment);
        fragmentTransaction.hide(discoverFragment);
        fragmentTransaction.hide(shoppingTrolleyFragment);
        fragmentTransaction.hide(userFragment);
        fragmentTransaction.show(homeFragment);

        fragmentTransaction.commit();

        //添加初始化
        actRadioRecord = actRadioHome;
        RadioGroup radioGroup = findViewById(R.id.act_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (i) {
                    case R.id.act_radio_home:
                        //记录步骤
                        actRadioRecord = actRadioHome;
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.hide(discoverFragment);
                        fragmentTransaction.hide(shoppingTrolleyFragment);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_type:
                        //记录步骤
                        actRadioRecord = actRadioType;
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.show(typeFragment);
                        fragmentTransaction.hide(discoverFragment);
                        fragmentTransaction.hide(shoppingTrolleyFragment);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_community:
                        //记录步骤
                        actRadioRecord = actRadioCommunity;
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.show(discoverFragment);
                        fragmentTransaction.hide(shoppingTrolleyFragment);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_cart:
                        if (islogin()) {
                            ARouter.getInstance().build(ShopConstants.LOGIN_PATH).withString(ShopConstants.REGISTER_PATH, "").navigation();
                            CacheManager.getInstance().decideARoutPage = ShopConstants.AROUT_SHOPCAR;
                            actRadioRecord.setChecked(true);
                            return;
                        }
                        //记录步骤
                        actRadioRecord = actRadioCart;
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.hide(discoverFragment);
                        fragmentTransaction.show(shoppingTrolleyFragment);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_user:
                        //记录步骤
                        actRadioRecord = actRadioUser;
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.hide(discoverFragment);
                        fragmentTransaction.hide(shoppingTrolleyFragment);
                        fragmentTransaction.show(userFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    public boolean islogin() {
        LoginBean loginBean = ShopeUserManager.getInstance().getLoginBean();
        if (loginBean == null) {
            return true;
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        switch (CacheManager.getInstance().decideARoutPage) {
            case ShopConstants.AROUT_SHOPCAR:
                actRadioCart.setChecked(true);
                break;
            case ShopConstants.AROUT_USER_HAND:
                actRadioUser.setChecked(true);
                break;
            default:
                actRadioHome.setChecked(true);
                break;
        }
    }

    private void initView() {
        actRadioCart = (RadioButton) findViewById(R.id.act_radio_cart);
        actRadioUser = (RadioButton) findViewById(R.id.act_radio_user);
        actRadioHome = (RadioButton) findViewById(R.id.act_radio_home);
        actRadioType = (RadioButton) findViewById(R.id.act_radio_type);
        actRadioCommunity = (RadioButton) findViewById(R.id.act_radio_community);
    }
}