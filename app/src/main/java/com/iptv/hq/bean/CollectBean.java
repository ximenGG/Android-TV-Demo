package com.iptv.hq.bean;

import java.util.List;

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
public class CollectBean implements Bean {
    /**
     * cur : 1
     * dataList : [{"artistCode":"60000000116","artistName":"兔小贝","code":"11005010000357","flag":1,"name":"对牛弹琴"},{"artistCode":"60000000116","artistName":"兔小贝","code":"11005010000119","flag":1,"name":"把舞儿跳起来"},{"artistCode":"60000000116","artistName":"兔小贝","code":"11005010000286","flag":1,"name":"小龙人"},{"artistCode":"60000002129","artistName":"李易峰","code":"11009010001858","flag":1,"name":"剑心"},{"artistCode":"60000000039","artistName":"张学友","code":"11007020000241","flag":1,"name":"爱是永恒"},{"artistCode":"60000000831","artistName":"邓紫棋","code":"11007020000539","flag":1,"name":"喜欢你"},{"artistCode":"60000002475","artistName":"王菲&邓丽君","code":"11006010000627","flag":1,"name":"清平调"},{"artistCode":"60000000799","artistName":"古巨基","code":"11007020000132","flag":1,"name":"劲歌金曲"},{"artistCode":"60000000044","artistName":"周杰伦","code":"11006010000424","flag":1,"name":"阳明山"},{"artistCode":"60000000044","artistName":"周杰伦","code":"11009010002015","flag":1,"name":"听见下雨的声音"},{"artistCode":"60000000116","artistName":"兔小贝","code":"11005010000337","flag":1,"name":"狼来了"},{"artistCode":"60000000123","artistName":"佚名","code":"11009040000090","flag":1,"name":"船是怎样在水面行驶的"}]
     * first : 1
     * last : 1
     * next : 1
     * pageSize : 100
     * pre : 1
     * start : 0
     * totalCount : 12
     * totalPage : 1
     */

    private int cur;
    private int first;
    private int last;
    private int next;
    private int pageSize;
    private int pre;
    private int start;
    private int totalCount;
    private int totalPage;
    private List<DataListBean> dataList;

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * artistCode : 60000000116
         * artistName : 兔小贝
         * code : 11005010000357
         * flag : 1
         * name : 对牛弹琴
         */

        private String artistCode;
        private String artistName;
        private String code;
        private int flag;
        private String name;

        public String getArtistCode() {
            return artistCode;
        }

        public void setArtistCode(String artistCode) {
            this.artistCode = artistCode;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
