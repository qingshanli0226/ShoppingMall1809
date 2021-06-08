package com.example.electricityproject.person;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;

import org.greenrobot.eventbus.EventBus;

public class PersonFragment extends BaseFragment<PersonPresenter> implements IoutloginView{

    private ToolBar toolbar;
    private TextView pleaseLogin;
    private LogBean logBean;
    private LinearLayout orderPayment;
    private LinearLayout orderShipment;
    private LinearLayout liveStreaming;
    private LinearLayout tell;
    private LinearLayout feedback;

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
                    Toast.makeText(getContext(), getResources().getString(R.string.person_now) + isLog.getResult().getName() + getResources().getString(R.string.person_Already_Login), Toast.LENGTH_SHORT).show();
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
        liveStreaming.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(getActivity(), getResources().getString(R.string.person_no_NoLogin), Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getResources().getString(R.string.person_exit_login));
                builder.setPositiveButton(getResources().getString(R.string.person_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        httpPresenter.outLogin();
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.person_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        //打电话
        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+10086));
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogBean isLog = BusinessUserManager.getInstance().getIsLog();
                if (isLog!=null){
                    EMClient.getInstance().login(isLog.getResult().getName(), isLog.getResult().getPassword(), new EMCallBack(){
                        @Override
                        public void onSuccess() {
                            Intent intent = new Intent(getContext(), ChatActivity.class);
                            //username为对方的环信id
                            intent.putExtra(EaseConstant.EXTRA_USER_ID,"zx");
                            startActivity(intent);
                        }

                        @Override
                        public void onError(int code, String error) {
                            Log.i("zx", "onError: "+error);
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }
                    });
                }else {
                    BusinessARouter.getInstance().getUserManager().OpenLogActivity(getContext(), null);
                }
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = mView.findViewById(R.id.toolbar);
        pleaseLogin = mView.findViewById(R.id.please_login);
        orderPayment = mView.findViewById(R.id.order_payment);
        orderShipment = mView.findViewById(R.id.order_shipment);
        liveStreaming = mView.findViewById(R.id.liveStreaming);
        feedback = mView.findViewById(R.id.feedback);
        tell = mView.findViewById(R.id.tell);
        //加入动态权限
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    Manifest.permission.CALL_PHONE
            },100);
        }
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

    }

    @Override
    public void onLoginChange(LogBean isLog) {
        if (isLog != null) {
            pleaseLogin.setText("" + isLog.getResult().getName());
        }
    }

    @Override
    public void outLogin(OutLogBean outLogBean) {
        if (outLogBean.getCode().equals("200")){
            pleaseLogin.setText(getResources().getString(R.string.person_no_Login));
            TokenSPUtility.putString(getContext(),null);
            BusinessUserManager.getInstance().setIsLog(null);
            EventBus.getDefault().post("outLog");
        }
    }
}