package com.shoppingmall.main.sort.fragment.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.GoodsBean;
import com.shoppingmall.net.bean.HomeBean;

import java.util.List;

public class ClassificationContentAdapter extends BaseRvAdapter<Object> {
    private final int SKIRT_URL = 0;
    private final int JACKET_URL = 1;

    @Override
    public int getLayoutId(int viewType) {
        int layoutId =-1;
        switch (viewType){
            case 0:
                layoutId = R.layout.item_class_horizontal_type_layout;
                break;
            case 1:
                layoutId = R.layout.item_class_vertical_type_layout;
                break;
        }
        return layoutId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position){
            case 0:
                List<GoodsBean.ResultBean.HotProductListBean> hotProductListBeans = (List<GoodsBean.ResultBean.HotProductListBean>) itemData;
                RecyclerView classHorizontalTypeRv = holder.getView(R.id.classHorizontalType);
                HorizontalTypeAdapter horizontalTypeAdapter = new HorizontalTypeAdapter();
                classHorizontalTypeRv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
                classHorizontalTypeRv.setAdapter(horizontalTypeAdapter);
                horizontalTypeAdapter.updateData(hotProductListBeans);
                break;

            case 1:
                List<GoodsBean.ResultBean.ChildBean> hotInfoBeans = (List<GoodsBean.ResultBean.ChildBean>) itemData;
                RecyclerView classVerticalTypeRv = holder.getView(R.id.classVerticalType);
                VerticalType verticalType = new VerticalType();
                classVerticalTypeRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                classVerticalTypeRv.setAdapter(verticalType);
                verticalType.updateData(hotInfoBeans);
                break;
        }
    }

    @Override
    public int getRootViewType(int position) {
        //绑定对应数据类型
        int type = -1;
        switch (position){
            case 0:
                type = SKIRT_URL;
                break;
            case 1:
                type = JACKET_URL;
                break;
        }
        return type;
    }
}
