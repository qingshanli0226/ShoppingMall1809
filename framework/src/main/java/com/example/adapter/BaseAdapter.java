package com.example.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {
    private List<T> list=new ArrayList<>();
    private iRecyclerItemClickListener recyclerItemClickListener;
    //添加数据
    public void updateData(List<T> updateList){
        this.list.clear();
        this.list.addAll(updateList);
        notifyDataSetChanged();
    }
    //删除数据
    public void delData(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
    public List<T> getUpdateData(){
        return list;
    }
    public void setRecyclerItemClickListener(iRecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return new BaseViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        displayViewHolder(holder,position,list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerItemClickListener!=null){
                    recyclerItemClickListener.OnItemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (recyclerItemClickListener!=null){
                    recyclerItemClickListener.OnItemLongClick(position);
                }
                return true;
            }
        });
    }

    public abstract int getLayoutId(int viewType);

    public abstract void displayViewHolder(BaseViewHolder baseViewHolder, int position, T itemData);

    public abstract int getRootViewType(int position);



    @Override
    public int getItemViewType(int position) {
        return getRootViewType(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        SparseArray<View> sparseArray=new SparseArray<>();
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
        public <V extends View> V getView(int id){
            if (sparseArray.get(id)!=null){
                return (V) sparseArray.get(id);
            }else {
                V childView = itemView.findViewById(id);
                sparseArray.put(id,childView);
                return childView;
            }

        }
    }

    //点击事件
    public interface iRecyclerItemClickListener{
        void OnItemClick(int position);
        void OnItemLongClick(int position);
    }
}
