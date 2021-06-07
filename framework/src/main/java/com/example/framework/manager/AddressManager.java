package com.example.framework.manager;

import android.content.Context;
import android.os.Handler;

import com.example.commened.FiannceContants;
import com.example.framework.greendao.CacheAddress;
import com.example.framework.greendao.DaoMaster;
import com.example.framework.greendao.DaoSession;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddressManager {
    private Context context;
    private DaoSession daoSession;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private Handler handler = new Handler();

    private List<CacheAddress> cacheAddresses;

    //初始化
    public void init(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, FiannceContants.SQ_ADDRESS);
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
    }

    //获取数据
    public synchronized  void  getAddress(IAddressTableListener listener){
        executorService.execute(() -> {
            List<CacheAddress> addressTables = daoSession.loadAll(CacheAddress.class);
            cacheAddresses = addressTables;
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
        void IAddressTableData(List<CacheAddress> list);
    }
    //添加
    public synchronized void addAddress(CacheAddress cacheAddress){
        executorService.execute(() -> {
            daoSession.insert(cacheAddress);
            cacheAddresses.add(cacheAddress);
            addressInterface();
        });
    }
    //删除
    public synchronized void deleteAddress(CacheAddress cacheAddress,int position){
        executorService.execute(() -> {
            daoSession.delete(cacheAddress);
            cacheAddresses.remove(position);
            addressInterface();
        });
    }

    //修改
    public synchronized void updateAddress(CacheAddress cacheAddress,int position){
        executorService.execute(() -> {
            daoSession.update(cacheAddress);
            cacheAddresses.get(position).setName(cacheAddress.getName());
            cacheAddresses.get(position).setPhone(cacheAddress.getPhone());
            cacheAddresses.get(position).setAddress(cacheAddress.getAddress());
            addressInterface();
        });
    }

    //接口回调
    public void addressInterface(){
        handler.post(() -> {
            if (iAddressListener!=null){
                iAddressListener.IAddressListData(cacheAddresses);
            }
        });
    }



    //接口回调
    public interface IAddressListener{
        void IAddressListData(List<CacheAddress> list);
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
