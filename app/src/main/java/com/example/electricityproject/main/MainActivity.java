package com.example.electricityproject.main;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import com.example.framework.BaseActivity;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessBuyCarManger;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
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
    private android.widget.TextView buyCarNum;
    private List<ShortcartProductBean.ResultBean> resultBeanList = new ArrayList<>();


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

        BeginTransaction(homeFragment, classifyFragment, findFragment, shoppingFragment, personFragment);

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
                        BeginTransaction(shoppingFragment, classifyFragment, findFragment, homeFragment, personFragment);
                        LogBean logBean = BusinessUserManager.getInstance().getIsLog();
                        if (logBean == null) {
                            Toast.makeText(MainActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
                            BusinessARouter.getInstance().getUserManager().OpenLogActivity(MainActivity.this, null);
                        } else {
                            Toast.makeText(MainActivity.this, "用户已经登录", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.btn_person:
                        BeginTransaction(personFragment, classifyFragment, findFragment, homeFragment, shoppingFragment);
                        break;
                }
            }
        });

        if (BusinessUserManager.getInstance().getIsLog()!=null && BusinessBuyCarManger.getInstance().getShortcartProductBean()!=null){
            ShortcartProductBean shortcartProductBean = BusinessBuyCarManger.getInstance().getShortcartProductBean();
            buyCarNum.setVisibility(View.VISIBLE);
            buyCarNum.setText(""+shortcartProductBean.getResult().size());
        }else {
            buyCarNum.setVisibility(View.GONE);
        }

        BusinessBuyCarManger.getInstance().Register(new BusinessBuyCarManger.iShopBeanChange() {
            @Override
            public void OnShopBeanChange(ShortcartProductBean shortcartProductBean) {
                resultBeanList = shortcartProductBean.getResult();
                buyCarNum.setVisibility(View.VISIBLE);
                buyCarNum.setText(""+resultBeanList.size());
            }
        });
        BusinessUserManager.getInstance().Register(new BusinessUserManager.IUserLoginChanged() {
            @Override
            public void onLoginChange(LogBean isLog) {
                if (isLog!=null){
                    buyCarNum.setVisibility(View.VISIBLE);
                    buyCarNum.setText(""+resultBeanList.size());
                }else {
                    Toast.makeText(MainActivity.this, "123456", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        buyCarNum = (TextView) findViewById(R.id.buy_car_num);
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
}