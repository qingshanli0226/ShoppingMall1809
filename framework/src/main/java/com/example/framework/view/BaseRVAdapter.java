package com.example.framework.view;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHolder> {
    private List<T> datalist = new ArrayList<>();

    public BaseRVAdapter(List<T> datalist) {
        this.datalist = datalist;
    }

    public void updateDate(List<T> datalist){
        this.datalist.clear();
        this.datalist.addAll(datalist);
        notifyDataSetChanged();
    }

    protected BaseRVAdapter() {
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return new BaseViewHolder(inflate);
    }

    protected abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        displayViewHolder(holder, position, datalist.get(position));

        holder.itemView.setOnClickListener(view -> {
            if (iRecyclerItemClickListener != null) {
                iRecyclerItemClickListener.onItemClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(view -> {
            if (iRecyclerItemClickListener != null) {
                iRecyclerItemClickListener.onItemLongClick(position);
            }
            return true;
        });
    }

    protected abstract void displayViewHolder(BaseViewHolder holder, int position, T itemData);


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getRootViewType(position);
    }

    protected abstract int getRootViewType(int position);


    private IRecyclerItemClickListener iRecyclerItemClickListener;

    public void setRecyclerItemClickListener(IRecyclerItemClickListener listener) {
        iRecyclerItemClickListener = listener;
    }

    public interface IRecyclerItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }


    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> sparseArray = new SparseArray<>();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View> V getView(int id) {
            if (sparseArray.get(id) != null) {
                return (V) sparseArray.get(id);
            } else {
                V view = (V) itemView.findViewById(id);
                sparseArray.put(id,view);
                return view;
            }
        }
    }
}


