package com.example.framework.manager;

import com.example.net.bean.AwaitPaymentBean;
import com.example.net.bean.AwaitPaymentBean.ResultBean;

import java.util.ArrayList;
import java.util.List;

public class CacheAwaitPaymentManager {

    private static CacheAwaitPaymentManager cacheAwaitPaymentManager;

    private CacheAwaitPaymentManager() {

    }

    public synchronized static CacheAwaitPaymentManager getInstance() {
        if (cacheAwaitPaymentManager == null) {
            cacheAwaitPaymentManager = new CacheAwaitPaymentManager();
        }
        return cacheAwaitPaymentManager;
    }

    private List<ResultBean> awaitPayment = new ArrayList<>();

    public List<ResultBean> getAwaitPayment() {
        return awaitPayment;
    }

    public void setAwaitPayment(List<ResultBean> awaitPayment) {
        this.awaitPayment.clear();
        this.awaitPayment.addAll(awaitPayment)  ;

    }
}
