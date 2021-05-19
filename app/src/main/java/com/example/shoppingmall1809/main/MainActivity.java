package com.example.shoppingmall1809.main;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.commom.ShopConstants;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.model.LoginBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.home.HomeFragment;
import com.example.shoppingmall1809.main.user.UserFragment;

@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment0 = new HomeFragment();
        HomeFragment homeFragment1 = new HomeFragment();
        HomeFragment homeFragment2 = new HomeFragment();
        HomeFragment homeFragment3 = new HomeFragment();
        UserFragment userFragment = new UserFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.act_home_ll, homeFragment0);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment1);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment2);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment3);
        fragmentTransaction.add(R.id.act_home_ll, userFragment);

        fragmentTransaction.hide(homeFragment1);
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
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_type:
                        login();

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.show(homeFragment1);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_community:
                        login();

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.show(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_cart:
                        login();

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.show(homeFragment3);
                        fragmentTransaction.hide(userFragment);
                        break;
                    case R.id.act_radio_user:
                        login();

                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        fragmentTransaction.show(userFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    public void login(){
        LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
        if (loginBean==null){
            ARouter.getInstance().build(ShopConstants.LOGIN_PATH).navigation();
        }
    }
}