package com.iptv.hq.bean;

public class Result_Pb<T>{


    /**
     * code : 10000000
     * list : {"code":"xs_sy_ott","des":"","freeFlag":0,"name":"小狮音乐安卓首页","resType":1}
     * streamNo : 00000VQJsjJZAH786855
     * text : 成功
     */

    private int code;
    private ListBean list;
    private T pb;
    private String streamNo;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public T getPb() {
        return pb;
    }

    public void setPb(T pb) {
        this.pb = pb;
    }

    public String getStreamNo() {
        return streamNo;
    }

    public void setStreamNo(String streamNo) {
        this.streamNo = streamNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static class ListBean {
        /**
         * code : xs_sy_ott
         * des :
         * freeFlag : 0
         * name : 小狮音乐安卓首页
         * resType : 1
         */

        private String code;
        private String des;
        private int freeFlag;
        private String name;
        private int resType;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getFreeFlag() {
            return freeFlag;
        }

        public void setFreeFlag(int freeFlag) {
            this.freeFlag = freeFlag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getResType() {
            return resType;
        }

        public void setResType(int resType) {
            this.resType = resType;
        }
    }
}
