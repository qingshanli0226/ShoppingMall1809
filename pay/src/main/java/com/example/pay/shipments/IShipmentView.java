package com.example.pay.shipments;

import com.example.framework.IBaseView;
import com.example.net.bean.ShipmentBean;

public interface IShipmentView extends IBaseView {
    public void onShipment(ShipmentBean shipmentBean);

}
