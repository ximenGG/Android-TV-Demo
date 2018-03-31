package com.iptv.hq.common;

import com.iptv.hq.Config;

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
 * Created by ximen on 2018/3/27.
 */
public interface Properties {

    interface Test {
        boolean DEBUG = false;
    }

    interface HTTP {
        String HTTP_URL = Config.host();
        String NET_IP = "http://pv.sohu.com/cityjson?ie=utf-8";
        String GET_PAGE = "API_ROP/page/get";
        String GET_LIST_DETAIL = "API_ROP/list/detail";
        String GET_RES_LIST = "API_ROP/user/store/get/reslist";
        String GET_PLAY_RES = "API_ROP/play/get/playres";
        String PIC_URL = Config.picHost();
    }
}
