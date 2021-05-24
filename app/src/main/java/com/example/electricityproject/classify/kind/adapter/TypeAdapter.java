package com.example.electricityproject.classify.kind.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.adapter.BaseAdapter;
import com.example.common.bean.GoodsBean;
import com.example.electricityproject.R;
import com.example.electricityproject.details.DetailsActivity;

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

                adapter.setRecyclerItemClickListener(new iRecyclerItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                        intent.putExtra("img",hotProductListBeans.get(position).getFigure());
                        intent.putExtra("name",hotProductListBeans.get(position).getName()+"");
                        intent.putExtra("price",hotProductListBeans.get(position).getCover_price());
                        intent.putExtra("productId",hotProductListBeans.get(position).getProduct_id());
                        intent.putExtra("productPrice",hotProductListBeans.get(position).getCover_price());
                        context.startActivity(intent);
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }

                });


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
