package com.iptv.hq.ui.views.exoplayer;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

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
 * Created by HQ on 2017/10/28.
 */
public class ScTextview extends TextView {
    public ScTextview(Context context) {
        super(context);
    }

    public ScTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isFocused() {
        return true;
    }

    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
    }
}