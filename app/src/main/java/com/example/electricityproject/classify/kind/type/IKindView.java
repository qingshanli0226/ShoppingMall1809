package com.example.electricityproject.classify.kind.type;

import com.example.common.bean.KindAccessoryBean;
import com.example.common.bean.KindBagBean;
import com.example.common.bean.KindDigitBean;
import com.example.common.bean.KindDressBean;
import com.example.common.bean.KindGameBean;
import com.example.common.bean.KindHomeProductsBean;
import com.example.common.bean.KindJacketBean;
import com.example.common.bean.KindOvercoatBean;
import com.example.common.bean.KindPantsBean;
import com.example.common.bean.KindSkirtBean;
import com.example.common.bean.KindStationeryBean;
import com.example.framework.IBaseView;

public interface IKindView extends IBaseView {
    void onSkirtData(KindSkirtBean kindSkirtBean);
    void onJacketData(KindJacketBean kindJacketBean);
    void onPantData(KindPantsBean kindPantsBean);
    void onOvercoatData(KindOvercoatBean kindOvercoatBean);
    void onAccessoryData(KindAccessoryBean kindAccessoryBean);
    void onBagData(KindBagBean kindBagBean);
    void onDressBean(KindDressBean kindDressBean);
    void onHomeProductsBean(KindHomeProductsBean kindHomeProductsBean);
    void onStationeryBean(KindStationeryBean kindStationeryBean);
    void onDigitBean(KindDigitBean kindDigitBean);
    void onGameBean(KindGameBean kindGameBean);



}
