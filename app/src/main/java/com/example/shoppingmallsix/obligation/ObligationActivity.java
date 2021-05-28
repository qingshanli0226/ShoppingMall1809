package com.example.shoppingmallsix.obligation;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.find.FindForSendbean;
import com.example.shoppingcar.BuildConfig;
import com.example.shoppingmallsix.R;

import java.util.ArrayList;
import java.util.List;

public class ObligationActivity extends BaseActivity<ObligationPresenter> implements Iobligation {
    private ToolBar toolbar;
    private  List<FindForSendbean.ResultBean> list = new ArrayList<>();
    ObligationPresenter obligationPresenter;
    private RecyclerView rv;
    private  ObligationAdapter obligationAdapter;

    @Override
    protected void initPresenter() {
       obligationPresenter = new ObligationPresenter(this);

    }

    @Override
    protected void initData() {
        obligationPresenter.findForSend();
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        obligationAdapter = new ObligationAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(obligationAdapter);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_obligation;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @Override
    public void onfindForSend(FindForSendbean findForSendbean) {
        if (BuildConfig.DEBUG) Log.d("ObligationActivity", "findForSendbean:" + findForSendbean);
        Toast.makeText(this, ""+findForSendbean, Toast.LENGTH_SHORT).show();
        if (findForSendbean.getCode().equals("200")){
            if (BuildConfig.DEBUG) Log.d("ObligationActivity", "findForSendbean:" + findForSendbean.getResult());
            list.addAll(findForSendbean.getResult());
            obligationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
