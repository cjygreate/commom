package com.verse.common.data;

import java.io.Serializable;

public class VerseResponse implements Serializable {

    private int code;

    private String msg;

    private Object object;

    public VerseResponse() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public VerseResponse success() {
        this.code = 200;
        this.msg = "成功";
        return this;
    }

    public VerseResponse success(Object o) {
        this.code = 200;
        this.msg = "成功";
        this.object = o;
        return this;
    }

    public VerseResponse fail(int code, String mes) {
        this.code = code;
        this.msg = mes;
        return this;
    }

}
