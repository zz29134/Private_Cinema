package com.mp.private_cinema.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/23
 * 说明
 */

public class Bean_Advertisement_Index extends DataSupport {

    private long id;
    @Column(unique = true)
    private String ADVERTISEMENT_ID;
    private String ADVERTISEMENT_IMAGEPATH;
    private String ADVERTISEMENT_LINKPATH;
    private String ADVERTISEMENT_DEADLINE;
    private byte[] ADVERTISEMENT_IMAGE;

    public Bean_Advertisement_Index() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getADVERTISEMENT_ID() {
        return ADVERTISEMENT_ID;
    }

    public void setADVERTISEMENT_ID(String ADVERTISEMENT_ID) {
        this.ADVERTISEMENT_ID = ADVERTISEMENT_ID;
    }

    public String getADVERTISEMENT_IMAGEPATH() {
        return ADVERTISEMENT_IMAGEPATH;
    }

    public void setADVERTISEMENT_IMAGEPATH(String ADVERTISEMENT_IMAGEPATH) {
        this.ADVERTISEMENT_IMAGEPATH = ADVERTISEMENT_IMAGEPATH;
    }

    public String getADVERTISEMENT_LINKPATH() {
        return ADVERTISEMENT_LINKPATH;
    }

    public void setADVERTISEMENT_LINKPATH(String ADVERTISEMENT_LINKPATH) {
        this.ADVERTISEMENT_LINKPATH = ADVERTISEMENT_LINKPATH;
    }

    public String getADVERTISEMENT_DEADLINE() {
        return ADVERTISEMENT_DEADLINE;
    }

    public void setADVERTISEMENT_DEADLINE(String ADVERTISEMENT_DEADLINE) {
        this.ADVERTISEMENT_DEADLINE = ADVERTISEMENT_DEADLINE;
    }

    public byte[] getADVERTISEMENT_IMAGE() {
        return ADVERTISEMENT_IMAGE;
    }

    public void setADVERTISEMENT_IMAGE(byte[] ADVERTISEMENT_IMAGE) {
        this.ADVERTISEMENT_IMAGE = ADVERTISEMENT_IMAGE;
    }
}
