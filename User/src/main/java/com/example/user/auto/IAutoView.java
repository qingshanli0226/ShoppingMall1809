package com.example.user.auto;

import com.example.common.bean.LogBean;
import com.example.framework.IBaseView;

public interface IAutoView extends IBaseView {
    void onAutoData(LogBean logBean);
}
