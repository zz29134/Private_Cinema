package com.mp.pc_library.bean;

import com.google.gson.JsonElement;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/2 <br/>
 * 说明
 */

public class ResponseData {

    private String Code;
    private String State;
    private JsonElement Content;

    public ResponseData() {
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public JsonElement getContent() {
        return Content;
    }

    public void setContent(JsonElement content) {
        Content = content;
    }
}
