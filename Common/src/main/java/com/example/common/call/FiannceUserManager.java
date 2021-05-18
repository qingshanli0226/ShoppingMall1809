package com.example.common.call;

import com.example.network.model.LogBean;

import java.util.ArrayList;
import java.util.List;

public class FiannceUserManager {

    private List<IUserLoginChanged> list=new ArrayList<>();
    private static FiannceUserManager manager;

    public synchronized static FiannceUserManager getInstance() {
        if (manager==null){
            manager=new FiannceUserManager();
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
    }

    public interface IUserLoginChanged{

        void onLoginChange(LogBean isLog);

    }
}
