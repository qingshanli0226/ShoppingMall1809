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
