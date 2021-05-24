package com.example.shoppingmallsix.fragment.discoverfragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.LoginBean;
import com.example.shoppingmallsix.R;
import com.example.user.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends BaseFragment {


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        if (loginBean != null){

        }else {
            Toast.makeText(getActivity(), getString(R.string.Pleaselogintoyouraccountfirst), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }
}
