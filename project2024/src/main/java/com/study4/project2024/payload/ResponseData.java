package com.study4.project2024.payload;

public class ResponseData {

    private int statusCode = 200;
    private String desc = "";
    private Object data;

    public ResponseData() {
    }

    public ResponseData(String desc) {
        this.desc = desc;
    }

    public ResponseData(String desc, int statusCode) {
        this.desc = desc;
        this.statusCode = statusCode;
    }

    public ResponseData(Object data) {
        this.data = data;
    }

    public ResponseData(Object data, String desc) {
        this.desc = desc;
        this.data = data;
    }

    public ResponseData(Object data, String desc, int statusCode) {
        this.desc = desc;
        this.data = data;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}