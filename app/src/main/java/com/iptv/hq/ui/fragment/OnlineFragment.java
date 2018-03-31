package com.iptv.hq.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.iptv.hq.R;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.bean.OnlineBean;
import com.iptv.hq.model.OnlineModel;
import com.iptv.hq.presenter.OnlinePresenter;
import com.iptv.hq.view.RequestView;

import retrofit2.Call;

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
 * Created by ximen on 2018/1/10.
 */
public class OnlineFragment extends BaseFragment<OnlineModel, RequestView, OnlinePresenter> implements RequestView {
    @Override
    public OnlinePresenter createPresenter() {
        return new OnlinePresenter();
    }

    @Override
    public OnlineModel createModel() {
        return new OnlineModel();
    }

    @Override
    protected void initData() {
        mPresenter.show("xs_sy_ott");
    }

    @Override
    public int createLayout() {
        return R.layout.fragment_online;
    }

    @Override
    public void init(View view) {

    }

    @Override
    public void onFocusChanged(View oldFocus, View newFocus) {

    }

    @Override
    public void onResponse(Call call, Bean bean, int id) {
        if (id == 0) {
            OnlineBean onlineBean = (OnlineBean) bean;
            System.out.println(onlineBean.getDataList());
        }
    }

    @Override
    public void onFailure(Call call, Throwable throwable, int id) {
    }

    public static OnlineFragment newInstance() {
        Bundle args = new Bundle();
        OnlineFragment fragment = new OnlineFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
