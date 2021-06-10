package com.example.electricityproject.main;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.bean.LogBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.electricityproject.classify.ClassifyFragment;
import com.example.electricityproject.find.FindFragment;
import com.example.electricityproject.home.HomeFragment;
import com.example.electricityproject.person.PersonFragment;
import com.example.electricityproject.shopp.ShoppingFragment;
import com.example.electricityproject.view.CircleView;
import com.example.framework.BaseActivity;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessUserManager;
import com.example.manager.ShopCacheManger;
import com.example.view.ToolBar;

import java.util.List;


public class MainActivity extends BaseActivity implements ShopCacheManger.iShopBeanChangeListener{

    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private FindFragment findFragment;
    private ShoppingFragment shoppingFragment;
    private PersonFragment personFragment;
    private FragmentTransaction fragmentTransaction;
    private RadioGroup group;
    private RadioButton btnHome;
    private RadioButton btnBuycar;
    private com.example.view.ToolBar toolbar;
    private android.widget.LinearLayout mainLin;
    private RadioButton btnKind;
    private RadioButton btnFind;
    private RadioButton btnPerson;
    private CircleView buyCarNum;

    @Override
    protected void initData() {
        btnHome.setChecked(true);

        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        findFragment = new FindFragment();
        shoppingFragment = new ShoppingFragment();
        personFragment = new PersonFragment();

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_lin, homeFragment);
        fragmentTransaction.add(R.id.main_lin, classifyFragment);
        fragmentTransaction.add(R.id.main_lin, findFragment);
        fragmentTransaction.add(R.id.main_lin, shoppingFragment);
        fragmentTransaction.add(R.id.main_lin, personFragment);
        //第一次进入首页加载HomeFragment
        BeginTransaction(homeFragment, classifyFragment, findFragment, shoppingFragment, personFragment);
        //点击切换各个Fragment
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.btn_home:
                        BeginTransaction(homeFragment, classifyFragment, findFragment, shoppingFragment, personFragment);
                        break;
                    case R.id.btn_kind:
                        BeginTransaction(classifyFragment, homeFragment, findFragment, shoppingFragment, personFragment);
                        break;
                    case R.id.btn_find:
                        BeginTransaction(findFragment, classifyFragment, homeFragment, shoppingFragment, personFragment);
                        break;
                    case R.id.btn_buycar:
                        //制造崩溃
//                        CrashReport.testJavaCrash();
                        BeginTransaction(shoppingFragment, classifyFragment, findFragment, homeFragment, personFragment);
                        LogBean logBean = BusinessUserManager.getInstance().getIsLog();
                        if (logBean == null) {
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.main_userNoLogin), Toast.LENGTH_SHORT).show();
                            BusinessARouter.getInstance().getUserManager().OpenLogActivity(MainActivity.this, null);
                        }
                        break;
                    case R.id.btn_person:
                        BeginTransaction(personFragment, classifyFragment, findFragment, homeFragment, shoppingFragment);
                        break;
                }
            }
        });

        if (BusinessUserManager.getInstance().getIsLog()!=null && ShopCacheManger.getInstance().getShortBeanList()!=null){
            List<ShortcartProductBean.ResultBean> shortcartProductBean = ShopCacheManger.getInstance().getShortBeanList();
            if (shortcartProductBean.size()>0){
                buyCarNum.setVisibility(View.VISIBLE);
                buyCarNum.setCurrentNum(""+shortcartProductBean.size());
            }else {
                buyCarNum.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onLoginChange(LogBean isLog) {
        if (BusinessUserManager.getInstance().getIsLog()!=null && ShopCacheManger.getInstance().getShortBeanList()!=null){
            List<ShortcartProductBean.ResultBean> shortcartProductBean = ShopCacheManger.getInstance().getShortBeanList();
            if (shortcartProductBean.size()>0){
                buyCarNum.setVisibility(View.VISIBLE);
                buyCarNum.setCurrentNum(""+shortcartProductBean.size());
            }else {
                buyCarNum.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        group = (RadioGroup) findViewById(R.id.group);
        btnHome = (RadioButton) findViewById(R.id.btn_home);
        btnBuycar = (RadioButton) findViewById(R.id.btn_buycar);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        mainLin = (LinearLayout) findViewById(R.id.main_lin);
        btnKind = (RadioButton) findViewById(R.id.btn_kind);
        btnFind = (RadioButton) findViewById(R.id.btn_find);
        btnPerson = (RadioButton) findViewById(R.id.btn_person);
        buyCarNum = (CircleView) findViewById(R.id.buy_car_num);
        ShopCacheManger.getInstance().registerShopBeanChange(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        btnBuycar.setChecked(true);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.hide(homeFragment);
        fragmentTransaction.hide(classifyFragment);
        fragmentTransaction.hide(findFragment);
        fragmentTransaction.show(shoppingFragment);
        fragmentTransaction.hide(personFragment);
        fragmentTransaction.commit();
    }

    private void BeginTransaction(Fragment showFragment, Fragment hideFragmentOne, Fragment hideFragmentTwo, Fragment hideFragmentThree, Fragment hideFragmentFour) {
        fragmentTransaction.show(showFragment);
        fragmentTransaction.hide(hideFragmentOne);
        fragmentTransaction.hide(hideFragmentTwo);
        fragmentTransaction.hide(hideFragmentThree);
        fragmentTransaction.hide(hideFragmentFour);
        fragmentTransaction.commit();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShopCacheManger.getInstance().unregisterShopBeanChange(this);
    }


    @Override
    public void OnShopBeanChange() {
        if (BusinessUserManager.getInstance().getIsLog()!=null&&ShopCacheManger.getInstance().getShortBeanList()!=null){
            if (ShopCacheManger.getInstance().getShortBeanList().size()>0){
                buyCarNum.setVisibility(View.VISIBLE);
                buyCarNum.setCurrentNum(ShopCacheManger.getInstance().getShortBeanList().size()+"");
            }else {
                buyCarNum.setVisibility(View.GONE);
            }
        }
    }
}