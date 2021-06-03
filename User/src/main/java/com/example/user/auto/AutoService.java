package com.example.user.auto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.common.TokenSPUtility;
import com.example.common.bean.LogBean;
import com.example.manager.BusinessUserManager;

public class AutoService extends Service implements BusinessUserManager.IUserLoginChanged{

    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!TokenSPUtility.getString(this).equals("")) {
                new AutoPresenter(new IAutoView() {
                    @Override
                    public void onAutoData(LogBean logBean) {
                        if (logBean.getCode().equals("200")) {
                            BusinessUserManager.getInstance().setIsLog(logBean);
                            TokenSPUtility.putString(AutoService.this, logBean.getResult().getToken());
                            BusinessUserManager.getInstance().setIsLog(logBean);
                            BusinessUserManager.getInstance().setLogList(logBean.getResult());
                        } else {
                            Toast.makeText(AutoService.this, "" + logBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
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
                }).getAutoData(TokenSPUtility.getString(this));
            }
            return super.onStartCommand(intent, flags, startId);
        }

    @Override
    public void onLoginChange(LogBean isLog) {

    }
}