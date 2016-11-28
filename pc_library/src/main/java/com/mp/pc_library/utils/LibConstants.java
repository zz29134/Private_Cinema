package com.mp.pc_library.utils;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/28
 * 说明
 */

public class LibConstants {

    /**
     * SP文件名
     */
    public static final String SHARED_PREFERENCE_NAME = "SharedPreferences_PrivateCinema";

    /**
     * 首次进入APP标志
     */
    public static final String FLAG_NAVIGATOR = "true";

    /**
     * 加密KEY
     */
    public static final String SECRET_KEY = "com.mobile.privatecinema";

    /**
     * 加密向量
     */
    public static final String SECRET_IV = "01234567";

    /**
     * URL头部
     */
    public static final String URLHead = "http://61.52.199.191:8888/APP/";

    interface netstate {
        String readfail = "网络读取失败!";
    }

    interface jsName {
        String code = "Code";
        String content = "Content";
        String state = "State";
        String code_success = "001";
        String code_error = "002";
        String code_none = "003";
        String code_timeout = "004";
        /** 总页数 */
        String pagetotal = "pagetotal";
        /** 总记录数 */
        String totlePageSize = "totlePageSize";

    }

    interface TrueOrFalse {
        String True = "true";
        String False = "false";
    }
}