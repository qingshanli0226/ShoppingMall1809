package com.shoppingmall.framework;

public class Constants {
    public static final String BASE_URL="http://49.233.0.68:8080";
    public static final String BASE_URL_JSON ="/atguigu/json/";
    public static final String BASE_URl_IMAGE =BASE_URL+"/atguigu/img";

    public static final String IMG_HTTPS="http://49.233.0.68:8080/atguigu/img";
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

    public static final String TO_DETAIL_ACTIVITY = "/detail/DetailActivity";
    public static final String TO_MAIN_ACTIVITY = "/app/MainActivity";
    public static final String TO_USER_ACTIVITY ="/user/RegisterActivity";

    public static String TOKEN_KEY="token";
    public static final String SP_NAME = "autoToken";
    public static final String SP_TOKEN = "token";
    public static final String SP_NAME_LOGIN = "login";

    //购物车说明：检查服务端一个产品库存情况的接口请求头需要添加token
    public static final String CHECKOUTPRODUCT = "checkOneProductInventory";
    //向服务端购物车添加一个产品的接口
    public static final String ADDPRODUCT = "addOneProduct";


    //获取服务端购物车产品信息的接口
    public static final String SHORTACRT = "getShortcartProducts";

    //更新服务端购物车产品的数量的接口
    public static final String UPDATEPRODUCT = "updateProductNum";



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

}
