package com.example.shoppingmall1809.main.type.category.adapter;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.CategoryBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.particulars.ParticularsActivity;

import java.util.List;

public class CategoryAdapter extends BaseRVAdapter<Object> {


    public CategoryAdapter(List<Object> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        int type = -1;
        switch (viewType){
            case 0:
                type = R.layout.type_item_hot;
                break;
            case 1:
                type = R.layout.type_item_use;
                break;
        }
        return type;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position){
            case 0:
                List<CategoryBean.ResultBean.HotProductListBean> hotProductListBeans = (List<CategoryBean.ResultBean.HotProductListBean>) itemData;
                RecyclerView hotrv = holder.itemView.findViewById(R.id.frag_type_hot_rv);
                hotrv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),RecyclerView.HORIZONTAL,false));

                CHotAdapter cHotAdapter = new CHotAdapter(hotProductListBeans);
                cHotAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(holder.itemView.getContext(), ParticularsActivity.class);
                        intent.putExtra("code",4);
                        intent.putExtra("hotProductListBeans",hotProductListBeans.get(position));
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });

                hotrv.setAdapter(cHotAdapter);
                break;
            case 1:
                List<CategoryBean.ResultBean.ChildBean> childBeans= (List<CategoryBean.ResultBean.ChildBean>) itemData;
                RecyclerView userrv = holder.itemView.findViewById(R.id.frag_type_use_rv);
                userrv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),3));
                CUseAdapter cUseAdapter = new CUseAdapter(childBeans);
                userrv.setAdapter(cUseAdapter);
                break;
        }
    }

    @Override
    protected int getRootViewType(int position) {
        return position;
    }


}
