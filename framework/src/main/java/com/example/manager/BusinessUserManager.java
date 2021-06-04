package com.example.manager;



import com.example.common.bean.LogBean;

import java.util.ArrayList;
import java.util.List;

public class BusinessUserManager {

    private List<IUserLoginChanged> list=new ArrayList<>();
    private static BusinessUserManager manager;

    public synchronized static BusinessUserManager getInstance() {
        if (manager==null){
            manager=new BusinessUserManager();
        }
        return manager;
    }
    public void Register(IUserLoginChanged iUserLoginChanged){
        list.add(iUserLoginChanged);
    }

    public void UnRegister(IUserLoginChanged iUserLoginChanged){
        list.remove(iUserLoginChanged);
    }
    
    private LogBean isLog;
    private boolean isBindAddress;
    private boolean isBindTel;
    private LogBean.ResultBean logList;

    public LogBean.ResultBean getLogList() {
        return logList;
    }

    public void setLogList(LogBean.ResultBean logList) {
        this.logList = logList;
    }

    public boolean isBindAddress() {
        return isBindAddress;
    }

    public void setBindAddress(String address) {
        if (isLog!=null){
            isLog.getResult().setAddress(address);
        }
    }

    public boolean isBindTel() {
        return isBindTel;
    }

    public void setBindAddress(boolean bindAddress) {
        isBindAddress = bindAddress;
    }

    public void setBindTel(boolean bindTel) {
        isBindTel = bindTel;
    }

    public void setBindTel(String bindTel) {
        if (isLog!=null){
            isLog.getResult().setPhone(bindTel);
        }
    }

    public LogBean getIsLog() {
        return isLog;
    }

    public void setIsLog(LogBean isLog) {
        this.isLog = isLog;
        if (isLog!=null){
            for (IUserLoginChanged iUserLoginChanged : list) {
                iUserLoginChanged.onLoginChange(isLog);
            }
        }
    }

    public interface IUserLoginChanged{
        void onLoginChange(LogBean isLog);
    }

}
