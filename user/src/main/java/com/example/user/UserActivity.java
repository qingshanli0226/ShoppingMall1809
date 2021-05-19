package com.example.user;

<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
=======
>>>>>>> zkhone
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.user.frag.FragmentAdapter;
import com.example.user.frag.LoginFragment;
import com.example.user.frag.RegisterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import com.example.framework.BaseActivity;

public class UserActivity extends BaseActivity {


    private androidx.viewpager.widget.ViewPager vp;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    public int bandLayout() {
        return R.layout.activity_main5;
    }

    @Override
    public void initView() {

        vp = findViewById(R.id.vp);
        list.add(new RegisterFragment());
        list.add(new LoginFragment());
        fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(fragmentPagerAdapter);


    }
    @Subscribe
    public void onEventMain(int a){
        if (a==1){
            vp.setCurrentItem(0);
        }else {
            vp.setCurrentItem(1);
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     EventBus.getDefault().unregister(this);
    }

}