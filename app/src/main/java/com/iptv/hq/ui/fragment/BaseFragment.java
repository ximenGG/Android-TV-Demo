package com.iptv.hq.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.iptv.SupportFragment;
import com.iptv.hq.model.IModel;
import com.iptv.hq.presenter.IPresenter;
import com.iptv.hq.view.IView;

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
 * Created by HQ on 2018/1/3.
 */
public abstract class BaseFragment<M extends IModel, V extends IView, P extends IPresenter<V, M>> extends SupportFragment implements ViewTreeObserver.OnGlobalFocusChangeListener {
    public P mPresenter;
    public M mModel;
    private View view;

    public void runOnUiThread(Runnable paramRunnable) {
        if (isAdded() && !isDetached()) _mActivity.runOnUiThread(paramRunnable);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = createModel();
        mPresenter = createPresenter();
        mPresenter.onAttach((V) this, mModel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), createLayout(), null);
        init(view);
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract P createPresenter();

    public abstract M createModel();

    protected abstract void initData();

    public abstract int createLayout();

    public abstract void init(View view);

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        onFocusChanged(oldFocus, newFocus);
    }

    public abstract void onFocusChanged(View oldFocus, View newFocus);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
        view.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return false;
    }
}
