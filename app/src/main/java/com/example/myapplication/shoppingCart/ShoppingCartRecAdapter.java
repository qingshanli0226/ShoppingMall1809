package com.example.myapplication.shoppingCart;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.myapplication.R;
import com.example.net.bean.ShoppingCartBean;

import java.util.List;

public class ShoppingCartRecAdapter extends BaseRecyclerViewAdapter<ShoppingCartBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.myshopping_item_lay;
    }

    protected IRecyclerItemChildClickListener childClickListener;

    public void setChildClickListener(IRecyclerItemChildClickListener childClickListener) {
        this.childClickListener = childClickListener;
    }

    public ShoppingCartRecAdapter(List<ShoppingCartBean.ResultBean> list) {
        dataList=list;
    }


    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ShoppingCartBean.ResultBean itemData) {
        ShopmallGlide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080" + "/atguigu/img" +itemData.getUrl()).into(holder.getView(R.id.shoppingItemImage));//图片
        TextView name = holder.getView(R.id.shoppingItemName);//名字
        name.setText(itemData.getProductName());
        TextView price = holder.getView(R.id.shoppingItemPrice);//价格
        price.setText(itemData.getProductPrice()+"");
        TextView num = holder.getView(R.id.shoppingItemNum);//数量
        num.setText(itemData.getProductNum()+"");

        CheckBox checkBox = holder.getView(R.id.shoppingItemCheck);//单选
        if (itemData.isCheck()){//根据数据源判断数据
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        checkBox.setOnClickListener(v -> {
            if (childClickListener!=null){
                childClickListener.onItemCheClick(position,v);
            }
        });
        ImageView add = holder.getView(R.id.shoppingItemAdd);//加
        add.setOnClickListener(v -> {
            if (childClickListener!=null){
                childClickListener.onItemAddClick(position,v);
            }
        });

        ImageView sub = holder.getView(R.id.shoppingItemSub);//减
        sub.setOnClickListener(v -> {
            if (childClickListener!=null){
                childClickListener.onItemSubClick(position,v);
            }
        });

    }
//子控件点击接口
    public interface IRecyclerItemChildClickListener{
        void onItemCheClick(int position,View view);
        void onItemAddClick(int position,View view);
        void onItemSubClick(int position,View view);
    }
    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
