package com.example.framework;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseRvAdapter.BaseViewHolder> {
    private List<T> data = new ArrayList<>();
    private IRvItemOnClickListener rvItemOnClickListener;

    public void setRvItemOnClickListener(IRvItemOnClickListener rvItemOnClickListener) {
        this.rvItemOnClickListener = rvItemOnClickListener;
    }

    public List<T> getData() {
        return this.data;
    }

    public void updata(List<T> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return getRootItemViewType(position);
    }

    public abstract int getRootItemViewType(int position);

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return new BaseViewHolder(rootView);
    }

    protected abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        displayViewHolder(holder,position,data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rvItemOnClickListener != null) {
                    rvItemOnClickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean isUse = false;
                if (rvItemOnClickListener != null) {
                    isUse  = rvItemOnClickListener.onLongItemClick(position);
                }
                return isUse;
            }
        });
    }

    protected abstract void displayViewHolder(BaseViewHolder holder, int position, T itemView);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> viewSparseArray = new SparseArray<>();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public <V extends View> V getView(int id){
            if (viewSparseArray.get(id) != null) {
                return (V) viewSparseArray.get(id);
            } else{
                V childView = itemView.findViewById(id);
                viewSparseArray.put(id,childView);
                return childView;
            }
        }
    }

    public interface IRvItemOnClickListener{
        void onItemClick(int position);
        boolean onLongItemClick(int position);
    }
}
