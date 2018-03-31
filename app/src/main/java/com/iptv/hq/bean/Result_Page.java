package com.iptv.hq.bean;

public class Result_Page<T>{

    private String streamNo;
    private int code;
    private String text;
    private T page;

    public String getStreamNo() {
        return streamNo;
    }

    public void setStreamNo(String streamNo) {
        this.streamNo = streamNo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public T getPage() {
        return page;
    }

    public void setPage(T page) {
        this.page = page;
    }

}
