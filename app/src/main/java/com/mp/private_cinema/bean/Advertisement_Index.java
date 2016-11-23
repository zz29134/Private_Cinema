package com.mp.private_cinema.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/23
 * 说明
 */

public class Advertisement_Index extends DataSupport {

    private String Advertisement_ID;
    private String Advertisement_ImagePath;
    private String Advertisement_LinkPath;
    private String Advertisement_DeadLine;

    public Advertisement_Index() {
    }

    public String getAdvertisement_ID() {
        return Advertisement_ID;
    }

    public void setAdvertisement_ID(String advertisement_ID) {
        Advertisement_ID = advertisement_ID;
    }

    public String getAdvertisement_ImagePath() {
        return Advertisement_ImagePath;
    }

    public void setAdvertisement_ImagePath(String advertisement_ImagePath) {
        Advertisement_ImagePath = advertisement_ImagePath;
    }

    public String getAdvertisement_LinkPath() {
        return Advertisement_LinkPath;
    }

    public void setAdvertisement_LinkPath(String advertisement_LinkPath) {
        Advertisement_LinkPath = advertisement_LinkPath;
    }

    public String getAdvertisement_DeadLine() {
        return Advertisement_DeadLine;
    }

    public void setAdvertisement_DeadLine(String advertisement_DeadLine) {
        Advertisement_DeadLine = advertisement_DeadLine;
    }
}
