package com.example.myapplication.type.typeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.myapplication.R;
import com.example.net.bean.SkirtBean;

public class TypeAdapter extends BaseRecyclerViewAdapter<SkirtBean.ResultBean.ChildBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.type_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, SkirtBean.ResultBean.ChildBean itemData) {
        TextView view = holder.getView(R.id.text);
        view.setText(itemData.getName());
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/type/typeadapter/TypeAdapter.java
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getPic()).into((ImageView) holder.getView(R.id.img));
=======
        ShopmallGlide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getPic()).into((ImageView) holder.getView(R.id.img));

>>>>>>> zyh:app/src/main/java/com/example/myapplication/typeadapter/TypeAdapter.java
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
