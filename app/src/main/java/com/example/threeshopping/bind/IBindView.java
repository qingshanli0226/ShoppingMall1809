package com.example.threeshopping.bind;

import com.example.framework.IBaseView;
import com.example.net.bean.SelectBean;

public interface IBindView extends IBaseView {
    void onPhone(SelectBean selectBean);
    void onAddr(SelectBean selectBean);
}
