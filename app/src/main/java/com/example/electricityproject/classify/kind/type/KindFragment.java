package com.example.electricityproject.classify.kind.type;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.KindAccessoryBean;
import com.example.common.bean.KindBagBean;
import com.example.common.bean.KindDigitBean;
import com.example.common.bean.KindDressBean;
import com.example.common.bean.KindGameBean;
import com.example.common.bean.KindHomeProductsBean;
import com.example.common.bean.KindJacketBean;
import com.example.common.bean.KindOvercoatBean;
import com.example.common.bean.KindPantsBean;
import com.example.common.bean.KindSkirtBean;
import com.example.common.bean.KindStationeryBean;
import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.electricityproject.classify.kind.adapter.KindAdapter;
import com.example.electricityproject.classify.kind.adapter.KindBean;
import com.example.electricityproject.classify.kind.adapter.accessory.AccessoryChildAdapter;
import com.example.electricityproject.classify.kind.adapter.accessory.AccessoryHotAdapter;
import com.example.electricityproject.classify.kind.adapter.bag.BagChildAdapter;
import com.example.electricityproject.classify.kind.adapter.bag.BagHotAdapter;
import com.example.electricityproject.classify.kind.adapter.digit.DigitChildAdapter;
import com.example.electricityproject.classify.kind.adapter.digit.DigitHotAdapter;
import com.example.electricityproject.classify.kind.adapter.dress.DressChildAdapter;
import com.example.electricityproject.classify.kind.adapter.dress.DressHotAdapter;
import com.example.electricityproject.classify.kind.adapter.game.GameChildAdapter;
import com.example.electricityproject.classify.kind.adapter.game.GameHotAdapter;
import com.example.electricityproject.classify.kind.adapter.homeproducts.HomePduHotAdapter;
import com.example.electricityproject.classify.kind.adapter.homeproducts.HomeProsutsChildAdapter;
import com.example.electricityproject.classify.kind.adapter.jacket.JacketChildAdapter;
import com.example.electricityproject.classify.kind.adapter.jacket.JacketHotAdapter;
import com.example.electricityproject.classify.kind.adapter.overcoat.OvercoatChildAdapter;
import com.example.electricityproject.classify.kind.adapter.overcoat.OvercoatHotAdapter;
import com.example.electricityproject.classify.kind.adapter.pant.PantChildAdapter;
import com.example.electricityproject.classify.kind.adapter.pant.PantHotAdapter;
import com.example.electricityproject.classify.kind.adapter.skirt.SkirtChildAdapter;
import com.example.electricityproject.classify.kind.adapter.skirt.SkirtHotAdapter;
import com.example.electricityproject.classify.kind.adapter.stationery.StationeryChildAdapter;
import com.example.electricityproject.classify.kind.adapter.stationery.StationeryHotAdapter;
import com.example.electricityproject.classify.kind.type.IKindPresenter;
import com.example.electricityproject.classify.kind.type.IKindView;
import com.example.framework.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class KindFragment extends BaseFragment<IKindPresenter> implements IKindView {

    private List<KindBean> list = new ArrayList<>();
    private ListView kindListview;
    private RecyclerView recommendRv;
    private RecyclerView commonRv;
    private SkirtHotAdapter skirtHotAdapter;
    private List<KindSkirtBean.ResultBean.HotProductListBean> arrayList = new ArrayList<>();
    private SkirtChildAdapter skirtChildAdapter;


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
                switch (position){
                    case 0:
                        httpPresenter.getSkirtData();
                        break;
                    case 1:
                        httpPresenter.getJacketData();
                        break;
                    case 2:
                        httpPresenter.getPantData();
                        break;
                    case 3:
                        httpPresenter.getOvercoatData();
                        break;
                    case 4:
                        httpPresenter.getAccessoryData();
                        break;
                    case 5:
                        httpPresenter.getBagData();
                        break;
                    case 6:
                        httpPresenter.getDressData();
                        break;
                    case 7:
                        httpPresenter.getHomeProducts();
                        break;
                    case 8:
                        httpPresenter.getStationeryData();
                        break;
                    case 9:
                        httpPresenter.getDigitData();
                        break;
                    case 10:
                        httpPresenter.getGameData();
                        break;
                }
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
        recommendRv = (RecyclerView) findViewById(R.id.recommend_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recommendRv.setLayoutManager(linearLayoutManager);
        commonRv = (RecyclerView) findViewById(R.id.common_rv);
        commonRv.setLayoutManager(new GridLayoutManager(getContext(),3));
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
    public void onSkirtData(KindSkirtBean kindSkirtBean) {

        List<KindSkirtBean.ResultBean.HotProductListBean> hot_product_list = kindSkirtBean.getResult().get(0).getHot_product_list();
        List<KindSkirtBean.ResultBean.ChildBean> childBeanList = kindSkirtBean.getResult().get(0).getChild();
        skirtHotAdapter = new SkirtHotAdapter();
        skirtChildAdapter = new SkirtChildAdapter();
        skirtHotAdapter.updateData(hot_product_list);
        skirtChildAdapter.updateData(childBeanList);
        recommendRv.setAdapter(skirtHotAdapter);
        commonRv.setAdapter(skirtChildAdapter);
    }

    @Override
    public void onJacketData(KindJacketBean kindJacketBean) {

        List<KindJacketBean.ResultBean.HotProductListBean> hot_product_list = kindJacketBean.getResult().get(0).getHot_product_list();
        List<KindJacketBean.ResultBean.ChildBean> child = kindJacketBean.getResult().get(0).getChild();
        JacketChildAdapter jacketChildAdapter = new JacketChildAdapter();
        jacketChildAdapter.updateData(child);
        commonRv.setAdapter(jacketChildAdapter);
        JacketHotAdapter jacketHotAdapter = new JacketHotAdapter();
        jacketHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(jacketHotAdapter);

    }

    @Override
    public void onPantData(KindPantsBean kindPantsBean) {
        List<KindPantsBean.ResultBean.HotProductListBean> hot_product_list = kindPantsBean.getResult().get(0).getHot_product_list();
        List<KindPantsBean.ResultBean.ChildBean> child = kindPantsBean.getResult().get(0).getChild();
        PantChildAdapter pantChildAdapter = new PantChildAdapter();
        pantChildAdapter.updateData(child);
        commonRv.setAdapter(pantChildAdapter);
        PantHotAdapter pantHotAdapter = new PantHotAdapter();
        pantHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(pantHotAdapter);
    }

    @Override
    public void onOvercoatData(KindOvercoatBean kindOvercoatBean) {
        List<KindOvercoatBean.ResultBean.HotProductListBean> hot_product_list = kindOvercoatBean.getResult().get(0).getHot_product_list();
        List<KindOvercoatBean.ResultBean.ChildBean> child = kindOvercoatBean.getResult().get(0).getChild();
        OvercoatChildAdapter overcoatChildAdapter = new OvercoatChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        OvercoatHotAdapter overcoatHotAdapter = new OvercoatHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }

    @Override
    public void onAccessoryData(KindAccessoryBean kindAccessoryBean) {
        List<KindAccessoryBean.ResultBean.HotProductListBean> hot_product_list = kindAccessoryBean.getResult().get(0).getHot_product_list();
        List<KindAccessoryBean.ResultBean.ChildBean> child = kindAccessoryBean.getResult().get(0).getChild();
        AccessoryChildAdapter overcoatChildAdapter = new AccessoryChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        AccessoryHotAdapter overcoatHotAdapter = new AccessoryHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }

    @Override
    public void onBagData(KindBagBean kindBagBean) {
        List<KindBagBean.ResultBean.HotProductListBean> hot_product_list = kindBagBean.getResult().get(0).getHot_product_list();
        List<KindBagBean.ResultBean.ChildBean> child = kindBagBean.getResult().get(0).getChild();
        BagChildAdapter overcoatChildAdapter = new BagChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        BagHotAdapter overcoatHotAdapter = new BagHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }

    @Override
    public void onDressBean(KindDressBean kindDressBean) {
        List<KindDressBean.ResultBean.HotProductListBean> hot_product_list = kindDressBean.getResult().get(0).getHot_product_list();
        List<KindDressBean.ResultBean.ChildBean> child = kindDressBean.getResult().get(0).getChild();
        DressChildAdapter overcoatChildAdapter = new DressChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        DressHotAdapter overcoatHotAdapter = new DressHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }

    @Override
    public void onHomeProductsBean(KindHomeProductsBean kindHomeProductsBean) {
        List<KindHomeProductsBean.ResultBean.HotProductListBean> hot_product_list = kindHomeProductsBean.getResult().get(0).getHot_product_list();
        List<KindHomeProductsBean.ResultBean.ChildBean> child = kindHomeProductsBean.getResult().get(0).getChild();
        HomeProsutsChildAdapter overcoatChildAdapter = new HomeProsutsChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        HomePduHotAdapter overcoatHotAdapter = new HomePduHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }

    @Override
    public void onStationeryBean(KindStationeryBean kindStationeryBean) {
        List<KindStationeryBean.ResultBean.HotProductListBean> hot_product_list = kindStationeryBean.getResult().get(0).getHot_product_list();
        List<KindStationeryBean.ResultBean.ChildBean> child = kindStationeryBean.getResult().get(0).getChild();
        StationeryChildAdapter overcoatChildAdapter = new StationeryChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        StationeryHotAdapter overcoatHotAdapter = new StationeryHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }

    @Override
    public void onDigitBean(KindDigitBean kindDigitBean) {
        List<KindDigitBean.ResultBean.HotProductListBean> hot_product_list = kindDigitBean.getResult().get(0).getHot_product_list();
        List<KindDigitBean.ResultBean.ChildBean> child = kindDigitBean.getResult().get(0).getChild();
        DigitChildAdapter overcoatChildAdapter = new DigitChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        DigitHotAdapter overcoatHotAdapter = new DigitHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }

    @Override
    public void onGameBean(KindGameBean kindGameBean) {
        List<KindGameBean.ResultBean.HotProductListBean> hot_product_list = kindGameBean.getResult().get(0).getHot_product_list();
        List<KindGameBean.ResultBean.ChildBean> child = kindGameBean.getResult().get(0).getChild();
        GameChildAdapter overcoatChildAdapter = new GameChildAdapter();
        overcoatChildAdapter.updateData(child);
        commonRv.setAdapter(overcoatChildAdapter);
        GameHotAdapter overcoatHotAdapter = new GameHotAdapter();
        overcoatHotAdapter.updateData(hot_product_list);
        recommendRv.setAdapter(overcoatHotAdapter);
    }
}