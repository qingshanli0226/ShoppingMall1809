package com.shoppingmall.pay.dropshipping;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.FindForSendBean;

public interface IDropShippingView extends IBaseView {
    void getFindForSend(FindForSendBean findForSendBean);
}
