package com.shoppingmall.main.sort;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.MainActivity;
import com.shoppingmall.main.adapter.ComAdapter;
import com.shoppingmall.main.sort.fragment.classfication.ClassificationFragment;
import com.shoppingmall.main.sort.fragment.label.LabelFragment;
import com.shoppingmall.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;


public class SortFragment extends BaseFragment {


    private TextView sortLeft;
    private TextView xxx;
    private TextView sortRight;
    private ViewPager sortVp;
    private RadioGroup sortRadioGroup;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sort;
    }

    @Override
    public void initView() {

        sortLeft = (TextView) mView.findViewById(R.id.sort_left);
        sortRight = (TextView) mView.findViewById(R.id.sort_right);
        sortVp = (ViewPager) mView.findViewById(R.id.sortVp);
        sortRadioGroup = (RadioGroup) mView.findViewById(R.id.sortRadioGroup);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
//        LoginBean loginBean = ShopMallUserManager.getInstance().getLoginBean();
//        if (loginBean==null){
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle(getString(R.string.Tips));
//            builder.setMessage(getString(R.string.TipsMessage));
//            builder.setPositiveButton(getString(R.string.welcomeActivity_alert_button_yes), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    ARouter.getInstance().build(Constants.TO_USER_ACTIVITY).withInt("",0).navigation();
//                }
//            });
//            builder.show();
//        }else {
            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(new ClassificationFragment());
            fragmentList.add(new LabelFragment());
            ComAdapter comAdapter = new ComAdapter(getActivity().getSupportFragmentManager(),fragmentList);
            sortVp.setAdapter(comAdapter);

            sortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i){
                        case R.id.sort_left:
                            sortVp.setCurrentItem(0);
                            break;
                        case R.id.sort_right:
                            sortVp.setCurrentItem(1);
                            break;
                    }
                }
            });
//        }

    }
}