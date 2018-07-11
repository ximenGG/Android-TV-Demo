package com.iptv.hq;
public class Config {
    static {
        System.loadLibrary("host");
    }

    public static native String host();

    public static native String picHost();
}
