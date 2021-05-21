package com.example.user.auto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.common.SPUtility;
import com.example.common.bean.LogBean;
import com.example.common.call.BusinessUserManager;

public class AutoService extends Service {
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!SPUtility.getString(this).equals("")){
            new AutoPresenter(new IAutoView() {
                @Override
                public void onAutoData(LogBean logBean) {
                    if (logBean.getCode().equals("200")){
                        Toast.makeText(AutoService.this, ""+logBean.getMessage(), Toast.LENGTH_SHORT).show();
                        BusinessUserManager.getInstance().setIsLog(logBean);
                        SPUtility.putString(AutoService.this,logBean.getResult().getToken());
                        // BusinessARouter.getInstance().getAppManager().OpenMainActivity(AutoService.this,null);
                    }else {
                        Toast.makeText(AutoService.this, ""+logBean.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Log.i("zx", "showError: "+error);
                }
            }).getAutoData(SPUtility.getString(this));
        }

        return super.onStartCommand(intent, flags, startId);
    }
}