package com.example.common.call;

<<<<<<< HEAD:Common/src/main/java/com/example/common/call/BusinessUserManager.java


import com.example.common.bean.LogBean;
=======
>>>>>>> c51b508b90e590efddf687cdfc0692f44bc44dd3:Common/src/main/java/com/example/common/call/FiannceUserManager.java

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
    }

    public interface IUserLoginChanged{

        void onLoginChange(LogBean isLog);

    }
}
