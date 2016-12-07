package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/29
 * 说明
 */

public class Bean_Home_HitFilms extends DataSupport {

    private String movie_id;
    private String movie_name;
    private String movie_type;
    private String release_year;
    private String director;
    private String actor;
    private String other_name;
    private String duration;
    private String area;
    private String introduction;
    private String score;
    private String recommend;
    private String picture_address;
    private String picture_type;

    public Bean_Home_HitFilms() {
    }

    public Bean_Home_HitFilms(String movie_id, String movie_name, String movie_type, String release_year, String director, String actor, String other_name, String duration, String area, String introduction, String score, String recommend, String picture_address, String picture_type) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_type = movie_type;
        this.release_year = release_year;
        this.director = director;
        this.actor = actor;
        this.other_name = other_name;
        this.duration = duration;
        this.area = area;
        this.introduction = introduction;
        this.score = score;
        this.recommend = recommend;
        this.picture_address = picture_address;
        this.picture_type = picture_type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getOther_name() {
        return other_name;
    }

    public void setOther_name(String other_name) {
        this.other_name = other_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getPicture_address() {
        return picture_address;
    }

    public void setPicture_address(String picture_address) {
        this.picture_address = picture_address;
    }

    public String getPicture_type() {
        return picture_type;
    }

    public void setPicture_type(String picture_type) {
        this.picture_type = picture_type;
    }
}
