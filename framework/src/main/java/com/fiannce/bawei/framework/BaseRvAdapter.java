package com.fiannce.bawei.framework;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//实现一个万能适配器，该适配器可以适配不同的数据，适配不同的布局.
public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseRvAdapter.BaseViewHolder> {
    public List<T> dataList = new ArrayList<>();//通过泛型来支持不同的类型的数据
    private IRecyclerItemClickListener iRecyclerItemClickListener;

    public void updateData(List<T> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType),parent,false);
        return new BaseViewHolder(rootView);
    }

    //让子类根据viewType返回对应的布局Id
    public abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {

        displayViewHolder(holder,position,dataList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iRecyclerItemClickListener!=null) {
                    iRecyclerItemClickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (iRecyclerItemClickListener!=null) {
                    iRecyclerItemClickListener.onItemLongClick(position);
                }
                return true;
            }
        });

    }

    public abstract void displayViewHolder(BaseViewHolder holder, int position, T itemData);

    //注册点击回调接口
    public void setRecyclerItemClickListener(IRecyclerItemClickListener listener) {
        iRecyclerItemClickListener = listener;
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

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        //存储控件，避免大量调用findViewById
        SparseArray<View> viewSparseArray = new SparseArray<>();

        public BaseViewHolder(View rootView) {
            super(rootView);
        }

        //获取ItemView的子控件,如果子控件已经在列表里了，就直接返回，避免调用findViewId，如果没有，则生成子控件，且将
        //生成的子控件存储到列表里，返回
        public <V extends View> V getView(int id) {
            if (viewSparseArray.get(id) != null) {
                return (V)viewSparseArray.get(id);
            } else {
                V childView = itemView.findViewById(id);
                viewSparseArray.put(id,childView);
                return childView;
            }
        }
    }

    public interface IRecyclerItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }
}
