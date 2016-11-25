package com.mp.private_cinema.utils;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .常量类
 */

public class Constants {

    /**
     * SP文件名
     */
    public static final String SHARED_PREFERENCE_NAME = "SharedPreferences_PC";

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

    /** 请求远程数据时的固定字段 */
    interface reqhead {

    }

    public interface REQUEST_FLAG {
        int LOGO_ADVERTISEMENT = 0x00F;
        int HOME_ADVERTISEMENT_TOP = 0x01F;
    }

    public interface CMD {
        String WELCOME = "welcome";
        String HOME_ADVERTISEMENT = "home_advertisement";
        String HOME_FILMLIST = "home_film";
    }

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

    interface keywords {

        String InitiBoot = SHARED_PREFERENCE_NAME + "_mInitiBoot";

        // 进行程度信息
        String Initialed = SHARED_PREFERENCE_NAME + "_mInitialed";

        // 服务器socket信息
        String Url = SHARED_PREFERENCE_NAME + "_url";
        String Host = SHARED_PREFERENCE_NAME + "_host";
        String Port = SHARED_PREFERENCE_NAME + "_port";

        String Deviceid = SHARED_PREFERENCE_NAME + "_deviceid";
        String Platform = SHARED_PREFERENCE_NAME + "_platform";
        String Version = SHARED_PREFERENCE_NAME + "_version";

        String Autoupdate = SHARED_PREFERENCE_NAME + "_autoupdate";

        String Ring = SHARED_PREFERENCE_NAME + "_settingRing";
        String Message = SHARED_PREFERENCE_NAME + "_settingMessage";

        String LoginID = SHARED_PREFERENCE_NAME + "_loginid";

        /** 会员图像地址 **/
        String MemberIcon = SHARED_PREFERENCE_NAME + "_memberIcon";
        /** 登录状态 **/
        String AlreadyLogin = SHARED_PREFERENCE_NAME + "_alreadyLogin";
    }

    interface TrueOrFalse {
        String True = "true";
        String False = "false";
    }


}
