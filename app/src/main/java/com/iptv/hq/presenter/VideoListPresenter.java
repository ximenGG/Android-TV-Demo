package com.iptv.hq.presenter;

import com.iptv.hq.bean.PlayListBean;
import com.iptv.hq.view.RequestViews;
import com.iptv.hq.model.VideoListModel;

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
 * Created by HQ on 2017/12/28.
 */
public class VideoListPresenter extends IPresenter<RequestViews, VideoListModel> {

    public void showPlayList(String code) {
        getIModel().getPlayList(code, getIView());
    }

    public void analysisList(List<PlayListBean.DataListBean> dataList) {
        getIModel().analysisList(dataList, getIView());
    }
}
