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
public class OnlineBean implements Bean {
    /**
     * cur : 1
     * dataList : [{"artistCode":"60000000044","artistName":"周杰伦","code":"11006010000424","flag":1,"hisSort":0,"name":"阳明山","sort":2},{"artistCode":"60000000044","artistName":"周杰伦","code":"11009010002015","flag":1,"hisSort":0,"name":"听见下雨的声音","sort":4},{"artistCode":"60000002236","artistName":"乐童音乐家","code":"11009010002130","flag":0,"hisSort":0,"name":"Give Love","sort":5},{"artistCode":"60000002242","artistName":"LadyGaGa","code":"11009010002158","flag":0,"hisSort":0,"name":"G·U·Y","sort":6},{"artistCode":"60000000650","artistName":"田馥甄","code":"11009010002150","flag":0,"hisSort":0,"name":"矛盾","sort":7},{"artistCode":"60000000647","artistName":"王菲","code":"11009010002148","flag":0,"hisSort":0,"name":"我愿意","sort":8},{"artistCode":"60000000044","artistName":"周杰伦","code":"11002020000260","flag":0,"hisSort":0,"name":"安静","sort":9},{"artistCode":"60000000044","artistName":"周杰伦","code":"11002020000264","flag":0,"hisSort":0,"name":"枫","sort":11},{"artistCode":"60000000044","artistName":"周杰伦","code":"11009010002014","flag":0,"hisSort":0,"name":"天涯过客","sort":12},{"artistCode":"60000000044","artistName":"周杰伦","code":"11007020001192","flag":0,"hisSort":0,"name":"超跑女神","sort":15},{"artistCode":"60000000044","artistName":"周杰伦","code":"11007020001200","flag":0,"hisSort":0,"name":"公公偏头痛","sort":17},{"artistCode":"60000000027","artistName":"鹿晗","code":"11009010001891","flag":0,"hisSort":0,"name":"我们的明天高","sort":21},{"artistCode":"60000000049","artistName":"薛之谦","code":"11006010000743","flag":0,"hisSort":0,"name":"绅士","sort":32},{"artistCode":"60000000044","artistName":"周杰伦","code":"11009010002017","flag":0,"hisSort":0,"name":"鞋子特大号","sort":34},{"artistCode":"60000000044","artistName":"周杰伦","code":"11002020000262","flag":0,"hisSort":0,"name":"搁浅","sort":35}]
     * first : 1
     * last : 1
     * next : 1
     * pageSize : 1000
     * pre : 1
     * start : 0
     * totalCount : 15
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
         * artistCode : 60000000044
         * artistName : 周杰伦
         * code : 11006010000424
         * flag : 1
         * hisSort : 0
         * name : 阳明山
         * sort : 2
         */

        private String artistCode;
        private String artistName;
        private String code;
        private int flag;
        private int hisSort;
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
