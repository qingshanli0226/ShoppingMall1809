package com.example.shoppingmall1809.main;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.home.HomeFragment;

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

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.act_home_ll, homeFragment0);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment1);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment2);
        fragmentTransaction.add(R.id.act_home_ll, homeFragment3);

        fragmentTransaction.hide(homeFragment0);
        fragmentTransaction.hide(homeFragment1);
        fragmentTransaction.hide(homeFragment2);
        fragmentTransaction.show(homeFragment3);

        fragmentTransaction.commit();


        RadioGroup radioGroup = findViewById(R.id.act_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (i){
                    case R.id.act_radio_home:
                        LogUtils.e("0");
                        fragmentTransaction.show(homeFragment0);
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        break;
                    case R.id.act_radio_type:
                        LogUtils.e("1");
                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.show(homeFragment1);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        break;
                    case R.id.act_radio_community:
                        LogUtils.e("2");
                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.show(homeFragment2);
                        fragmentTransaction.hide(homeFragment3);
                        break;
                    case R.id.act_radio_cart:
                        LogUtils.e("3");
                        fragmentTransaction.hide(homeFragment0);
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(homeFragment2);
                        fragmentTransaction.show(homeFragment3);
                        break;
                    case R.id.act_radio_user:
                        break;
                }
                fragmentTransaction.commit();
            }
        });

    }
}