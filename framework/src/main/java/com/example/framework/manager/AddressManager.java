package com.example.framework.manager;

import android.content.Context;
import android.os.Handler;

import com.example.commom.ShopConstants;
import com.example.framework.db.AddressTable;
import com.example.framework.db.DaoMaster;
import com.example.framework.db.DaoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddressManager {

    private Context context;
    private DaoSession addressDao;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private Handler handler = new Handler();

    private List<AddressTable> addressList;

    //初始化
    public void init(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, ShopConstants.SQ_ADDRESS);
        addressDao = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
    }

    //获取数据
    public synchronized  void  getAddress(IAddressTableListener listener){
        cachedThreadPool.execute(() -> {
            List<AddressTable> addressTables = addressDao.loadAll(AddressTable.class);
            addressList = addressTables;
            handler.post(() -> {
                if (listener != null) {
                    listener.IAddressTableData(addressTables);
                }
            });
            addressInterface();
        });
    }
    //获取数据接口回调
    public interface IAddressTableListener{
        void IAddressTableData(List<AddressTable> list);
    }
    //添加
    public synchronized void addAddress(AddressTable addressTable){
        cachedThreadPool.execute(() -> {
            addressDao.insert(addressTable);
            addressList.add(addressTable);
            addressInterface();
        });
    }
    //删除
    public synchronized void deleteAddress(AddressTable addressTable,int position){
        cachedThreadPool.execute(() -> {
            addressDao.delete(addressTable);
            addressList.remove(position);
            addressInterface();
        });
    }

    //修改
    public synchronized void updateAddress(AddressTable addressTable,int position){
        cachedThreadPool.execute(() -> {
            addressDao.update(addressTable);
            addressList.get(position).setName(addressTable.getName());
            addressList.get(position).setPhone(addressTable.getPhone());
            addressList.get(position).setAddress(addressTable.getAddress());
            addressInterface();
        });
    }

    //接口回调
    public void addressInterface(){
        handler.post(() -> {
            if (iAddressListener!=null){
                iAddressListener.IAddressListData(addressList);
            }
        });
    }



    //接口回调
    public interface IAddressListener{
        void IAddressListData(List<AddressTable> list);
    }
    private IAddressListener iAddressListener;
    public void unregister() {
       iAddressListener=null;
    }
    public void register(IAddressListener iAddressListener) {
        this.iAddressListener = iAddressListener;
    }


    private static AddressManager addressManager;
    private AddressManager() {
    }
    public static AddressManager getInstance() {
        if (addressManager == null) {
            addressManager = new AddressManager();
        }
        return addressManager;
    }
}
