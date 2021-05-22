package com.example.electricityproject.classify.kind.type;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.example.common.Constants;
import com.example.common.bean.GoodsBean;
=======
import com.example.adapter.BaseAdapter;
import com.example.common.bean.KindAccessoryBean;
import com.example.common.bean.KindBagBean;
import com.example.common.bean.KindDigitBean;
import com.example.common.bean.KindDressBean;
import com.example.common.bean.KindGameBean;
import com.example.common.bean.KindHomeProductsBean;
import com.example.common.bean.KindJacketBean;
import com.example.common.bean.KindOvercoatBean;
import com.example.common.bean.KindPantsBean;
>>>>>>> refs/remotes/origin/four
import com.example.common.bean.KindSkirtBean;
import com.example.common.bean.LogBean;
<<<<<<< HEAD
=======
import com.example.electricityproject.details.DetailsActivity;
>>>>>>> refs/remotes/origin/four
import com.example.electricityproject.R;
import com.example.electricityproject.classify.kind.adapter.KindAdapter;
import com.example.electricityproject.classify.kind.adapter.KindBean;
import com.example.electricityproject.classify.kind.adapter.TypeAdapter;
import com.example.framework.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class KindFragment extends BaseFragment<IKindPresenter> implements IKindView {

    private List<KindBean> list = new ArrayList<>();
    private List<Object> objectList = new ArrayList<>();
    private ListView kindListview;
    private List<KindSkirtBean.ResultBean.HotProductListBean> arrayList = new ArrayList<>();
    private String[] url = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL, Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL, Constants.DIGIT_URL, Constants.GAME_URL};
    private RecyclerView typeRv;
    private TypeAdapter typeAdapter;


    @Override
    protected void initData() {

        list.add(new KindBean("小裙子"));
        list.add(new KindBean("上衣"));
        list.add(new KindBean("下装"));
        list.add(new KindBean("外套"));
        list.add(new KindBean("配件"));
        list.add(new KindBean("包包"));
        list.add(new KindBean("装扮"));
        list.add(new KindBean("居家宅品"));
        list.add(new KindBean("办公文具"));
        list.add(new KindBean("数据周边"));
        list.add(new KindBean("游戏专区"));


        KindAdapter kindAdapter = new KindAdapter(getContext(), R.layout.item_kind, list);
        kindListview.setAdapter(kindAdapter);


        kindListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kindAdapter.setSelectedPosition(position);
                kindAdapter.notifyDataSetChanged();

                httpPresenter.getTypeData(url[position]);

            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new IKindPresenter(this);
    }

    @Override
    protected void initView() {
        kindListview = (ListView) findViewById(R.id.kind_listview);
        typeRv = (RecyclerView) findViewById(R.id.type_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        typeRv.setLayoutManager(manager);
        typeAdapter=new TypeAdapter(getContext());
        typeRv.setAdapter(typeAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kind;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }


    @Override
    public void onTypeData(GoodsBean goodsBean) {

        objectList.clear();

        List<GoodsBean.ResultBean> result = goodsBean.getResult();
        List<GoodsBean.ResultBean.HotProductListBean> hot_product_list = result.get(0).getHot_product_list();
        List<GoodsBean.ResultBean.ChildBean> child = result.get(0).getChild();
        objectList.add(hot_product_list);
        objectList.add(child);
        typeAdapter.updateData(objectList);


    }
}