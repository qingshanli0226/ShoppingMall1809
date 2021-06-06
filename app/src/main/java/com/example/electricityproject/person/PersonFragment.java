package com.example.electricityproject.person;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.TokenSPUtility;
import com.example.common.bean.LogBean;
import com.example.common.bean.OutLogBean;
import com.example.electricityproject.R;
import com.example.electricityproject.person.dropshipment.DropShipmentActivity;
import com.example.electricityproject.person.findforpay.FindForPayActivity;
import com.example.electricityproject.person.zhibo.SinatvActivity;
import com.example.framework.BaseFragment;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

public class PersonFragment extends BaseFragment<PersonPresenter> implements IoutloginView{

    private ToolBar toolbar;
    private TextView pleaseLogin;
    private LogBean logBean;
    private LinearLayout orderPayment;
    private LinearLayout orderShipment;
    private TextView zhibo;

    @Override
    protected void initData() {
        httpPresenter = new PersonPresenter(this);
        //登录则吐司已登录,未登录跳转到登录页面
        pleaseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogBean isLog = BusinessUserManager.getInstance().getIsLog();

                if (BusinessUserManager.getInstance().getIsLog() != null) {
                    pleaseLogin.setText(isLog.getResult().getName() + "");
                    Toast.makeText(getContext(), "当前" + isLog.getResult().getName() + "用户已经登陆", Toast.LENGTH_SHORT).show();
                } else {
                    BusinessARouter.getInstance().getUserManager().OpenLogActivity(getContext(), null);
                }
            }
        });
        //代付款
        orderPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BusinessUserManager.getInstance().getIsLog() != null) {
                    Intent intent = new Intent(getContext(), FindForPayActivity.class);
                    startActivity(intent);
                } else {
                    BusinessARouter.getInstance().getUserManager().OpenLogActivity(getContext(), null);
                }
            }
        });
        //待收货
        orderShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BusinessUserManager.getInstance().getIsLog() != null) {
                    startActivity(new Intent(getActivity(), DropShipmentActivity.class));
                } else {
                    BusinessARouter.getInstance().getUserManager().OpenLogActivity(getContext(), null);
                }
            }
        });

        //直播
        zhibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SinatvActivity.class));
            }
        });
        //退出登录
        mView.findViewById(R.id.outLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BusinessUserManager.getInstance().getIsLog()==null){
                    Toast.makeText(getActivity(), "当前还未登录,无需退出", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("退出登录?");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        httpPresenter.outLogin();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        pleaseLogin = (TextView) findViewById(R.id.please_login);
        orderPayment = (LinearLayout) findViewById(R.id.order_payment);
        orderShipment = (LinearLayout) findViewById(R.id.order_shipment);
        zhibo = (TextView) findViewById(R.id.zhibo);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String error) {
        Log.i("zx", "showError: " + error);
    }

    @Override
    public void onLoginChange(LogBean isLog) {
        if (isLog != null) {
            pleaseLogin.setText("" + isLog.getResult().getName());
        }
    }


    @Override
    public void outLogin(OutLogBean outLogBean) {
        Log.i("zx", "outLogin: "+outLogBean.toString());
        if (outLogBean.getCode().equals("200")){

            pleaseLogin.setText("未登录");
            TokenSPUtility.putString(getContext(),null);
            BusinessUserManager.getInstance().setIsLog(null);

        }
    }


}