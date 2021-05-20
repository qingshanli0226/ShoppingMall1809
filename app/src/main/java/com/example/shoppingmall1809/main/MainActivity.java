package com.example.shoppingmall1809.main;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.commom.ShopConstants;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.model.LoginBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.home.HomeFragment;
import com.example.shoppingmall1809.main.type.TypeFragment;
import com.example.shoppingmall1809.main.user.UserFragment;

@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment0 = new HomeFragment();
        TypeFragment typeFragment = new TypeFragment();
        HomeFragment homeFragment2 = new HomeFragment();
        HomeFragment homeFragment3 = new HomeFragment();
        UserFragment userFragment = new UserFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.act_home_ll, homeFragment0);
        fragmentTransaction.add(R.id.act_home_ll, typeFragment);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment2);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment3);
        fragmentTransaction.add(R.id.act_home_ll, userFragment);

        fragmentTransaction.hide(typeFragment);
        fragmentTransaction.hide(homeFragment2);
        fragmentTransaction.hide(homeFragment3);
        fragmentTransaction.hide(userFragment);
        fragmentTransaction.show(homeFragment0);

        fragmentTransaction.commit();


        RadioGroup radioGroup = findViewById(R.id.act_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (i){
                    case R.id.act_radio_home:
                        fragmentTransaction.show(homeFragment0);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_type:
                        if (login()) {
                            return;
                        }

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.show(typeFragment);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_community:
                        if (login()) {
                            return;
                        }

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.show(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_cart:
                        if (login()) {
                            return;
                        }

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.show(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_user:
                        if (login()) {
                            return;
                        }

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(typeFragment);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.show(userFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    public boolean login(){
        LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
        if (loginBean==null){
            ARouter.getInstance().build(ShopConstants.LOGIN_PATH).navigation();
            RadioButton radioButton = findViewById(R.id.act_radio_home);
            radioButton.setChecked(true);
            return true;
        }
        return false;
    }

}