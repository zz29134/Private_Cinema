package com.mp.private_cinema.utils;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .常量类
 */

public class Constants {

    /** 请求远程数据时的固定字段 */
    interface reqhead {

    }

    public interface REQUEST_FLAG {
        int LOGO_ADVERTISEMENT = 0;
        int HOME_ADVERTISEMENT_TOP = 1;
        int HOME_HITFILMS = 2;
        int CINEMA_RECOMMEND = 3;
    }

    public interface CMD {
        String WELCOME = "welcome";
        String HOME_ADVERTISEMENT = "home_advertisement";
        String HOME_FILMLIST = "home_film";
        String CINEMA_RECOMMEND = "cinema_recommend";
        String CINEMA_LIST = "cinema_list";
    }

    public interface params {
        /**
         * 偏移量
         */
        String offset = "offset";
        /**
         * 每页获取数据数量
         */
        String limit = "limit";
        /**
         * 排序方式
         */
        String orderby = "orderby";
    }

}
