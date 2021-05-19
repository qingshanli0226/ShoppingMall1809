package com.example.electricityproject.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.electricityproject.R;
import com.example.electricityproject.classify.ClassifyFragment;
import com.example.electricityproject.find.FindFragment;
import com.example.electricityproject.home.HomeFragment;
import com.example.electricityproject.person.PersonFragment;
import com.example.electricityproject.shopp.ShoppingFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mainVp;
    private CommonTabLayout mainTab;
    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();
    private List<Fragment> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mainTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}