package com.example.threeshopping.type.typechild.classify.adapter;

import android.view.View;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.TypeBean;
import com.example.threeshopping.R;

import java.util.List;

public class ClassAdapter extends BaseRvAdapter<Object>{
    @Override
    public int getRootItemViewType(int position) {
        if(position == 0){
            return 0;
        }
        return 1;
    }

    @Override
    protected int getLayoutId(int viewType) {
        int layoutId = -1;
        if(viewType == 0){
            layoutId = R.layout.itme_type_hot_layout;
        } else{
            layoutId = R.layout.itme_type_tui_layout;

        }
        return layoutId;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, Object itemView) {
        if(position == 0){
            RecyclerView typeRv = holder.getView(R.id.typeRv);
            List<TypeBean.ResultBean.HotProductListBean> hotProductListBeanList = (List<TypeBean.ResultBean.HotProductListBean>) itemView;
            ItemClassHotAdapter itemClassHotAdapter = new ItemClassHotAdapter();
            typeRv.setAdapter(itemClassHotAdapter);
            itemClassHotAdapter.updata(hotProductListBeanList);
            typeRv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),RecyclerView.HORIZONTAL, false));
        } else{
            RecyclerView tuiRv = holder.getView(R.id.tuiRv);
            List<TypeBean.ResultBean.ChildBean> childBeans = (List<TypeBean.ResultBean.ChildBean>) itemView;
            ItemClassTuiAdapter itemClassTuiAdapter = new ItemClassTuiAdapter();
            tuiRv.setAdapter(itemClassTuiAdapter);
            itemClassTuiAdapter.updata(childBeans);
            tuiRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL ));

        }
    }
}
