package com.example.shoppingmallsix.fragment.classify.frgment.classitfy.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.framework.BaseRvAdapter;

import com.example.net.bean.classify.ClassBean;

import com.example.shoppingmallsix.R;

import java.util.List;

public class ClassRightAdapter extends BaseRvAdapter<ClassBean.ResultBean>{
    private Context context;
    public void  setUpdateData(List<ClassBean.ResultBean> strings,Context context){

        this.context=context;
        setDataList(strings);
        notifyDataSetChanged();
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_right_item;
    }
    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ClassBean.ResultBean itemData) {
        RecyclerView recyclerViewTop = holder.getView(R.id.classify_right_item_rv_top);
        RecyclerView recyclerViewButton = holder.getView(R.id.classify_right_item_rv_button);

        LinearLayout linearLayout = holder.getView(R.id.classify_right_button_item_linear);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        List<ClassBean.ResultBean.HotProductListBean> hot_product_list = itemData.getHot_product_list();
        List<ClassBean.ResultBean.ChildBean> child = itemData.getChild();
        //判断lngearlayout
        int indext = child.size() /4;
        if (indext >1){
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200+(indext-4*100)));
        }else {
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        //top
        ClassRightTopAdapter<ClassBean.ResultBean.HotProductListBean> hotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(hot_product_list);
        recyclerViewTop.setAdapter(hotProductListBeanClassRightTopAdapter);
        recyclerViewTop.setLayoutManager(linearLayoutManager);
        //button
        ClassRightButtonAdapter<ClassBean.ResultBean.ChildBean> childBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(child);
        recyclerViewButton.setAdapter(childBeanClassRightButtonAdapter);
        recyclerViewButton.setLayoutManager(new GridLayoutManager(context,3));


    }
    @Override
    public int getRootViewType(int position) {
        return position;
    }
}