package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by eE on 2016/11/30.
 */

public class Bean_Home_Advertisement extends DataSupport {

    private String ADVERTISEMENT_IMAGEPATH;
    private String ADVERTISEMENT_LINKPATH;
    private String ADVERTISEMENT_DEADLINE;

    public Bean_Home_Advertisement() {
    }

    public Bean_Home_Advertisement(String ADVERTISEMENT_IMAGEPATH, String ADVERTISEMENT_LINKPATH, String ADVERTISEMENT_DEADLINE) {
        this.ADVERTISEMENT_IMAGEPATH = ADVERTISEMENT_IMAGEPATH;
        this.ADVERTISEMENT_LINKPATH = ADVERTISEMENT_LINKPATH;
        this.ADVERTISEMENT_DEADLINE = ADVERTISEMENT_DEADLINE;
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

    public String getADVERTISEMENT_DEADLINE() {
        return ADVERTISEMENT_DEADLINE;
    }

    public void setADVERTISEMENT_DEADLINE(String ADVERTISEMENT_DEADLINE) {
        this.ADVERTISEMENT_DEADLINE = ADVERTISEMENT_DEADLINE;
    }
}
