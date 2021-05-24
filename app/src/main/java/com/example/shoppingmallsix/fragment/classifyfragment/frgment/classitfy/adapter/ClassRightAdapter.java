package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.framework.BaseRvAdapter;

import com.example.net.bean.classify.ClassBean;

import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.manager.FullyGridLayoutManager;

import java.util.List;

public class ClassRightAdapter<T> extends BaseRvAdapter<T>{

    public static final int SKIRT_TYPE = 0;
    public static final int JACKET_TYPE = 1;
    public static final int PANTS_TYPE = 2;
    public static final int OVERCOAT_TYPE = 3;
    public static final int ACCRSSORY_TYPE = 4;
    public static final int BAG_TYPE = 5;
    public static final int DRESS_TYPE = 6;
    public static final int PRODUCTS_TYPE = 7;
    public static final int STATIONERY_TYPE = 8;
    public static final int DIGIT_TYPE = 9;
    public static final int GAME_TYPE = 10;
    private Context context;
    public void  setUpdateData(List<T> strings,Context context){

        this.context=context;
        setDataList(strings);
        notifyDataSetChanged();
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_right_item;
    }
    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {
        RecyclerView recyclerViewTop = holder.getView(R.id.classify_right_item_rv_top);
        RecyclerView recyclerViewButton = holder.getView(R.id.classify_right_item_rv_button);

        LinearLayout linearLayout = holder.getView(R.id.classify_right_button_item_linear);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        //获取强转数据
        ClassBean.ResultBean resultBean = (ClassBean.ResultBean) itemData;

        List<ClassBean.ResultBean.HotProductListBean> hot_product_list = resultBean.getHot_product_list();
        List<ClassBean.ResultBean.ChildBean> child = resultBean.getChild();
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
        recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));


    }
    @Override
    public int getRootViewType(int position) {
        return position;
    }
}