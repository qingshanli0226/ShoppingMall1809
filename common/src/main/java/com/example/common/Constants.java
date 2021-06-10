package com.example.common;

public class Constants {
    public static final String BASE_URL="http://49.233.0.68:8080/";
    public static final String BASE_URL_JSON ="/atguigu/json/";
    public static final String BASE_URl_IMAGE =BASE_URL+"/atguigu/img";
    //小裙子
    public static final String SKIRT_URL = BASE_URL_JSON + "SKIRT_URL.json";
    //上衣
    public static final String JACKET_URL = BASE_URL_JSON + "JACKET_URL.json";
    //下装(裤子)
    public static final String PANTS_URL = BASE_URL_JSON + "PANTS_URL.json";
    //外套
    public static final String OVERCOAT_URL = BASE_URL_JSON + "OVERCOAT_URL.json";
    //配件
    public static final String ACCESSORY_URL = BASE_URL_JSON + "ACCESSORY_URL.json";
    //包包
    public static final String BAG_URL = BASE_URL_JSON + "BAG_URL.json";
    //装扮
    public static final String DRESS_UP_URL = BASE_URL_JSON + "DRESS_UP_URL.json";
    //居家宅品
    public static final String HOME_PRODUCTS_URL = BASE_URL_JSON + "HOME_PRODUCTS_URL.json";
    //办公文具
    public static final String STATIONERY_URL = BASE_URL_JSON + "STATIONERY_URL.json";
    //数码周边
    public static final String DIGIT_URL = BASE_URL_JSON +  "DIGIT_URL.json";
    //游戏专区
    public static final String GAME_URL = BASE_URL_JSON + "GAME_URL.json";

    public static final String LOGIN = "login";//登录
    public static final String USERREGISTER = "register";//注册
    public static final String AUTOLOGIN = "autoLogin";//自动登录
    public static final String LOGOUT = "logout";//退出登录
    //代付款
    public static final String AWAITPAYMENT = "findForPay";
    //代发货
    public static final String SHIPMENT = "findForSend";
    //主页Fragment路径
    public static final String HOME_URL = BASE_URL_JSON + "HOME_URL.json";
    //分类Fragment里面的标签Fragment页面数据
    public static final String TAG_URL = BASE_URL_JSON + "TAG_URL.json";


    public static final String NEW_POST_URL = BASE_URL_JSON + "NEW_POST_URL.json";
    public static final String HOT_POST_URL = BASE_URL_JSON + "HOT_POST_URL.json";


    //页面的具体数据的id
    public static final String GOODSINFO_URL = BASE_URL_JSON + "GOODSINFO_URL.json";

    //服饰
    public static final String CLOSE_STORE = BASE_URL_JSON + "CLOSE_STORE.json";
    //游戏
    public static final String GAME_STORE = BASE_URL_JSON + "GAME_STORE.json";
    //动漫
    public static final String COMIC_STORE = BASE_URL_JSON + "COMIC_STORE.json";
    //cosplay
    public static final String COSPLAY_STORE = BASE_URL_JSON + "COSPLAY_STORE.json";
    //古风
    public static final String GUFENG_STORE = BASE_URL_JSON + "GUFENG_STORE.json";
    //漫展
    public static final String STICK_STORE = BASE_URL_JSON + "STICK_STORE.json";
    //文具
    public static final String WENJU_STORE = BASE_URL_JSON + "WENJU_STORE.json";
    //零食
    public static final String FOOD_STORE = BASE_URL_JSON + "FOOD_STORE.json";
    //首饰厂
    public static final String SHOUSHI_STORE = BASE_URL_JSON + "SHOUSHI_STORE.json";



    public static Boolean isBackHome = false;



    //客服数据
    public static final String CALL_CENTER = "http://www6.53kf.com/webCompany.php?arg=10007377&style=1&kflist=off&kf=info@atguigu.com,video@atguigu.com,public@atguigu.com,3069368606@qq.com,215648937@qq.com,sudan@atguigu.com,sszhang@atguigu.com&zdkf_type=1&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fwww.atguigu.com%2Fcontant.shtml&keyword=&tfrom=1&tpl=crystal_blue&timeStamp=1479001706368&ucust_id=";


    //欢迎页面
    public static final String PATH_WEL = "/app/WelActivity";
    //主页面
    public static final String PATH_MAIN = "/app/MainActivity";
    //详情
    public static final String PATH_PARTICULARS = "/app/ParticularsActivity";
    //详情
    public static final String PATH_SHOPACTIVITY = "/app/ShopActivity";
    //地址管理页面
    public static final String PATH_ADDRMANAGER = "/app/AddrManagerActivity";
    //绑定页面
    public static final String PATH_BIND = "/app/BindInfoActivity";
    //设置
    public static final String PATH_SETTING = "/app/SettingActivity";
    //
    public static final String PATH_INFORMATION = "/app/PersonalinformationActivity";
    //信息
    public static final String PATH_MESSAGE = "/pay/MessageActivity";
    //待付款
    public static final String PATH_AWAITPAYMENT = "/pay/Awaitpayment";
    //自负方式
    public static final String PATH_PAYMENT = "/pay/PaymentActivity";
    //待发货
    public static final String PATH_SHIPMENTS= "/pay/ShipmentsActivity";
    //订单详细页面
    public static final String PATH_ORDERINFOACTIVITY= "/pay/OrderInfoActivity";
    //用户
    public static final String PATH_USER = "/user/UserActivity";

    //地图
    public static final String PATH_MAP = "/map/ChecklogActivity";

    public static final String PATH_LIVE = "/live/LiveActivity";
    public static final String PATH_VIDEO = "/Video/VideoActivity";
    public static final String PATH_PUSH_LIVE = "/Video/PushLiveActivity";

    public static final String SP_TOKEN = "token";
    public static final String SP_READ_NUM = "num";
    public static final String SP_PATH = "path";

    //判断home页面的布局
    public static final int HOME_BANNER = 0;
    public static final int HOME_CHANNEL = 1;
    public static final int HOME_ACT = 2;
    public static final int HOME_SEKILL = 3;
    public static final int HOME_RECOMMEND = 4;
    public static final int HOME_HOT = 5;


    //向服务端购物车添加一个产品的接口
    public static final String ADDPRODUCT = "addOneProduct";


    //获取服务端购物车产品信息的接口
    public static final String SHORTACRT = "getShortcartProducts";




    //检查服务端多个产品是否库存充足
    public static final String CHECKINVENTORY = "checkInventory";
//    检查服务端一个产品库存情况的接口
    public static final String CHECKONEPRODUCTINVENTORY= "checkOneProductInventory";


    //向服务端下订单接口
    public static final String GETORDER = "getOrderInfo";

    //请求服务端，是否支付成功
    public static final String CONFIRMSERVER = "confirmServerPayResult";

    //查找待支付的订单
    public static final String FINDPAY = "findForPay";

    //查找待发货的订单
    public static final String FINDSEND = "findForSend";

    //从服务端购物车删除一个产品的接口
    public static final String REMOVEPRODUCT = "removeOneProduct";

    //从更新服务端购物车产品的选择
    public static final String UPDATEPRODUCTSELECTED = "updateProductSelected";

    //更新服务端购物车产品的数量的接口
    public static final String UPDATEPRODUCTNUM = "updateProductNum";



    //全选服务端购物车产品或者全不选
    public static final String SELECTALL = "selectAllProduct";

    //从从服务端购物车删除多个产品的接口
    public static final String REMOVEMANY = "removeManyProduct";


    public static final String SQLDB = "shopcar.db";

    //更新电话
    public static final String UPDATEPHONE = "updatePhone";
    //更新地址
    public static final String UPDATEADDRESS = "updateAddress";

    //支付宝判断
    public static final int PAY_SDK_FLAG = 0;
    //默认地址
    public static final String DEFAULT_ADDR = "defaultAddr";
    public static final String ADD_ADDR = "addAddr";
    public static final String PHONE_ADDR = "phoneAddr";
    public static final String FLAG_ADDR = "flag";


}
