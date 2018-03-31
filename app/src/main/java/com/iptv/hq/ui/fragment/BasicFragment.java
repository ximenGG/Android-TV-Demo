package com.iptv.hq.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.iptv.SupportFragment;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.ui.views.LoadingPage;

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
public abstract class BasicFragment extends SupportFragment implements ViewTreeObserver.OnGlobalFocusChangeListener {
    private LoadingPage loadingPage;
    private View view;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = createView();
        initView(view);
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            protected View createView() {
                return view;
            }
        };
        setLoadingPage(loadingPage);
        return loadingPage;
    }

    /**
     * 设置加载页的样式
     *
     * @param loadingPage
     */
    public void setLoadingPage(LoadingPage loadingPage) {
    }


    @Override
    public void onResume() {
        super.onResume();
        loading();
    }

    public void loading() {
        loadingPage.loading();
    }

    public void nonet() {
        loadingPage.noNet();
    }

    public void complete() {
        loadingPage.complete();
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    public abstract View createView();

    public abstract void initView(View view);

    public View inflate(@LayoutRes int layout) {
        return LayoutInflater.from(AppOtt.mContext).inflate(layout, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        view.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
    }
}
