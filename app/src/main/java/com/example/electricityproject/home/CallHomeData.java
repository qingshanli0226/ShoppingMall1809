package com.example.electricityproject.home;

import com.example.common.bean.HomeBean;
import com.example.framework.IBaseView;

public interface CallHomeData extends IBaseView {
    void onHomeBanner(HomeBean homeBean);
}
