package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.fiannce.bawei.net.mode.FocusBean;
import com.fiannce.bawei.net.mode.VersionBean;
import com.shoppingmall.bawei.shoppingmall1809.R;
import com.shoppingmall.bawei.shoppingmall1809.ShpmallApplication;
import com.shoppingmall.bawei.shoppingmall1809.databinding.ActivityMvvmBinding;

public class MVVMActivity extends AppCompatActivity {
    private ShopModel shopModel;
    private ActivityMvvmBinding activityMvvmBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMvvmBinding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);

        initData();
        activityMvvmBinding.setMvvmActivity(this);
        City city = new City();
        activityMvvmBinding.setCity(city);
        city.setImageUrl("https://img1.baidu.com/it/u=2496571732,442429806&fm=26&fmt=auto&gp=0.jpg");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                city.setImageUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fyouimg1.c-ctrip.com%2Ftarget%2Ftg%2F035%2F063%2F726%2F3ea4031f045945e1843ae5156749d64c.jpg&refer=http%3A%2F%2Fyouimg1.c-ctrip.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624783131&t=a06eb1747b9dd47738e0bbfe96fd34b4");
            }
        },3000);

       /* activityMvvmBinding.btnVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopModel.getUpdateVersion();
            }
        });*/
        activityMvvmBinding.btnFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopModel.getFocusBean();
            }
        });
    }

    public void onBtnVersionClick() {
        shopModel.getUpdateVersion();
        Toast.makeText(this,"version按钮被点击",Toast.LENGTH_SHORT).show();
    }


    private void initData() {
        shopModel = new ViewModelProvider(this).get(ShopModel.class);
        shopModel.getLiveData().observe(this, new Observer<ViewModeBean<MVVMBean>>() {
            @Override
            public void onChanged(ViewModeBean<MVVMBean> viewModeBean) {
                viewModeBean.handle(new ViewModeBean.IHanleCallback<MVVMBean>() {
                    @Override
                    public void onLoading() {
                        Toast.makeText(MVVMActivity.this, "加载",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSuccess(MVVMBean mvvmBean) {
                        mvvmBean.getData(new MVVMBean.IBeanCallBack() {
                            @Override
                            public void onVersion(VersionBean versionBean) {
                                activityMvvmBinding.setVersion(mvvmBean.getVersionBean());
                            }
                            @Override
                            public void onFocus(FocusBean focusBean) {
                                Toast.makeText(MVVMActivity.this, mvvmBean.getFocusBean().getResult().get(1).getCoverImg(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MVVMActivity.this, "错误",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
