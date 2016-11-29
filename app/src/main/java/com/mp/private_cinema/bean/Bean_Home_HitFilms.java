package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/29
 * 说明
 */

public class Bean_Home_HitFilms extends DataSupport {

    private String Film_Name;
    private String Film_ImagePath;
    private String Film_Rating;

    public Bean_Home_HitFilms() {
    }

    public Bean_Home_HitFilms(String film_Name, String film_ImagePath, String film_Rating) {
        Film_Name = film_Name;
        Film_ImagePath = film_ImagePath;
        Film_Rating = film_Rating;
    }

    public String getFilm_Name() {
        return Film_Name;
    }

    public void setFilm_Name(String film_Name) {
        Film_Name = film_Name;
    }

    public String getFilm_ImagePath() {
        return Film_ImagePath;
    }

    public void setFilm_ImagePath(String film_ImagePath) {
        Film_ImagePath = film_ImagePath;
    }

    public String getFilm_Rating() {
        return Film_Rating;
    }

    public void setFilm_Rating(String film_Rating) {
        Film_Rating = film_Rating;
    }
}
