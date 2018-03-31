package com.iptv.hq.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.iptv.Fragmentation;

import java.io.File;

import okhttp3.Cache;

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
 * Created by HQ on 2017/10/15.
 */
public class AppOtt extends Application {
    public static Context mContext;
    public static Handler mainHandler;//主线程的handler

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mainHandler = new Handler(Looper.getMainLooper());//主线程的handler
        Glide.get(this).setMemoryCategory(MemoryCategory.LOW);
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .install();

    }

    @Override
    public void onLowMemory() {
        Glide.get(this).onLowMemory();
        super.onLowMemory();
    }

    public static Cache getCache() {
        //缓存文件夹
        File cacheFile = new File(AppOtt.mContext.getExternalCacheDir().toString(), "cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        return new Cache(cacheFile, cacheSize);
    }
}
