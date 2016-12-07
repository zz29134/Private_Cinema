package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/11/30 <br/>
 * 说明
 */

public class Bean_Home_HitCinemas extends DataSupport {

    private String store_basid;
    private String store_name;
    private String store_adress;
    private String store_telephone;
    private String store_score;
    private String picture_type;
    private String picture_address;

    public Bean_Home_HitCinemas() {
    }

    public Bean_Home_HitCinemas(String store_basid, String store_name, String store_adress, String store_telephone, String store_score, String picture_type, String picture_address) {
        this.store_basid = store_basid;
        this.store_name = store_name;
        this.store_adress = store_adress;
        this.store_telephone = store_telephone;
        this.store_score = store_score;
        this.picture_type = picture_type;
        this.picture_address = picture_address;
    }

    public String getStore_basid() {
        return store_basid;
    }

    public void setStore_basid(String store_basid) {
        this.store_basid = store_basid;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_adress() {
        return store_adress;
    }

    public void setStore_adress(String store_adress) {
        this.store_adress = store_adress;
    }

    public String getStore_telephone() {
        return store_telephone;
    }

    public void setStore_telephone(String store_telephone) {
        this.store_telephone = store_telephone;
    }

    public String getStore_score() {
        return store_score;
    }

    public void setStore_score(String store_score) {
        this.store_score = store_score;
    }

    public String getPicture_type() {
        return picture_type;
    }

    public void setPicture_type(String picture_type) {
        this.picture_type = picture_type;
    }

    public String getPicture_address() {
        return picture_address;
    }

    public void setPicture_address(String picture_address) {
        this.picture_address = picture_address;
    }
}
