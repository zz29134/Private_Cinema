package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/11/30 <br/>
 * 说明
 */

public class Bean_Home_HitCinemas extends DataSupport {

    private String CINEMA_ID;
    private String CINEMA_NAME;
    private String CINEMA_ADDRESS;
    private String CINEMA_TELEPHONE;
    private String CINEMA_SCORE;
    private String PICTURE_TYPE;
    private String PICTURE_ADDRESS;

    public Bean_Home_HitCinemas() {
    }

    public Bean_Home_HitCinemas(String CINEMA_ID, String CINEMA_NAME, String CINEMA_ADDRESS, String CINEMA_TELEPHONE, String CINEMA_SCORE, String PICTURE_TYPE, String PICTURE_ADDRESS) {
        this.CINEMA_ID = CINEMA_ID;
        this.CINEMA_NAME = CINEMA_NAME;
        this.CINEMA_ADDRESS = CINEMA_ADDRESS;
        this.CINEMA_TELEPHONE = CINEMA_TELEPHONE;
        this.CINEMA_SCORE = CINEMA_SCORE;
        this.PICTURE_TYPE = PICTURE_TYPE;
        this.PICTURE_ADDRESS = PICTURE_ADDRESS;
    }

    public String getCINEMA_ID() {
        return CINEMA_ID;
    }

    public void setCINEMA_ID(String CINEMA_ID) {
        this.CINEMA_ID = CINEMA_ID;
    }

    public String getCINEMA_NAME() {
        return CINEMA_NAME;
    }

    public void setCINEMA_NAME(String CINEMA_NAME) {
        this.CINEMA_NAME = CINEMA_NAME;
    }

    public String getCINEMA_ADDRESS() {
        return CINEMA_ADDRESS;
    }

    public void setCINEMA_ADDRESS(String CINEMA_ADDRESS) {
        this.CINEMA_ADDRESS = CINEMA_ADDRESS;
    }

    public String getCINEMA_TELEPHONE() {
        return CINEMA_TELEPHONE;
    }

    public void setCINEMA_TELEPHONE(String CINEMA_TELEPHONE) {
        this.CINEMA_TELEPHONE = CINEMA_TELEPHONE;
    }

    public String getCINEMA_SCORE() {
        return CINEMA_SCORE;
    }

    public void setCINEMA_SCORE(String CINEMA_SCORE) {
        this.CINEMA_SCORE = CINEMA_SCORE;
    }

    public String getPICTURE_TYPE() {
        return PICTURE_TYPE;
    }

    public void setPICTURE_TYPE(String PICTURE_TYPE) {
        this.PICTURE_TYPE = PICTURE_TYPE;
    }

    public String getPICTURE_ADDRESS() {
        return PICTURE_ADDRESS;
    }

    public void setPICTURE_ADDRESS(String PICTURE_ADDRESS) {
        this.PICTURE_ADDRESS = PICTURE_ADDRESS;
    }
}
