package com.example.framework;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {
    public List<T> dataList=new ArrayList<>();//各种类型
    private IRecyclerItemClickListener iRecyclerItemClickListener;

    public void updataData(List<T> dataList){
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView= LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType),parent,false);
        return null;
    }
    //让子类根据viewType返回相应的布局
    public abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        displayViewHolder(holder,position,dataList.get(position));
        //单击
        holder.itemView.setOnClickListener(v -> {
            if (iRecyclerItemClickListener!=null){
                iRecyclerItemClickListener.onItemClick(position);
            }
        });
        //长按
        holder.itemView.setOnLongClickListener(v -> {
            if (iRecyclerItemClickListener!=null){
                iRecyclerItemClickListener.onItemLongClick(position);
            }
            return false;
        });
    }
    public abstract void displayViewHolder(BaseViewHolder holder,int position,T itemData);
    //注册点击
    public void setRecyclerItemClickListener(IRecyclerItemClickListener listener){
        iRecyclerItemClickListener=listener;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //让子类根据位置返回具体的布局类型
    public abstract int getRootViewType(int position);

    @Override
    public int getItemViewType(int position) {
        return getRootViewType(position);
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        //存储控件  避免大量findViewById
        SparseArray<View> viewSparseArray=new SparseArray<>();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        //获取ItemView的子控件,如果子控件已经在列表里了，就直接返回，避免调用findViewId，如果没有，则生成子控件，且将
        //生成的子控件存储到列表里，返回
        public <V extends View>V getView(int id){
            if (viewSparseArray.get(id)!=null){
                return (V) viewSparseArray.get(id);
            }else {
                V viewById = itemView.findViewById(id);
                viewSparseArray.put(id,viewById);
                return viewById;
            }
        }
    }
    //接口
    public interface IRecyclerItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }
}
