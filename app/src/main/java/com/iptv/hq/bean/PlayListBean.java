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
public class PlayListBean implements Bean {
    /**
     * cur : 1
     * dataList : [{"artistCode":"60000000706","artistName":"刘德华","code":"11007020000005","flag":0,"hisSort":0,"image":"","name":"爱你一万年","sort":1},{"artistCode":"60000000879","artistName":"蔡琴","code":"11007020000316","flag":0,"hisSort":0,"image":"","name":"被遗忘的时光","sort":2},{"artistCode":"60000000039","artistName":"张学友","code":"11007020000971","flag":0,"hisSort":0,"image":"","name":"饿狼传说","sort":3},{"artistCode":"60000000831","artistName":"邓紫棋","code":"11007020000539","flag":1,"hisSort":0,"image":"","name":"喜欢你","sort":4},{"artistCode":"60000000024","artistName":"林俊杰","code":"11007020000678","flag":0,"hisSort":0,"image":"","name":"可惜没如果","sort":5},{"artistCode":"60000000878","artistName":"蔡依林","code":"11007020000340","flag":0,"hisSort":0,"image":"","name":"爱情三十六计","sort":6},{"artistCode":"60000000011","artistName":"陈奕迅","code":"11007020000151","flag":0,"hisSort":0,"image":"","name":"K歌之王","sort":7},{"artistCode":"60000000647","artistName":"王菲","code":"11007020001300","flag":0,"hisSort":0,"image":"","name":"但愿人长久","sort":8},{"artistCode":"60000000485","artistName":"张靓颖","code":"11007020000782","flag":0,"hisSort":0,"image":"","name":"画心","sort":9},{"artistCode":"60000000048","artistName":"TFboys","code":"11002010000013","flag":0,"hisSort":0,"image":"","name":"宠爱","sort":10},{"artistCode":"60000002817","artistName":"邓超","code":"11007020000655","flag":0,"hisSort":0,"image":"","name":"超级英雄","sort":11},{"artistCode":"60000002153","artistName":"朴树","code":"11009010001902","flag":0,"hisSort":0,"image":"","name":"平凡之路","sort":12}]
     * first : 1
     * last : 1
     * next : 1
     * pageSize : 1000
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
         * artistCode : 60000000706
         * artistName : 刘德华
         * code : 11007020000005
         * flag : 0
         * hisSort : 0
         * image :
         * name : 爱你一万年
         * sort : 1
         */

        private String artistCode;
        private String artistName;
        private String code;
        private int flag;
        private int hisSort;
        private String image;
        private String name;
        private int sort;

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

        public int getHisSort() {
            return hisSort;
        }

        public void setHisSort(int hisSort) {
            this.hisSort = hisSort;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
