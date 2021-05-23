package com.example.myapplication.typeadapter;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.SkirtBean;

import java.util.List;

public class ClassifyAdapter extends BaseRecyclerViewAdapter<Object> {


    @Override
    public int getLayoutId(int viewType) {
        switch (viewType){
            case 0:
                return R.layout.item_hot;
            case 1:
                return R.layout.item_goods;

        }
        return R.layout.item_hot;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position){
            case 0:
                Log.i("zyh", "displayViewHolder: "+itemData);
                List<SkirtBean.ResultBean.HotProductListBean> hotProductListBeans= (List<SkirtBean.ResultBean.HotProductListBean>) itemData;
                Log.i("zyh", "displayViewHolder:错误 "+itemData);

                RecyclerView recyclerView  = holder.getView(R.id.hotimg);
                HotAdapter hotAdapter = new HotAdapter();
                hotAdapter.updataData(hotProductListBeans);
                recyclerView.setAdapter(hotAdapter);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                Log.i("zyh", "displayViewHolder:eee "+itemData);

                break;
            case 1:
                Log.i("zyh", "displayViewHolderffff: "+itemData);
                List<SkirtBean.ResultBean.ChildBean> childBeans= (List<SkirtBean.ResultBean.ChildBean>) itemData;
                RecyclerView view = holder.getView(R.id.typeimg);
                TypeAdapter typeAdapter = new TypeAdapter();
                typeAdapter.updataData(childBeans);
                view.setAdapter(typeAdapter);
                view.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                Log.i("zyh", "displayViewHolderjjj: "+itemData);

                break;
        }

    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
