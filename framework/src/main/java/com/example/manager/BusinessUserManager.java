package com.example.manager;



import com.example.common.bean.LogBean;

import java.util.ArrayList;
import java.util.List;
//用户信息管理
public class BusinessUserManager {
    //监听用户登录状态
    private List<IUserLoginChanged> list=new ArrayList<>();
    private static BusinessUserManager manager;
    //同步单例锁
    public synchronized static BusinessUserManager getInstance() {
        if (manager==null){
            manager=new BusinessUserManager();
        }
        return manager;
    }
    //在基类里面注册并且监听
    public void Register(IUserLoginChanged iUserLoginChanged){
        list.add(iUserLoginChanged);
    }
    //取消注册 不染的话会造成单例对页面存在引用 这样的话可能会造成内存泄漏
    public void UnRegister(IUserLoginChanged iUserLoginChanged){
        list.remove(iUserLoginChanged);
    }
    //登录实体类
    private LogBean isLog;
    private boolean isBindAddress;
    private boolean isBindTel;
    private LogBean.ResultBean logList;

    public LogBean.ResultBean getLogList() {
        return logList;
    }

    /**
     * 往里面赋值并且监听
     * @param logList
     */
    public void setLogList(LogBean.ResultBean logList) {
        this.logList = logList;
    }

    public boolean isBindAddress() {
        return isBindAddress;
    }

    /**
     * 修改用户的地址可以用来监听
     * @param address
     */
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

    /**
     * 监听监听用户各个页面，每当用户的登录状态发生改变的时候它会通知各个页面去给他通知 这里面涉及的设计模式有模板模式
     * 模板模式：
     *      模板模式的作用就是我们把一些应该写在ui的一些业务方法写到基类里面，这样写的好处就是可以能使UI页面的业务代码大大减少
     *     ，并且也方便后期维护
     * @param isLog
     */
    public void setIsLog(LogBean isLog) {
        this.isLog = isLog;
        if (isLog!=null){
            for (IUserLoginChanged iUserLoginChanged : list) {
                iUserLoginChanged.onLoginChange(isLog);
            }
        }
    }

    /**
     * 登录注册的接口
     */
    public interface IUserLoginChanged{
        void onLoginChange(LogBean isLog);
    }

}
