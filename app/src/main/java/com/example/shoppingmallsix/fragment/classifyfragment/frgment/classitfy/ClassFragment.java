package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;


import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.ClassLeftBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter.ClassLeftAdapter;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter.ClassRightAdapter;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.manager.FullyLinearLayoutManager;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.recycler.myRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends BaseFragment<ClassPresenter> implements IClassView, BaseRvAdapter.IRecyclerItemClickListener {

    private RecyclerView classifyLeftRecyclerView;
    private myRecyclerView classifyRightRecyclerView;
    // "", "", "", "", "", "", "", "", ""
    private String[] strings = new String[]{getString(R.string.skirt), getString(R.string.jacket),getString(R.string.jacket)
            ,getString(R.string.Downloading),getString(R.string.coat),getString(R.string.accessories),getString(R.string.handbag),
            getString(R.string.dress),getString(R.string.Thehouseistasted),getString(R.string.Surroundingthedata),getString(R.string.Theamezone)};
    private List<ClassLeftBean> list = new ArrayList<>();
    private ClassLeftAdapter classifyAdapter;
    private List<Object> objectList = new ArrayList<>();
    private ClassRightAdapter<Object> resultBeanClassRightAdapter;
    private int Types = 0;

    @Override
    protected void initPresenter() {
        httpPresenter = new ClassPresenter(this);
    }

    private void inits() {
        initRecyclerView();
    }

    @Override
    protected void initData() {
        httpPresenter.getSkirtData();
        //存储list集合
        for (int i = 0; i < strings.length; i++) {
            ClassLeftBean classLeftBean = new ClassLeftBean();
            if (i == 0) {
                classLeftBean.setaBoolean(true);
            } else {
                classLeftBean.setaBoolean(false);
            }
            classLeftBean.setString(strings[i]);
            list.add(classLeftBean);
        }
        inits();
    }

    private void initRecyclerView() {
        //左布局初始化
        classifyAdapter = new ClassLeftAdapter(list);
        classifyAdapter.setiRecyclerItemClickListener(this);
        classifyLeftRecyclerView.setAdapter(classifyAdapter);
        classifyLeftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //右布局初始化
        //Top
        resultBeanClassRightAdapter = new ClassRightAdapter<>();
        classifyRightRecyclerView.setAdapter(resultBeanClassRightAdapter);
        classifyRightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initView() {
        classifyLeftRecyclerView = (RecyclerView) mBaseView.findViewById(R.id.classify_left_recycler_view);
        classifyRightRecyclerView = (myRecyclerView) mBaseView.findViewById(R.id.classify_right_recycler_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class;
    }

    @Override
    public void onItemClick(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (position == i) {
                list.get(i).setaBoolean(true);
                switch (strings[i]) {
                    case "小裙子":
                        httpPresenter.getSkirtData();
                        break;
                    case "上衣":
                        httpPresenter.getJacketData();
                        break;
                    case "下装":
                        httpPresenter.getPantsBean();
                        break;
                    case "外套":
                        httpPresenter.getOvercoatBean();
                        break;
                    case "配件":
                        httpPresenter.getAccrssoryBean();
                        break;
                    case "包包":
                        httpPresenter.getBagBean();
                        break;
                    case "装扮":
                        httpPresenter.getDressBean();
                        break;
                    case "居家宅品":
                        httpPresenter.getProductsBean();
                        break;
                    case "办公文具":
                        httpPresenter.getStationeryBean();
                        break;
                    case "数据周边":
                        httpPresenter.getDigitBean();
                        break;
                    case "游戏专区":
                        httpPresenter.getGameBean();
                        break;
                }


            } else {
                list.get(i).setaBoolean(false);
            }
        }
        classifyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItwmLongClick(int position) {

    }


    @Override
    public void onSkirtData(SkirtBean skirtBean) {

        objectList.clear();
        List<SkirtBean.ResultBean> skirtBeanResult = skirtBean.getResult();
        objectList.addAll(skirtBeanResult);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.SKIRT_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onJacketData(JacketBean jacketBean) {
        objectList.clear();
        List<JacketBean.ResultBean> result = jacketBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.JACKET_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());

    }

    @Override
    public void onPantsBean(PantsBean pantsBean) {
        objectList.clear();
        List<PantsBean.ResultBean> result = pantsBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.PANTS_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onOvercoatBean(OvercoatBean overcoatBean) {
        objectList.clear();
        List<OvercoatBean.ResultBean> result = overcoatBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.OVERCOAT_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onAccrssoryBean(AccrssoryBean accrssoryBean) {
        objectList.clear();
        List<AccrssoryBean.ResultBean> result = accrssoryBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.ACCRSSORY_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onBagBean(BagBean bagBean) {
        objectList.clear();
        List<BagBean.ResultBean> result = bagBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.BAG_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onDressBean(DressBean dressBean) {
        objectList.clear();
        List<DressBean.ResultBean> result = dressBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.DRESS_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onProductsBean(ProductsBean productsBean) {
        objectList.clear();
        List<ProductsBean.ResultBean> result = productsBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.PRODUCTS_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onStationeryBean(StationeryBean stationeryBean) {
        objectList.clear();
        List<StationeryBean.ResultBean> result = stationeryBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.STATIONERY_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onDigitBean(DigitBean digitBean) {
        objectList.clear();
        List<DigitBean.ResultBean> result = digitBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.DIGIT_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void onGameBean(GameBean gameBean) {
        objectList.clear();
        List<GameBean.ResultBean> result = gameBean.getResult();
        objectList.addAll(result);
        loadingPage.showSuccessView();
        //设置传串的内容
        Types = ClassRightAdapter.GAME_TYPE;
        resultBeanClassRightAdapter.setUpdateData(objectList, Types, getActivity());
    }

    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        loadingPage.showError(msg);
    }
}