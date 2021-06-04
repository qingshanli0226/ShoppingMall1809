package com.example.electricityproject.person.dropshipment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.FindForSendBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.manager.ShopCacheManger;
import com.example.view.ToolBar;

import java.util.List;

public class DropShipmentActivity extends BaseActivity implements ShopCacheManger.iFindShopChangeListener{

    private ToolBar toolbar;
    private RecyclerView dropRe;
    private DropShipmentAdapter dropShipmentAdapter;
    private List<FindForSendBean.ResultBean> findShopList;


    @Override
    protected void initData() {

        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightImgClick() {

            }

            @Override
            public void onRightTvClick() {

            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        dropRe = (RecyclerView) findViewById(R.id.drop_re);
        ShopCacheManger.getInstance().registerFindShopChange(this);
        dropRe.setLayoutManager(new LinearLayoutManager(this));
        dropShipmentAdapter = new DropShipmentAdapter();
        if (ShopCacheManger.getInstance().getFindShopList()!=null){
           findShopList = ShopCacheManger.getInstance().getFindShopList();
           dropShipmentAdapter.updateData(findShopList);
        }else {
            ShopCacheManger.getInstance().getDropShipment();
        }
        dropRe.setAdapter(dropShipmentAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drop_shipment;
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShopCacheManger.getInstance().unregisterFindShopChange(this);
    }

    @Override
    public void OnFindShopChange() {
        findShopList=ShopCacheManger.getInstance().getFindShopList();
        dropShipmentAdapter.updateData(findShopList);
        dropShipmentAdapter.notifyDataSetChanged();
    }
}