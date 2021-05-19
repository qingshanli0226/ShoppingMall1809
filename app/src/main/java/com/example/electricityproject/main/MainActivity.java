package com.example.electricityproject.main;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.bean.LogBean;
import com.example.common.call.BusinessARouter;
import com.example.common.call.BusinessUserManager;
import com.example.electricityproject.R;
<<<<<<< HEAD
import com.example.electricityproject.classify.ClassifyFragment;
import com.example.electricityproject.find.FindFragment;
import com.example.electricityproject.home.HomeFragment;
import com.example.electricityproject.person.PersonFragment;
import com.example.electricityproject.shopp.ShoppingFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
=======
import com.example.electricityproject.mainfragment.ClassifyFragment;
import com.example.electricityproject.mainfragment.FindFragment;
import com.example.electricityproject.mainfragment.HomeFragment;
import com.example.electricityproject.mainfragment.PersonFragment;
import com.example.electricityproject.mainfragment.ShoppingFragment;
>>>>>>> zzz

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private FindFragment findFragment;
    private ShoppingFragment shoppingFragment;
    private PersonFragment personFragment;
    private FragmentTransaction fragmentTransaction;
    private RadioGroup group;
    private RadioButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        initView();
=======
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        mainTab = (CommonTabLayout) findViewById(R.id.main_tab);
        mTabEntitys.add(new TabCus("首页",R.mipmap.main_home,R.mipmap.main_home_press));
        mTabEntitys.add(new TabCus("分类",R.mipmap.main_type,R.mipmap.main_type_press));
        mTabEntitys.add(new TabCus("发现",R.mipmap.main_community,R.mipmap.main_community_press));
        mTabEntitys.add(new TabCus("购物车",R.mipmap.main_cart,R.mipmap.main_cart_press));
        mTabEntitys.add(new TabCus("个人中心",R.mipmap.main_user,R.mipmap.main_user_press));

        list.add(new HomeFragment());
        list.add(new ClassifyFragment());
        list.add(new FindFragment());
        list.add(new ShoppingFragment());
        list.add(new PersonFragment());

        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), list);
        mainVp.setAdapter(fragAdapter);
        mainTab.setTabData(mTabEntitys);
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mainTab.setCurrentTab(position);
            }
>>>>>>> zx

        btnHome.setChecked(true);

        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        findFragment = new FindFragment();
        shoppingFragment = new ShoppingFragment();
        personFragment = new PersonFragment();

        BeginTransaction();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.btn_home:
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(classifyFragment);
                        fragmentTransaction.hide(findFragment);
                        fragmentTransaction.hide(shoppingFragment);
                        fragmentTransaction.hide(personFragment);
                        break;

                    case R.id.btn_kind:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.show(classifyFragment);
                        fragmentTransaction.hide(findFragment);
                        fragmentTransaction.hide(shoppingFragment);
                        fragmentTransaction.hide(personFragment);
                        LogBean logBean = BusinessUserManager.getInstance().getIsLog();

                        if (logBean == null) {
                            Toast.makeText(MainActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
                            BusinessARouter.getInstance().getUserManager().OpenLogActivity(MainActivity.this, null);
                        }
                        break;

                    case R.id.btn_find:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(classifyFragment);
                        fragmentTransaction.show(findFragment);
                        fragmentTransaction.hide(shoppingFragment);
                        fragmentTransaction.hide(personFragment);
                        LogBean logBeans = BusinessUserManager.getInstance().getIsLog();
                        if (logBeans == null) {
                            Toast.makeText(MainActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
                            BusinessARouter.getInstance().getUserManager().OpenLogActivity(MainActivity.this, null);
                        }
                        break;

                    case R.id.btn_buycar:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(classifyFragment);
                        fragmentTransaction.hide(findFragment);
                        fragmentTransaction.show(shoppingFragment);
                        fragmentTransaction.hide(personFragment);
                        LogBean logBean1 = BusinessUserManager.getInstance().getIsLog();

                        if (logBean1 == null) {
                            Toast.makeText(MainActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
                            BusinessARouter.getInstance().getUserManager().OpenLogActivity(MainActivity.this, null);
                        }
                        break;

                    case R.id.btn_person:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(classifyFragment);
                        fragmentTransaction.hide(findFragment);
                        fragmentTransaction.hide(shoppingFragment);
                        fragmentTransaction.show(personFragment);
                        LogBean logBean2 = BusinessUserManager.getInstance().getIsLog();

                        if (logBean2 == null) {
                            Toast.makeText(MainActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
                            BusinessARouter.getInstance().getUserManager().OpenLogActivity(MainActivity.this, null);
                        }
                        break;
                }
                fragmentTransaction.commit();

            }
        });


    }


    private void BeginTransaction() {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_lin, homeFragment);
        fragmentTransaction.add(R.id.main_lin, classifyFragment);
        fragmentTransaction.add(R.id.main_lin, findFragment);
        fragmentTransaction.add(R.id.main_lin, shoppingFragment);
        fragmentTransaction.add(R.id.main_lin, personFragment);

        fragmentTransaction.show(homeFragment);
        fragmentTransaction.hide(classifyFragment);
        fragmentTransaction.hide(findFragment);
        fragmentTransaction.hide(shoppingFragment);
        fragmentTransaction.hide(personFragment);
        fragmentTransaction.commit();

    }

    private void initView() {
        group = (RadioGroup) findViewById(R.id.group);
        btnHome = (RadioButton) findViewById(R.id.btn_home);
    }
}