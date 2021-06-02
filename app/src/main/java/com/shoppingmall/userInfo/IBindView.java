package com.shoppingmall.userInfo;


import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.SelectBean;

public interface IBindView extends IBaseView {
    void onPhone(SelectBean selectBean);
    void onAddress(SelectBean selectBean);
}
