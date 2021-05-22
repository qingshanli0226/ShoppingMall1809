package com.example.electricityproject.classify.kind.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.GoodsBean;
import com.example.common.bean.KindAccessoryBean;
import com.example.electricityproject.R;

import java.util.List;

public
class TypeAdapter extends BaseAdapter<Object> {

    private Context context;
    public TypeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        switch (viewType){
            case 0:
                return R.layout.item_hot_right;
            case 1:
                return R.layout.item_child_right;
        }
        return R.layout.item_hot_right;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, Object itemData) {

        switch (position){
            case 0:
                List<GoodsBean.ResultBean.HotProductListBean> hotProductListBeans = (List<GoodsBean.ResultBean.HotProductListBean>) itemData;
                RecyclerView hotRv = baseViewHolder.getView(R.id.rv_hot_right);
                GridLayoutManager manager = new GridLayoutManager(context,1);
                manager.setOrientation(RecyclerView.HORIZONTAL);
                hotRv.setLayoutManager(manager);
                HotAdapter adapter = new HotAdapter(context);
                hotRv.setAdapter(adapter);
                adapter.updateData(hotProductListBeans);

                break;
            case 1:

                List<GoodsBean.ResultBean.ChildBean> childBeans = (List<GoodsBean.ResultBean.ChildBean>) itemData;
                RecyclerView goodsRv = baseViewHolder.getView(R.id.rv_child_right);
                goodsRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                ChildAdapter goodAdapter = new ChildAdapter(context);
                goodsRv.setAdapter(goodAdapter);
                goodAdapter.updateData(childBeans);
                break;
        }
    }


    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
