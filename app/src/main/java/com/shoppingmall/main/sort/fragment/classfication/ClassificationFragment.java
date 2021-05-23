package com.shoppingmall.main.sort.fragment.classfication;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.sort.fragment.adapter.ClassificationAdapter;
import com.shoppingmall.main.sort.fragment.adapter.ClassificationContentAdapter;
import com.shoppingmall.net.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;


public class ClassificationFragment extends BaseFragment<ClassificationPresenter> implements IClassification {

    private String[] urls = new String[]{Constants.SKIRT_URL,
            Constants.JACKET_URL, Constants.PANTS_URL,
            Constants.OVERCOAT_URL, Constants.ACCESSORY_URL,
            Constants.BAG_URL, Constants.DRESS_UP_URL,
            Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL,
            Constants.GAME_URL};
    private List<Object> list=new ArrayList<>();

    private RecyclerView classificationTitleRv;
    private RecyclerView classificationContentRv;
    private ClassificationContentAdapter classificationContentAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_classification;
    }

    @Override
    public void initView() {
        classificationTitleRv = (RecyclerView) mView.findViewById(R.id.classificationTitleRv);
        classificationContentRv = (RecyclerView) mView.findViewById(R.id.classificationContentRv);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new ClassificationPresenter(this);
    }

    @Override
    public void initData() {
        httpPresenter.getGoodsData(urls[0]);

        List<String> classificationTitleList = new ArrayList<>();
        classificationTitleList.add(getString(R.string.SKIRT));
        classificationTitleList.add(getString(R.string.JACKET_URL));
        classificationTitleList.add(getString(R.string.PANTS_URL));
        classificationTitleList.add(getString(R.string.OVERCOAT_URL));
        classificationTitleList.add(getString(R.string.ACCESSORY_URL));
        classificationTitleList.add(getString(R.string.BAG_URL));
        classificationTitleList.add(getString(R.string.DRESS_UP_URL));
        classificationTitleList.add(getString(R.string.HOME_PRODUCTS_URL));
        classificationTitleList.add(getString(R.string.STATIONERY_URL));
        classificationTitleList.add(getString(R.string.DIGIT_URL));
        classificationTitleList.add(getString(R.string.GAME_URL));
        ClassificationAdapter classificationAdapter = new ClassificationAdapter();
        classificationTitleRv.setLayoutManager(new LinearLayoutManager(getContext()));
        classificationTitleRv.setAdapter(classificationAdapter);
        classificationAdapter.updateData(classificationTitleList);

        classificationContentAdapter = new ClassificationContentAdapter();
        classificationContentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        classificationContentRv.setAdapter(classificationContentAdapter);

        classificationAdapter.setRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                classificationAdapter.setPosition(position);
                classificationAdapter.notifyDataSetChanged();
                httpPresenter.getGoodsData(urls[position]);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }

    @Override
    public void getGoods(GoodsBean goodsBean) {
        list.clear();
        List<GoodsBean.ResultBean> result = goodsBean.getResult();
        List<GoodsBean.ResultBean.ChildBean> child = result.get(0).getChild();
        List<GoodsBean.ResultBean.HotProductListBean> hots = result.get(0).getHot_product_list();
        list.add(hots);
        list.add(child);
        classificationContentAdapter.updateData(list);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        loadingPage.showTransparentLoadingView();
    }
}