package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/11/30 <br/>
 * 说明
 */

public class Bean_Home_HitCinemas extends DataSupport {

    private String Cinema_Name;
    private String Cinema_Address;
    private String Cinema_Post;
    private String Cinema_Feature;
    private String Cinema_Rating;

    public Bean_Home_HitCinemas() {
    }

    public Bean_Home_HitCinemas(String cinema_Name, String cinema_Address, String cinema_Post, String cinema_Feature, String cinema_Rating) {
        Cinema_Name = cinema_Name;
        Cinema_Address = cinema_Address;
        Cinema_Post = cinema_Post;
        Cinema_Feature = cinema_Feature;
        Cinema_Rating = cinema_Rating;
    }

    public String getCinema_Name() {
        return Cinema_Name;
    }

    public void setCinema_Name(String cinema_Name) {
        Cinema_Name = cinema_Name;
    }

    public String getCinema_Address() {
        return Cinema_Address;
    }

    public void setCinema_Address(String cinema_Address) {
        Cinema_Address = cinema_Address;
    }

    public String getCinema_Post() {
        return Cinema_Post;
    }

    public void setCinema_Post(String cinema_Post) {
        Cinema_Post = cinema_Post;
    }

    public String getCinema_Feature() {
        return Cinema_Feature;
    }

    public void setCinema_Feature(String cinema_Feature) {
        Cinema_Feature = cinema_Feature;
    }

    public String getCinema_Rating() {
        return Cinema_Rating;
    }

    public void setCinema_Rating(String cinema_Rating) {
        Cinema_Rating = cinema_Rating;
    }
}
