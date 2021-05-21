package com.example.myapplication.typeadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.R;
import com.example.myapplication.home.homeadapter.HotoneAdapter;
import com.example.net.bean.AccessoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.Overconat;
import com.example.net.bean.PantsBean;
import com.example.net.bean.SkirtBean;

import java.util.List;

public class ClassifyAdapter<T> extends BaseRecyclerViewAdapter<T> {
    public static final int SKIRT_URL = 1;
    public static final int JACKET_URL=2;
    public static final int PANTS_URL=3;
    public static final int OVERCOAT_URL=4;
    public static final int ACCESSORY_URL=5;
    public static final int BAG_URL=6;
    public static final int DRESS_UP_URL=7;
    public static final int HOME_PRODUCTS_URL=8;
    public static final int STATIONERY_URL=9;
    public static  final int DIGIT_URL=10;
    public static final int GAME_URL=11;
    private int ide;
    private Context context;
    private TypeAdapter typeAdapter;
    private HotAdapter hotAdapter;
    public void setupdata(List<T> list, Context context,int id){
        this.ide=id;
        this.context=context;
        updataData(list);
        notifyDataSetChanged();
    }


    @Override
    public int getLayoutId(int viewType) {
        int resId=-1;
        switch (viewType){
            case SKIRT_URL:
                resId=R.layout.item_skirt;
                break;
               case JACKET_URL:
                   resId=R.layout.item_skirt;
                break;
               case PANTS_URL:
                   resId=R.layout.item_skirt;
                break;
               case OVERCOAT_URL:
                   resId=R.layout.item_skirt;
                break;
               case ACCESSORY_URL:
                   resId=R.layout.item_skirt;
                break;
               case BAG_URL:
                   resId=R.layout.item_skirt;
                break;
               case DRESS_UP_URL:
                   resId=R.layout.item_skirt;
                break;
               case HOME_PRODUCTS_URL:
                   resId=R.layout.item_skirt;
                break;
               case STATIONERY_URL:
                   resId=R.layout.item_skirt;
                break;
               case DIGIT_URL:
                   resId=R.layout.item_skirt;
                break;
               case GAME_URL:
                   resId=R.layout.item_skirt;
                break;

        }
        return resId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        RecyclerView view = holder.getView(R.id.rv);
        RecyclerView view1 = holder.getView(R.id.rv1);
        switch (ide){
            case SKIRT_URL:
              SkirtBean.ResultBean skirt= (SkirtBean.ResultBean) itemData;
                List<SkirtBean.ResultBean.ChildBean> child = skirt.getChild();
                List<SkirtBean.ResultBean.HotProductListBean> hot_product_list = skirt.getHot_product_list();
                if (BuildConfig.DEBUG) Log.d("ClassifyAdapter", "child:" + child);
                hotAdapter=new HotAdapter(hot_product_list);
                view.setAdapter(hotAdapter);
                view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                typeAdapter=new TypeAdapter(child);
                view1.setAdapter(typeAdapter);
                view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                break;
            case JACKET_URL:
                JacketBean.ResultBean jack= (JacketBean.ResultBean) itemData;
                List<JacketBean.ResultBean.ChildBean> child1 = jack.getChild();
                List<JacketBean.ResultBean.HotProductListBean> hot_product_list1 = jack.getHot_product_list();
                HotoneAdapter hotoneAdapter=new HotoneAdapter(hot_product_list1);
                view.setAdapter(hotoneAdapter);
                view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                TypeoneAdapter typeoneAdapter=new TypeoneAdapter(child1);
                view1.setAdapter(typeoneAdapter);
                view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                break;
                case PANTS_URL:
                    PantsBean.ResultBean paint= (PantsBean.ResultBean) itemData;
                    List<PantsBean.ResultBean.ChildBean> child2 = paint.getChild();
                    List<PantsBean.ResultBean.HotProductListBean> hot_product_list2 = paint.getHot_product_list();
                    HotTwoAdapter hottwoAdapter=new HotTwoAdapter(hot_product_list2);
                    view.setAdapter(hottwoAdapter);
                    view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                    TypeTwoAdapter typetwoAdapter=new TypeTwoAdapter(child2);
                    view1.setAdapter(typetwoAdapter);
                    view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                    break;
                case OVERCOAT_URL:
                    Overconat.ResultBean over= (Overconat.ResultBean) itemData;
                    List<Overconat.ResultBean.ChildBean> child3 = over.getChild();
                    List<Overconat.ResultBean.HotProductListBean> hot_product_list3 = over.getHot_product_list();
                    HotThressAdapter hotThressAdapter=new HotThressAdapter(hot_product_list3);
                    view.setAdapter(hotThressAdapter);
                    view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                    TypeThreeAdapter typeThreeAdapter=new TypeThreeAdapter(child3);
                    view1.setAdapter(typeThreeAdapter);
                    view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                    break;
                case ACCESSORY_URL:
                    AccessoryBean.ResultBean access= (AccessoryBean.ResultBean) itemData;
                    List<AccessoryBean.ResultBean.ChildBean> child4 = access.getChild();
                    List<AccessoryBean.ResultBean.HotProductListBean> hot_product_list4 = access.getHot_product_list();
                    HotFourAdapter hotFourAdapter=new HotFourAdapter(hot_product_list4);
                    view.setAdapter(hotFourAdapter);
                    view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                    TypeFourAdapter typeFourAdapter=new TypeFourAdapter(child4);
                    view1.setAdapter(typeFourAdapter);
                    view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
                case BAG_URL:
                    BagBean.ResultBean bag= (BagBean.ResultBean) itemData;
                    List<BagBean.ResultBean.ChildBean> child5 = bag.getChild();
                    List<BagBean.ResultBean.HotProductListBean> hot_product_list5 = bag.getHot_product_list();
                    HotFiveAdapter hotFiveAdapter=new HotFiveAdapter(hot_product_list5);
                    view.setAdapter(hotFiveAdapter);
                    view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                    TypeFiveAdapter typeFiveAdapter=new TypeFiveAdapter(child5);
                    view1.setAdapter(typeFiveAdapter);
                    view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
                case DRESS_UP_URL:
                    DressBean.ResultBean dre= (DressBean.ResultBean) itemData;
                    List<DressBean.ResultBean.ChildBean> child6 = dre.getChild();
                    List<DressBean.ResultBean.HotProductListBean> hot_product_list6 = dre.getHot_product_list();
                    HotsixAdapter hotsixAdapter=new HotsixAdapter(hot_product_list6);
                    view.setAdapter(hotsixAdapter);
                    view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

                    TypesixAdapter typesixAdapter=new TypesixAdapter(child6);
                    view1.setAdapter(typesixAdapter);
                    view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
                case HOME_PRODUCTS_URL:
                break;
                case STATIONERY_URL:
                break;
                case DIGIT_URL:
                break;
                case GAME_URL:
                break;


        }
    }

    @Override
    public int getRootViewType(int position) {
        int type=-1;
        switch (position){
            case 0:
                type=SKIRT_URL;
                break;
            case 2:
                type=JACKET_URL;
                break;
                 case 3:
                type=PANTS_URL;
                break;
                 case 4:
                type=OVERCOAT_URL;
                break;
                 case 5:
                type=ACCESSORY_URL;
                break;
                 case 6:
                type=BAG_URL;
                break;
                 case 7:
                type=DRESS_UP_URL;
                break;
                 case 8:
                type=HOME_PRODUCTS_URL;
                break;
                 case 9:
                type=STATIONERY_URL;
                break;
                 case 10:
                type=DIGIT_URL;
                break;
                 case 11:
                type=GAME_URL;
                break;

        }
        return type;
    }
}
