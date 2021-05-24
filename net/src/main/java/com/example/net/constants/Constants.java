package com.example.net.constants;

public class Constants {
    public static final String BASE_URL="http://49.233.0.68:8080";
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
    //User
    public static final String USER_LOGIN = "login";//登录
    public static final String USER_REGISTER = "register";//注册
    public static final String USER_AUTOLOGIN = "autoLogin";//自动登录
    public static final String USER_UPDATEPHONE = "updatePhone";//更新用户绑定的电话
    public static final String USER_UPDATEMONEY = "updateMoney";//更新现金的接口
    public static final String USER_UPLOAD = "upload";//上传头像文件的接口
    public static final String USER_DOWMLOADFILE = "downloadFile";//下载文件的接口
    public static final String USER_UPDATEPOINT = "updatePoint";//更新积分的接口
    public static final String USER_UPDATEEMAIL = "updateEmail";//更新邮箱的接口
    public static final String USER_UPDATEADDRESS= "updateAddress";//更新地址的接口
    public static final String USER_LOGOUT= "logout";//退出登录接口

    //business
    public static final String BUSINESS_GETRECOMMEND = "getRecommend";//获取推荐产品信息的接口
    public static final String BUSINESS_SEARCH = "search";//搜索数据的接口
    public static final String BUSINESS_CHECKONEPRODUCTINVENTORY = "checkOneProductInventory";//检查服务端一个产品库存情况的接口
    public static final String BUSINESS_ADDONEPRODUCT = "addOneProduct";//向服务端购物车添加一个产品的接口
    public static final String BUSINESS_GETSHORCARTPRODUCTS = "getShortcartProducts";//获取服务端购物车产品信息的接口
    public static final String BUSINESS_UPDATEPRODUCTNUM = "updateProductNum";//更新服务端购物车产品的数量的接口
    public static final String BUSINESS_CHECKINVENTORY = "checkInventory";//检查服务端多个产品是否库存充足
    public static final String BUSINESS_GETORDERINFO = "getOrderInfo";//向服务端下订单接口
    public static final String BUSINESS_CONFIRMSERVERPAYRESULT = "confirmServerPayResult";//请求服务端，是否支付成功
    public static final String BUSINESS_FINDFORPAY = "findForPay";//查找待支付的订单
    public static final String BUSINESS_FINDFORSEND = "findForSend";//查找待发货的订单
    public static final String BUSINESS_REMOVEONEPRODUCT = "removeOneProduct";//从服务端购物车删除一个产品的接口
    public static final String BUSINESS_UPDATEPRODUCTSELECTED = "updateProductSelected";//更新服务端购物车产品的选择
    public static final String BUSINESS_SELECTALLPRODUCT = "selectAllProduct";//全选服务端购物车产品或者全不选
    public static final String BUSINESS_REMOVEMANYPRODUCT = "removeManyProduct";//从服务端购物车删除多个产品的接口












    //客服数据
    public static final String CALL_CENTER = "http://www6.53kf.com/webCompany.php?arg=10007377&style=1&kflist=off&kf=info@atguigu.com,video@atguigu.com,public@atguigu.com,3069368606@qq.com,215648937@qq.com,sudan@atguigu.com,sszhang@atguigu.com&zdkf_type=1&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fwww.atguigu.com%2Fcontant.shtml&keyword=&tfrom=1&tpl=crystal_blue&timeStamp=1479001706368&ucust_id=";
}