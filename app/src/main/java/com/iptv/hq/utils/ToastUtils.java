package com.iptv.hq.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;

    /**
     * 弹出自定义显示时间Toast
     */
    public static void toast(Context context, String toastStr, int duration) {
        Toast.makeText(context, toastStr, duration).show();
    }

    /**
     * 弹出Toast提示
     */
    public static void toast(Context cxt, String str) {
        if (toast == null) {
            toast = Toast.makeText(cxt, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

}
