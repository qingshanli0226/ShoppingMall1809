package com.example.electricityproject.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.electricityproject.R;
import com.example.electricityproject.mainfragment.ClassifyFragment;
import com.example.electricityproject.mainfragment.FindFragment;
import com.example.electricityproject.mainfragment.HomeFragment;
import com.example.electricityproject.mainfragment.PersonFragment;
import com.example.electricityproject.mainfragment.ShoppingFragment;
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
        mTabEntitys.add(new TabCus("首页",R.mipmap.bottom01,R.mipmap.bottom02));
        mTabEntitys.add(new TabCus("分类",R.mipmap.bottom07,R.mipmap.bottom08));
        mTabEntitys.add(new TabCus("发现",R.mipmap.bottom09,R.mipmap.bottom10));
        mTabEntitys.add(new TabCus("购物车",R.mipmap.bottom03,R.mipmap.bottom04));
        mTabEntitys.add(new TabCus("个人中心",R.mipmap.bottom05,R.mipmap.bottom06));

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