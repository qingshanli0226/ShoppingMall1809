package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import com.fiannce.bawei.net.mode.FocusBean;
import com.fiannce.bawei.net.mode.VersionBean;

public class MVVMBean {
    private int type;//当type为1时，数据类型为versionBean，为2时，类型为focusBean
    private VersionBean versionBean;
    private FocusBean focusBean;
    private int status;//当status为0的时候代表着数据正确返回，为1的时候代表着当前正在网络请求，为2的时候代表着错误

    public MVVMBean(int type) {
        this.setType(type);

    }

    public VersionBean getVersionBean() {
        return versionBean;
    }

    public void setVersionBean(VersionBean versionBean) {
        this.versionBean = versionBean;
    }

    public FocusBean getFocusBean() {
        return focusBean;
    }

    public void setFocusBean(FocusBean focusBean) {
        this.focusBean = focusBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
