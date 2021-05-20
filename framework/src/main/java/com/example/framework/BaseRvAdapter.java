package com.example.framework;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public  abstract class BaseRvAdapter<T> extends  RecyclerView.Adapter<BaseRvAdapter.BaseViewHolder> {

    public List<T> dataList = new ArrayList<>();
    private IRecyclerItemClickListener iRecyclerItemClickListener;



    protected void setDataList(List<T> list){
        dataList=list;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);

        return new BaseViewHolder(inflate);
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        displayViewHolder(holder,position,dataList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iRecyclerItemClickListener != null){
                    iRecyclerItemClickListener.onItemClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (iRecyclerItemClickListener != null){
                    iRecyclerItemClickListener.onItwmLongClick(position);
                }

                return true;
            }
        });

    }

    public abstract void displayViewHolder(BaseViewHolder holder,int position,T itemData);

    public void setiRecyclerItemClickListener(IRecyclerItemClickListener listener) {
        iRecyclerItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public abstract int getRootViewType(int position);

    @Override
    public int getItemViewType(int position) {
        return getRootViewType(position);
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray<View> sparseArray = new SparseArray<>();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View> V getView(int id){
            if(sparseArray.get(id) != null){
                return (V)sparseArray.get(id);
            }else {
                V viewById = itemView.findViewById(id);

                sparseArray.put(id,viewById);
                return viewById;
            }
        }
    }


    public interface IRecyclerItemClickListener{
        void onItemClick(int position);
        void onItwmLongClick(int position);
    }

}
