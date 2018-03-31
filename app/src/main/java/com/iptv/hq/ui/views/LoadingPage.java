package com.iptv.hq.ui.views;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.iptv.hq.R;
import com.iptv.hq.ui.views.indicators.Indicator;
import com.iptv.hq.utils.NetworkUtils;
import com.iptv.hq.view.LoadState;

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
 * Created by ximen on 2018/1/15.
 */
public abstract class LoadingPage extends FrameLayout implements View.OnClickListener,LoadState{

    private View loading;
    private View nonet;
    private View success;
    private Context context;
    private LoadingView loadingView;

    public LoadingPage(@NonNull Context context) {
        this(context, null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.loading_page, this);
        loadingView = (LoadingView) findViewById(R.id.loading_view);
        loading = view.findViewById(R.id.loading);
        nonet = view.findViewById(R.id.nonet);
        success = createView();
        addView(success, new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.findViewById(R.id.settings).setOnClickListener(this);
        loading();
    }

    protected abstract View createView();
    public void loading() {
        loading.setVisibility(VISIBLE);
        nonet.setVisibility(INVISIBLE);
        success.setVisibility(INVISIBLE);
        loading.bringToFront();
        if (!NetworkUtils.checkNetwork(context)) {//没有网络
            noNet();
        }
    }

    public void noNet() {
        nonet.setVisibility(VISIBLE);
        loading.setVisibility(INVISIBLE);
        success.setVisibility(INVISIBLE);
        nonet.bringToFront();
    }
    public void complete() {
        loading.setVisibility(INVISIBLE);
        nonet.setVisibility(INVISIBLE);
        success.setVisibility(VISIBLE);
        success.bringToFront();
    }

    public void setIndicator(Indicator d) {
        loadingView.setIndicator(d);
    }

    public void setIndicator(int indicator) {
        loadingView.setIndicator(indicator);
    }

    public void setIndicatorColor(int color) {
        loadingView.setIndicatorColor(color);
    }

    @Override
    public void onClick(View v) {
        context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }
}
