package com.iptv.hq.utils;

import com.iptv.hq.common.AppOtt;

public class HandlerUtils {
    /**
     * 在主线程执行一段任务
     * @param r
     */
    public static void runOnMainThread(Runnable r) {
        runOnMainThread(r, 0);
    }

    public static void runOnMainThread(Runnable r, int time) {
        AppOtt.mainHandler.postDelayed(r, time);
    }

    public static void removeOnMainCallback(Runnable r) {
        AppOtt.mainHandler.removeCallbacks(r);
    }

}
