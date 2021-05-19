package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.ClassLeftBean;
import com.example.shoppingmallsix.R;

import java.util.List;

public class ClassRightAdapter extends BaseRvAdapter<ClassLeftBean>{
    private LinearLayout linearLayout;
    public ClassRightAdapter(List<ClassLeftBean> strings){
        setDataList(strings);
    }


    @Override
    public int getLayoutId(int viewType) {
        return 0;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ClassLeftBean itemData) {


    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
