package com.iptv.hq.ui.views.dynamicbg;

/**
 * Created by HQ on 2017/3/5.
 */

public class IncompatibleRatioException extends RuntimeException {

    public IncompatibleRatioException() {
        super("Can't perform Ken Burns effect on rects with distinct aspect ratios!");
    }
}
