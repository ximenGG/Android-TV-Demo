package com.iptv.hq.bean.dao;

import android.content.Context;

import com.iptv.hq.bean.VideoBean;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.utils.ThreadPool;

import java.util.ArrayList;
import java.util.List;

/**
 * 　　  　　温馨提示您
 * ━━━━ 珍 爱 生 命 ━━━━━━
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
 * ━━━━ 远 离 代 码 ━━━━━━
 * Created by HQ on 2017/8/1.
 */
public class GreenDaoUtil {
    private static DaoSession daoSession;
    private final static String DATA_BASE_NAME = "lxyy.db";

    public synchronized static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(context, DATA_BASE_NAME).getWritableDb()).newSession();
        }
        return daoSession;
    }

    public synchronized static void insert(final VideoBean videoBean) {
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                VideoBean unique = GreenDaoUtil.getDaoSession(AppOtt.mContext).getVideoBeanDao().queryBuilder().where(VideoBeanDao.Properties.ResCode.eq(videoBean.getResCode())).build().unique();
                if (unique == null || !unique.getIsPlay()) {//对象为空，数据错误
                    getDaoSession(AppOtt.mContext).getVideoBeanDao().insertOrReplace(videoBean);
                }
            }
        });

    }

    public synchronized static void update(final VideoBean videoBean) {
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                VideoBean unique = GreenDaoUtil.getDaoSession(AppOtt.mContext).getVideoBeanDao().queryBuilder().where(VideoBeanDao.Properties.ResCode.eq(videoBean.getResCode())).build().unique();
                if (unique != null && unique.getIsPlay()) {//对象不为空，可以播放的资源
                    getDaoSession(AppOtt.mContext).getVideoBeanDao().update(videoBean);
                }
            }
        });

    }

    public synchronized static List<VideoBean> queryList(final List<String> list) {
        List<VideoBean> videoBeen = new ArrayList<VideoBean>();
        for (int i = 0; i < list.size(); i++) {
            videoBeen.addAll(GreenDaoUtil.getDaoSession(AppOtt.mContext).getVideoBeanDao().queryBuilder().where(VideoBeanDao.Properties.ResCode.eq(list.get(i))).build().list());
        }
        return videoBeen;
    }
}
