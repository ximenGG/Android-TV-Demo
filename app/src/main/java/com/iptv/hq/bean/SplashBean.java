package com.iptv.hq.bean;


/**
* Created by HQ on 2017/10/15
*/

public class SplashBean implements Bean {

    /**
     * cip : 183.54.192.49
     * cid : 440000
     * cname : 广东省
     */

    private String cip;
    private String cid;
    private String cname;

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}