package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by eE on 2016/11/30.
 */

public class Bean_Home_Advertisement extends DataSupport {

    private String ADVERTISEMENT_IMAGEPATH;
    private String ADVERTISEMENT_LINKPATH;

    public Bean_Home_Advertisement() {
    }

    public String getADVERTISEMENT_LINKPATH() {
        return ADVERTISEMENT_LINKPATH;
    }

    public void setADVERTISEMENT_LINKPATH(String ADVERTISEMENT_LINKPATH) {
        this.ADVERTISEMENT_LINKPATH = ADVERTISEMENT_LINKPATH;
    }

    public String getADVERTISEMENT_IMAGEPATH() {
        return ADVERTISEMENT_IMAGEPATH;
    }

    public void setADVERTISEMENT_IMAGEPATH(String ADVERTISEMENT_IMAGEPATH) {
        this.ADVERTISEMENT_IMAGEPATH = ADVERTISEMENT_IMAGEPATH;
    }
}
