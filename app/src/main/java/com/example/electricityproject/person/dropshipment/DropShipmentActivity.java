package com.example.electricityproject.person.dropshipment;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.FindForSendBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.view.ToolBar;

public class DropShipmentActivity extends BaseActivity<DropShipmentPresenter> implements IDropShipmentView{

    private ToolBar toolbar;
    private RecyclerView dropRe;
    private DropShipmentAdapter dropShipmentAdapter;

    @Override
    protected void initData() {
        httpPresenter=new DropShipmentPresenter(this);
        httpPresenter.getDropShipment();

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

        dropRe.setLayoutManager(new LinearLayoutManager(this));
        dropShipmentAdapter = new DropShipmentAdapter();
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
    public void onDropShipmentBean(FindForSendBean findForSendBean) {
        Log.i("zx", "onDropShipmentBean: "+findForSendBean.toString());
        if (findForSendBean.getCode().equals("200")){
            dropShipmentAdapter.updateData(findForSendBean.getResult());
            dropShipmentAdapter.notifyDataSetChanged();
        }
    }
}