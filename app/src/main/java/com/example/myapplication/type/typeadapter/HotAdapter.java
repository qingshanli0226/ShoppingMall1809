package com.example.myapplication.type.typeadapter;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.myapplication.R;
import com.example.net.bean.SkirtBean;

public class HotAdapter extends BaseRecyclerViewAdapter<SkirtBean.ResultBean.HotProductListBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_hot_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, SkirtBean.ResultBean.HotProductListBean itemData) {
        TextView view = holder.getView(R.id.money);
        view.setText(itemData.getCover_price()+"");
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/type/typeadapter/HotAdapter.java
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getFigure()).into((ImageView) holder.getView(R.id.img));
=======
        Log.i("zyh", "displayViewHolder: "+itemData);
        ShopmallGlide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getFigure()).into((ImageView) holder.getView(R.id.img));

>>>>>>> zyh:app/src/main/java/com/example/myapplication/typeadapter/HotAdapter.java
    }
    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
