package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter;

import android.content.Context;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseRvAdapter;

import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.SkirtBean;

import com.example.net.bean.StationeryBean;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.manager.FullyGridLayoutManager;

import java.util.List;

public class ClassRightAdapter<T> extends BaseRvAdapter<T>{
    private int mTypes;
    public static final int SKIRT_TYPE = 0;
    public static final int JACKET_TYPE = 1;
    public static final int PANTS_TYPE = 2;
    public static final int OVERCOAT_TYPE = 3;
    public static final int ACCRSSORY_TYPE = 4;
    public static final int BAG_TYPE = 5;
    public static final int DRESS_TYPE = 6;
    public static final int PRODUCTS_TYPE = 7;
    public static final int STATIONERY_TYPE = 8;
    public static final int DIGIT_TYPE = 9;
    public static final int GAME_TYPE = 10;
    private Context context;
    public void  setUpdateData(List<T> strings, int styp, Context context){
        mTypes=styp;
        this.context=context;
        setDataList(strings);
        notifyDataSetChanged();
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_right_item;
    }
    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {
        RecyclerView recyclerViewTop = holder.getView(R.id.classify_right_item_rv_top);
        RecyclerView recyclerViewButton = holder.getView(R.id.classify_right_item_rv_button);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        switch (mTypes){
            case SKIRT_TYPE:
                SkirtBean.ResultBean resultBean = (SkirtBean.ResultBean) itemData;

                List<SkirtBean.ResultBean.HotProductListBean> skirthot_product_list = resultBean.getHot_product_list();
                List<SkirtBean.ResultBean.ChildBean> skirtchild = resultBean.getChild();

                //top
                ClassRightTopAdapter<SkirtBean.ResultBean.HotProductListBean> skirtchildBeanClassRightTopAdapter = new ClassRightTopAdapter<>(skirthot_product_list, mTypes);
                recyclerViewTop.setAdapter(skirtchildBeanClassRightTopAdapter);
                recyclerViewTop.setLayoutManager(linearLayoutManager);
                //button
                ClassRightButtonAdapter<SkirtBean.ResultBean.ChildBean> skirtchildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(skirtchild, mTypes);
                recyclerViewButton.setAdapter(skirtchildBeanClassRightButtonAdapter);
                recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case JACKET_TYPE:
                    JacketBean.ResultBean jacketresultBean = (JacketBean.ResultBean) itemData;
                    List<JacketBean.ResultBean.ChildBean> jacketchild = jacketresultBean.getChild();
                    List<JacketBean.ResultBean.HotProductListBean> jackethot_product_list = jacketresultBean.getHot_product_list();
                    //top
                    ClassRightTopAdapter<JacketBean.ResultBean.HotProductListBean> jackethotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(jackethot_product_list, mTypes);
                    recyclerViewTop.setAdapter(jackethotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                //button
                    ClassRightButtonAdapter<JacketBean.ResultBean.ChildBean> jacketchildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(jacketchild, mTypes);
                    recyclerViewButton.setAdapter(jacketchildBeanClassRightButtonAdapter);

                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case PANTS_TYPE:
                    PantsBean.ResultBean pantsresultBean = (PantsBean.ResultBean) itemData;
                    List<PantsBean.ResultBean.HotProductListBean> pantshot_product_list = pantsresultBean.getHot_product_list();
                    List<PantsBean.ResultBean.ChildBean> pantschild = pantsresultBean.getChild();
                    //top
                    ClassRightTopAdapter<PantsBean.ResultBean.HotProductListBean> pantshotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(pantshot_product_list, mTypes);
                    recyclerViewTop.setAdapter(pantshotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                //button
                    ClassRightButtonAdapter<PantsBean.ResultBean.ChildBean> pantschildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(pantschild, mTypes);
                    recyclerViewButton.setAdapter(pantschildBeanClassRightButtonAdapter);

                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case OVERCOAT_TYPE:
                    OvercoatBean.ResultBean overcoatresultBean = (OvercoatBean.ResultBean) itemData;
                    List<OvercoatBean.ResultBean.HotProductListBean> overcoathot_product_list = overcoatresultBean.getHot_product_list();
                    List<OvercoatBean.ResultBean.ChildBean> overcoatchild = overcoatresultBean.getChild();
                    //top
                    ClassRightTopAdapter<OvercoatBean.ResultBean.HotProductListBean> overcpathotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(overcoathot_product_list, mTypes);
                    recyclerViewTop.setAdapter(overcpathotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<OvercoatBean.ResultBean.ChildBean> overcpatchildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(overcoatchild, mTypes);
                    recyclerViewButton.setAdapter(overcpatchildBeanClassRightButtonAdapter);

                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case ACCRSSORY_TYPE:
                    AccrssoryBean.ResultBean accrssoryresultBean = (AccrssoryBean.ResultBean) itemData;
                    List<AccrssoryBean.ResultBean.HotProductListBean> accrssoryhot_product_list = accrssoryresultBean.getHot_product_list();
                    List<AccrssoryBean.ResultBean.ChildBean> accrssorychild = accrssoryresultBean.getChild();
                    //top
                    ClassRightTopAdapter<AccrssoryBean.ResultBean.HotProductListBean> accrssoryhotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(accrssoryhot_product_list, mTypes);
                    recyclerViewTop.setAdapter(accrssoryhotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<AccrssoryBean.ResultBean.ChildBean> accrssorychildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(accrssorychild, mTypes);
                    recyclerViewButton.setAdapter(accrssorychildBeanClassRightButtonAdapter);
                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case BAG_TYPE:
                    BagBean.ResultBean bagresultBean = (BagBean.ResultBean) itemData;
                    List<BagBean.ResultBean.HotProductListBean> baghot_product_list = bagresultBean.getHot_product_list();
                    List<BagBean.ResultBean.ChildBean> bagchild = bagresultBean.getChild();
                    //top
                    ClassRightTopAdapter<BagBean.ResultBean.HotProductListBean> baghotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(baghot_product_list, mTypes);
                    recyclerViewTop.setAdapter(baghotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<BagBean.ResultBean.ChildBean> bagchildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(bagchild, mTypes);
                    recyclerViewButton.setAdapter(bagchildBeanClassRightButtonAdapter);
                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case DRESS_TYPE:
                    DressBean.ResultBean dressresultBean = (DressBean.ResultBean) itemData;
                    List<DressBean.ResultBean.HotProductListBean> dresshot_product_list = dressresultBean.getHot_product_list();
                    List<DressBean.ResultBean.ChildBean> dresschild = dressresultBean.getChild();
                    //top
                    ClassRightTopAdapter<DressBean.ResultBean.HotProductListBean> dresshotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(dresshot_product_list, mTypes);
                    recyclerViewTop.setAdapter(dresshotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<DressBean.ResultBean.ChildBean> dresschildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(dresschild, mTypes);
                    recyclerViewButton.setAdapter(dresschildBeanClassRightButtonAdapter);
                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case PRODUCTS_TYPE:
                    ProductsBean.ResultBean productsresultBean = (ProductsBean.ResultBean) itemData;
                    List<ProductsBean.ResultBean.HotProductListBean> productshot_product_list = productsresultBean.getHot_product_list();
                    List<ProductsBean.ResultBean.ChildBean> productschild = productsresultBean.getChild();
                    //top
                    ClassRightTopAdapter<ProductsBean.ResultBean.HotProductListBean> productshotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(productshot_product_list, mTypes);
                    recyclerViewTop.setAdapter(productshotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<ProductsBean.ResultBean.ChildBean> productschildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(productschild, mTypes);
                    recyclerViewButton.setAdapter(productschildBeanClassRightButtonAdapter);
                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case STATIONERY_TYPE:
                    StationeryBean.ResultBean stationeyresultBean = (StationeryBean.ResultBean) itemData;
                    List<StationeryBean.ResultBean.HotProductListBean> stationeyhot_product_list = stationeyresultBean.getHot_product_list();
                    List<StationeryBean.ResultBean.ChildBean> stationeychild = stationeyresultBean.getChild();
                    //top
                    ClassRightTopAdapter<StationeryBean.ResultBean.HotProductListBean> stationeyhotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(stationeyhot_product_list, mTypes);
                    recyclerViewTop.setAdapter(stationeyhotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<StationeryBean.ResultBean.ChildBean> stationeychildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(stationeychild, mTypes);
                    recyclerViewButton.setAdapter(stationeychildBeanClassRightButtonAdapter);
                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case DIGIT_TYPE:
                    DigitBean.ResultBean digitresultBean = (DigitBean.ResultBean) itemData;
                    List<DigitBean.ResultBean.HotProductListBean> digithot_product_list = digitresultBean.getHot_product_list();
                    List<DigitBean.ResultBean.ChildBean> digitchild = digitresultBean.getChild();
                    //top
                    ClassRightTopAdapter<DigitBean.ResultBean.HotProductListBean> digithotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(digithot_product_list, mTypes);
                    recyclerViewTop.setAdapter(digithotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<DigitBean.ResultBean.ChildBean> digitchildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(digitchild, mTypes);
                    recyclerViewButton.setAdapter(digitchildBeanClassRightButtonAdapter);
                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;
                case GAME_TYPE:
                    GameBean.ResultBean gameresultBean = (GameBean.ResultBean) itemData;
                    List<GameBean.ResultBean.HotProductListBean> gamehot_product_list = gameresultBean.getHot_product_list();
                    List<GameBean.ResultBean.ChildBean> gamechild = gameresultBean.getChild();
                    //top
                    ClassRightTopAdapter<GameBean.ResultBean.HotProductListBean> gamehotProductListBeanClassRightTopAdapter = new ClassRightTopAdapter<>(gamehot_product_list, mTypes);
                    recyclerViewTop.setAdapter(gamehotProductListBeanClassRightTopAdapter);
                    recyclerViewTop.setLayoutManager(linearLayoutManager);
                    //button
                    ClassRightButtonAdapter<GameBean.ResultBean.ChildBean> gamechildBeanClassRightButtonAdapter = new ClassRightButtonAdapter<>(gamechild, mTypes);
                    recyclerViewButton.setAdapter(gamechildBeanClassRightButtonAdapter);
                    recyclerViewButton.setLayoutManager(new FullyGridLayoutManager(context,3));
                break;



        }

    }
    @Override
    public int getRootViewType(int position) {
        return position;
    }
}