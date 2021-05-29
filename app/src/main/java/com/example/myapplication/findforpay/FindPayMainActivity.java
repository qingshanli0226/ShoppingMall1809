package com.example.myapplication.findforpay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.framework.BaseActivity;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.payorder.OrderActivity;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.example.pay.PayActivity;

import java.util.Map;

public class FindPayMainActivity extends BaseActivity<FindpayPresenter> implements IFindPayView {


    private androidx.recyclerview.widget.RecyclerView rv;
    private FindPayAdapter findPayAdapter;
    public static final String APPID = "2016102100732196";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCetpoOv2BedP/Iv1ynicEQ3Bpl3Rmd1Q8EeNs3ljNCR1fQURYYasgRUJPohzsGVlLtPJsPd+jTNqqImsbY/GPuuPgifDQ+e5PnPWZrngm9Ru91wTqi5SIq/BkpN1nF5HIrHs5rRzyBcKcX82fkPBd5N2KNovD+8Biz+pdWuHVOjbCZ7MVCc/LfpwMI9QwZXMrEsm4PvqsnnnlNB3l3gqb110zgqZLX9hckP42+fikYNGxKLFF4zYZ0DRLkGjtLCse2KXgX3Tr4+/BQpxnyGhZMtgFoO9JXQGo0UER9D2FGc7LZCWschyGETLeJwWKIjZXIZD57gfPCK37A+7R7vejnAgMBAAECggEAbNSToSczorGhr3sgwrVgEPqMk2rgJO0zBgMFdwFklr8rBOqFNysJk23obltEax0IcirLvPihSyvCFjfjwGiY8doeNC9s96dvjPH6aDMPRJ3+l4VvesGaA1Wovy14Po1eiBjwvHk1kSC5Q2AhzkwyYGlNCAhCLt5eYhOkcM+9iKXlkISUSdA+t1ER9k5BeM10XnsZX/gl1yYW6fnxQ/XKsfeLmbLRhFbBHd6UwqcBWZqKN8lCh1h6JOF0jLuVByMMJzyXf8yKeQY2iNmUCvJbb/7bePvYoMVKrQzlyHonnM4V7mCxiyOb6740SILJes5N9g/Fu09V/Tfv+hJRWfYZYQKBgQDKvXfkYCrAv4amHDwmaBY57laHAF0LE6tQy8OMuT+BzzAJBx/j3uxJquAuAnHZQS9GR93W5li7fUKRtviM4eBrPjh/lJXAxKJn3JAcqpcXQ0f3DC1pAtOzGjgywqcPuNTu+NygUpPkjtPFBbFynElK2ruEnhBgrH39ARqbGx3WlwKBgQDIaEX6t0As0CQ6uc4BI2+niehmH2LG8ELO0S8UWjCJSB0oq3p0iTws190soQHzITsBWADvSs3hHEP/3LY13FhGNF0lLAkO3CsBcA/IpTaF0oS+lwI6hd2zA/iHLzeD44EGTpJc+HZMuZ+GTf7NRdMNFAKbvDQbPe4w1EEf3reaMQKBgQCaT5/jiXbBAoYgBLmbmfng2hGt647mEXCBrLYIdC9sRCCRnoSdUl2SrKa5Hk89RyoOWkD1gpnjCrISaqu/v2Sq+87Q/G0HLiNW3kAqMYWSxTkPRouBtA8h8UD5EcNKaipYQb7boD7E5hk1iuHHFEGM4fN8OzrH+kJiweZYTElnvQKBgF8Iw5ae67nUgjmu/reffEUwqpoy6/521NeKbw7xre6L2ff9STaWFYkWXHXbbDdFXNvIRbkz+el0I/LjUSy9bsbr8fe8qBb55RLrdzCo1/Ah4n0W0yG5dWZ8zZAdne/XJMo+3D1mPYMoyzM/LUNehzS+dnYvi24XsipJnRBl5x8hAoGAd0W7wYvjdoy0xsPldZxqco5f00e3Ka3SYWbIkYPr6wvxoyWwy+VJMWzXEDLXIIRNrcKrpu1Y2XVAZjsHp4ZYjZDjYaRTOcTb8IZ1Ps+ODWFypxZ9VdQBztqp59a8k4fqNM62UHKL/slJ2X4YtSdXaJMJO8kcvA9TUbodoPi9hX8=";
    public static final String RSA_PRIVATE = "";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @Override
    protected int bandLayout() {
        return R.layout.activity_find_pay_main;
    }


    @Override
    public void initView() {
        rv = findViewById(R.id.rv);
        findPayAdapter = new FindPayAdapter();

    }

    @Override
    public void initPresenter() {
        findPayAdapter.setRecyclerItemClickListener(new BaseRecyclerViewAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FindPayMainActivity.this);
                builder.setMessage("确认支付该订单");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FindPayMainActivity.this, PayActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }

    @Override
    public void initData() {
        mPresenter = new FindpayPresenter(this);
        mPresenter.getFindPay();

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


    @Override
    public void destroy() {
        super.destroy();

    }

    @Override
    public void onFindPay(FindForPayBean findForPayBean) {
        findPayAdapter.updataData(findForPayBean.getResult());
        rv.setAdapter(findPayAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFindSend(FindForSendBean findForSendBean) {

    }




}
