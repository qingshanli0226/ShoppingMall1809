package com.example.threeshopping.type;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;

import com.example.threeshopping.type.typechild.classify.ClassifyFragment;

import com.example.threeshopping.type.typechild.label.LabelFragment;


import java.util.ArrayList;
import java.util.List;


public class TypeFragment extends BaseFragment {


    private ToolBar toolbar;
    private RadioGroup typeGroup;
    private RadioButton typeClassify;
    private RadioButton typeLabel;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        typeGroup = (RadioGroup) findViewById(R.id.type_group);
        typeClassify = (RadioButton) findViewById(R.id.type_Classify);
        typeLabel = (RadioButton) findViewById(R.id.type_Label);
        fragments = new ArrayList<>();
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        fragments.add(new ClassifyFragment());
        fragments.add(new LabelFragment());

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.typeLinear,fragments.get(0));
        fragmentTransaction.add(R.id.typeLinear,fragments.get(1));
        fragmentTransaction.commit();

        typeClassify.setChecked(true);
        showFragment(0);

        typeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.type_Classify:
                        showFragment(0);
                        break;
                    case R.id.type_Label:
                        showFragment(1);
                        break;
                }
            }
        });
    }

    private void showFragment(int position) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if(i == position){
                fragmentTransaction.show(fragments.get(i));
            } else{
                fragmentTransaction.hide(fragments.get(i));
            }
        }
        fragmentTransaction.commit();
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
}