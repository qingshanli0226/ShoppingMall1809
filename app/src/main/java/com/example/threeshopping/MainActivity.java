package com.example.threeshopping;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.UserManager;
import com.example.net.bean.LoginBean;
import com.example.threeshopping.cart.CartFragment;
import com.example.threeshopping.communit.CommunitFragment;
import com.example.threeshopping.home.HomeFragment;
import com.example.threeshopping.personal.PersonalFragment;
import com.example.threeshopping.type.TypeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private android.widget.LinearLayout mainLinear;
    private android.widget.RadioGroup mainGroup;
    private android.widget.RadioButton mainOne;
    private android.widget.RadioButton mainTwo;
    private android.widget.RadioButton mainThree;
    private android.widget.RadioButton mainFour;
    private android.widget.RadioButton mainFive;
    private LoginBean loginBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainLinear = (LinearLayout) findViewById(R.id.mainLinear);
        mainGroup = (RadioGroup) findViewById(R.id.mainGroup);
        mainOne = (RadioButton) findViewById(R.id.mainOne);
        mainTwo = (RadioButton) findViewById(R.id.mainTwo);
        mainThree = (RadioButton) findViewById(R.id.mainThree);
        mainFour = (RadioButton) findViewById(R.id.mainFour);
        mainFive = (RadioButton) findViewById(R.id.mainFive);

        fragments = new ArrayList<>();
    }

    @Override
    public void initPresenter() {

    }

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void initData() {
        //添加fragment
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunitFragment());
        fragments.add(new CartFragment());
        fragments.add(new PersonalFragment());


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainLinear, fragments.get(0));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(1));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(2));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(3));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(4));
        fragmentTransaction.commit();

        //第一个显示
        mainOne.setChecked(true);
        showFragment(0);


        loginBean = UserManager.getInstance().getLoginBean();
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.mainOne:
                        showFragment(0);
                        break;
                    case R.id.mainTwo:
                        showFragment(1);
                        skip(1);
                        break;
                    case R.id.mainThree:
                        showFragment(2);
                        skip(2);
                        break;
                    case R.id.mainFour:
                        showFragment(3);
                        skip(3);
                        break;
                    case R.id.mainFive:
                        showFragment(4);
                        skip(4);
                        break;
                }
            }
        });
    }

    private void skip(int page) {
        if (loginBean == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("page", page);
//            CommonArouter.getInstance().build(Constants.PATH_LOGIN).with(bundle).navigation();
            CommonArouter.getInstance().build(Constants.PATH_USER).with(bundle).navigation();
        }
    }


    //显示那个fargment
    private void showFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position) {
                fragmentTransaction.show(fragments.get(i));
            } else {
                fragmentTransaction.hide(fragments.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = CommonArouter.getInstance().getBundle();
        int page = bundle.getInt("page");

        Log.i("zzy", "onNew: " + page);
        showFragment(page);
        switch (page) {
            case 0:
                mainOne.setChecked(true);
                break;
            case 1:
                mainTwo.setChecked(true);
                break;
            case 2:
                mainThree.setChecked(true);
                break;
            case 3:
                mainFour.setChecked(true);
                break;
            case 4:
                mainFive.setChecked(true);
                break;
        }

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {
    }

    @Override
    public void onUserChange(LoginBean loginBean) {
        super.onUserChange(loginBean);
        this.loginBean = loginBean;
        LogUtil.d("登录");
    }
}