package com.iptv.hq.ui.views.dynamicbg;

import android.graphics.RectF;

/**
 * Created by HQ on 2017/3/5.
 */

public interface TransitionGenerator {


    public Transition generateNextTransition(RectF drawableBounds, RectF viewport);

}
