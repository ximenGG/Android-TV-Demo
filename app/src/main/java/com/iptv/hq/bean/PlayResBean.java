package com.iptv.hq.bean;

/**
 * 　　    ()  　　  ()
 * 　　   ( ) 　　　( )
 * 　　   ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Created by HQ on 2017/11/3.
 */
public class PlayResBean implements Bean {


    /**
     * streamNo : 00000W7Paq6sSm741183
     * code : 10000000
     * text : 成功
     * playres : {"code":"11007020000005","mediaType":1,"playurl":"4f1f8cab4f19435fb2d9a5812c2e66f2","lrc":null,"allTime":281,"pfCode":"000"}
     */

    private String streamNo;
    private int code;
    private String text;
    private PlayresBean playres;

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

    public PlayresBean getPlayres() {
        return playres;
    }

    public void setPlayres(PlayresBean playres) {
        this.playres = playres;
    }

    public static class PlayresBean {
        /**
         * code : 11007020000005
         * mediaType : 1
         * playurl : 4f1f8cab4f19435fb2d9a5812c2e66f2
         * lrc : null
         * allTime : 281
         * pfCode : 000
         */

        private String code;
        private int mediaType;
        private String playurl;
        private Object lrc;
        private int allTime;
        private String pfCode;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getMediaType() {
            return mediaType;
        }

        public void setMediaType(int mediaType) {
            this.mediaType = mediaType;
        }

        public String getPlayurl() {
            return playurl;
        }

        public void setPlayurl(String playurl) {
            this.playurl = playurl;
        }

        public Object getLrc() {
            return lrc;
        }

        public void setLrc(Object lrc) {
            this.lrc = lrc;
        }

        public int getAllTime() {
            return allTime;
        }

        public void setAllTime(int allTime) {
            this.allTime = allTime;
        }

        public String getPfCode() {
            return pfCode;
        }

        public void setPfCode(String pfCode) {
            this.pfCode = pfCode;
        }
    }
}
