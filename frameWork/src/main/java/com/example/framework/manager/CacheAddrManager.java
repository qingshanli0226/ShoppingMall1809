package com.example.framework.manager;

import android.os.Handler;

import com.example.common.LogUtil;
import com.fiannce.sql.AddrBeanDao;
import com.fiannce.sql.bean.AddrBean;
import com.fiannce.sql.manager.SqlManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheAddrManager {

    private static CacheAddrManager cacheAddrManager;
    private Handler handler = new Handler();
    private CacheAddrManager() {
    }

    public static CacheAddrManager getInstance() {
        if (cacheAddrManager == null) {
            synchronized (CacheAddrManager.class) {
                if (cacheAddrManager == null) {
                    cacheAddrManager = new CacheAddrManager();
                }
            }
        }
        return cacheAddrManager;
    }

    private List<AddrBean> addrBeans = new ArrayList<>();
    private List<IAddrListener> addrListeners = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void init() {
        searchAddr();
    }

    public synchronized void registerListener(IAddrListener addrListener) {
        addrListeners.add(addrListener);
    }

    public synchronized void unRegisterListener(IAddrListener addrListener) {
        addrListeners.remove(addrListener);

    }

    public synchronized List<AddrBean> getAddrBeans() {
        return addrBeans;
    }

    public synchronized void setAddrBeans(List<AddrBean> addrBeans) {
        this.addrBeans.addAll(addrBeans);
    }

    public synchronized void searchAddr() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //查询数据
                AddrBeanDao addrBeanDao = SqlManager.getInstance().getDaoSession().getAddrBeanDao();
                List<AddrBean> beans = addrBeanDao.loadAll();
                //修改缓存
                setAddrBeans(beans);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        CacheAddrManager.getInstance().notifyAll(addrBeans);
                    }
                });
                //通知页面
            }
        });
    }

    public synchronized void addAddr(AddrBean addrBean) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int mPosition = -1;
                for (int i = 0; i < addrBeans.size(); i++) {
                    if (addrBeans.get(i).getIsDefault()) {
                        mPosition = i;
                    }
                }
                //修改数据库
                AddrBeanDao addrBeanDao = SqlManager.getInstance().getDaoSession().getAddrBeanDao();
                //把默认去掉
                if (mPosition != -1) {
                    AddrBean addrBean = addrBeans.get(mPosition);
                    addrBean.setIsDefault(false);
                    addrBeanDao.update(addrBean);
                }


                addrBeanDao.insert(addrBean);
                //添加缓存
                addrBeans.add(addrBean);
                //通知
                CacheAddrManager.getInstance().notifyOne(addrBeans.size() - 1,false);
            }
        });
    }

    public void notifyAll(List<AddrBean> addrBeans) {
        for (IAddrListener addrListener : addrListeners) {
            addrListener.onrefreshAll(addrBeans);
        }
    }

    public void notifyOne(int position,boolean isDelect) {
        for (IAddrListener addrListener : addrListeners) {
            addrListener.onrefreshOne(position,isDelect);
        }
    }

    //删除
    public void removeOne(AddrBean addrBean,int position) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                AddrBeanDao addrBeanDao = SqlManager.getInstance().getDaoSession().getAddrBeanDao();
                addrBeanDao.delete(addrBean);
                //缓存
                addrBeans.remove(position);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notifyOne(position,true);
                    }
                });
            }
        });


    }

    public void update(int position, String addr, String phone, boolean isDefualt) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (addrBeans.get(position).getIsDefault()) {
                    //已经是默认
                    //修改缓存
                    AddrBean cacheBean = addrBeans.get(position);
                    cacheBean.setIsDefault(isDefualt);
                    cacheBean.setAddr(addr);
                    cacheBean.setPhone(phone);
                    AddrBeanDao addrBeanDao = SqlManager.getInstance().getDaoSession().getAddrBeanDao();
                    addrBeanDao.update(cacheBean);
                } else {
                    //修改默认
                    int mPosition = -1;
                    for (int i = 0; i < addrBeans.size(); i++) {
                        if (addrBeans.get(i).getIsDefault() && position != i) {
                            mPosition = i;
                        }
                    }
                    //修改数据库
                    AddrBeanDao addrBeanDao = SqlManager.getInstance().getDaoSession().getAddrBeanDao();
                    //把默认去掉
                    if (mPosition != -1) {
                        AddrBean addrBean = addrBeans.get(mPosition);
                        addrBean.setIsDefault(false);
                        addrBeanDao.update(addrBean);
                    }

                    //添加新默认
                    AddrBean newAddrBean = addrBeans.get(position);
                    newAddrBean.setIsDefault(true);
                    newAddrBean.setAddr(addr);
                    newAddrBean.setPhone(phone);
                    addrBeanDao.update(newAddrBean);
                }
                //通知页面
                CacheAddrManager.this.notifyAll(addrBeans);
            }
        });
    }


    //接口监听
    public interface IAddrListener {
        void onrefreshAll(List<AddrBean> addrBeans);

        void onrefreshOne(int position,boolean isDelect);
    }
}
