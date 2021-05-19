package com.example.net;




import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.store.CloseStoreBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.store.ComicStoreBean;
import com.example.net.bean.store.CosplayStoreBean;
import com.example.net.bean.store.FoodStoreBean;
import com.example.net.bean.store.GameStoreBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;
import com.example.net.bean.TabBean;
import com.example.net.bean.store.GufengStoreBean;
import com.example.net.bean.store.ShoushiStoreBean;
import com.example.net.bean.store.StickStoreBean;
import com.example.net.bean.store.WenjuStoreBean;
import com.example.net.constants.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;


//认为是IMode
public interface FiannceApiService {

    //主页面HoemFrgment数据
    @GET(Constants.HOME_URL)
    Observable<HomeBean> getHoemData();

    //分类Fragment里面的标签Fragment页面数据
    @GET(Constants.TAG_URL)
    Observable<TabBean> getTabData();

    //小裙子
    @GET(Constants.SKIRT_URL)
    Observable<SkirtBean> getSkirtData();

    //上衣
    @GET(Constants.JACKET_URL)
    Observable<JacketBean> getJacketData();

    //下装(裤子)
    @GET(Constants.PANTS_URL)
    Observable<PantsBean> getPantsData();

    //外套
    @GET(Constants.OVERCOAT_URL)
    Observable<OvercoatBean> getOvercoatData();

    //配件
    @GET(Constants.ACCESSORY_URL)
    Observable<AccrssoryBean> getAccrssoryData();

    //包包
    @GET(Constants.BAG_URL)
    Observable<BagBean> getBagData();

    //装扮
    @GET(Constants.DRESS_UP_URL)
    Observable<DressBean> getDressData();

    //居家宅品
    @GET(Constants.HOME_PRODUCTS_URL)
    Observable<ProductsBean> getProductData();

    //办公文具
    @GET(Constants.STATIONERY_URL)
    Observable<StationeryBean> getStationeryData();

    //数码周边
    @GET(Constants.DIGIT_URL)
    Observable<DigitBean> getDigitData();

    //游戏专区
    @GET(Constants.GAME_URL)
    Observable<GameBean> getGameData();




    //服饰
    @GET(Constants.CLOSE_STORE)
    Observable<CloseStoreBean> getCloseData();

    //游戏
    @GET(Constants.GAME_STORE)
    Observable<GameStoreBean> getGameStoreData();

    //动漫
    @GET(Constants.COMIC_STORE)
    Observable<ComicStoreBean> getComicStoreData();

    //cosplay
    @GET(Constants.COSPLAY_STORE)
    Observable<CosplayStoreBean> getCosplayStoreData();

    //古风
    @GET(Constants.GUFENG_STORE)
    Observable<GufengStoreBean> getGufengStoreData();

    //古风
    @GET(Constants.STICK_STORE)
    Observable<StickStoreBean> getStickStoreData();

    //文具
    @GET(Constants.WENJU_STORE)
    Observable<WenjuStoreBean> getWenjuStoreData();

    //零食
    @GET(Constants.FOOD_STORE)
    Observable<FoodStoreBean> getFoodStoreData();

    //首饰厂
    @GET(Constants.SHOUSHI_STORE)
    Observable<ShoushiStoreBean> getShoushiStoreData();







}
