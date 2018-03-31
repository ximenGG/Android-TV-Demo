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
 * Created by HQ on 2017/11/17.
 */
public class PlayListBody {
    private String code;
    private String userId;
    private String project;
    private int pageSize;
    private int cur;

    public PlayListBody(Build build) {
        this.code = build.code;
        this.userId = build.userId;
        this.project = build.project;
        this.pageSize = build.pageSize;
        this.cur = build.cur;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    public static class Build {
        private String code;
        private String userId;
        private String project;
        private int pageSize;
        private int cur;


        public Build code(String code) {
            this.code = code;
            return this;
        }


        public Build userId(String userId) {
            this.userId = userId;
            return this;
        }


        public Build project(String project) {
            this.project = project;
            return this;
        }


        public Build pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }


        public Build cur(int cur) {
            this.cur = cur;
            return this;
        }

        public PlayListBody build() {
            return new PlayListBody(this);
        }
    }
}
