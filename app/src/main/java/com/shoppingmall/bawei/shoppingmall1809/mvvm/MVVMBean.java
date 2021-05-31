package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import com.fiannce.bawei.net.mode.FocusBean;
import com.fiannce.bawei.net.mode.VersionBean;

public class MVVMBean {
    private int type;//当type为1时，数据类型为versionBean，为2时，类型为focusBean
    private VersionBean versionBean;
    private FocusBean focusBean;

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

    public void getData(IBeanCallBack iBeanCallBack) {
        if (type == 1) {
            iBeanCallBack.onVersion(versionBean);
        } else if (type == 2) {
            iBeanCallBack.onFocus(focusBean);
        }

    }

    public interface IBeanCallBack {
        void onVersion(VersionBean versionBean);
        void onFocus(FocusBean focusBean);
    }
}
