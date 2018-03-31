package com.iptv.hq.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.iptv.hq.R;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.bean.MainBean;
import com.iptv.hq.common.Properties;
import com.iptv.hq.model.MainModel;
import com.iptv.hq.presenter.MainPresenter;
import com.iptv.hq.view.RequestView;
import com.iptv.event.EventManager;

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
 * Created by HQ on 2018/1/3.
 */
public class MainFragment extends BaseFragment<MainModel, RequestView, MainPresenter> implements RequestView, View.OnClickListener {
    private RelativeLayout rMainLay;
    @Override
    public int createLayout() {
        return R.layout.fragment_test;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public MainModel createModel() {
        return new MainModel();
    }

    @Override
    protected void initData() {
        mPresenter.show();
    }

    @Override
    public void init(View view) {
        rMainLay = (RelativeLayout) view.findViewById(R.id.r_main_lay);
    }

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResponse(Call call, Bean bean, int id) {
        if (id == 0 && rMainLay != null) {
            MainBean mainBean = (MainBean) bean;
            for (int i = 0; i < rMainLay.getChildCount(); i++) {
                ImageView imageView = (ImageView) rMainLay.getChildAt(i);
                imageView.setOnClickListener(this);
                if (imageView != null) {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + mainBean.getLayrecs().get(i).getImageVA())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            .apply(RequestOptions.skipMemoryCacheOf(true))
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imageView);
                }
            }

        }
    }

    @Override
    public void onFailure(Call call, Throwable throwable, int id) {

    }

    @Override
    public void onClick(View v) {
        if (v == rMainLay.getChildAt(0)) {
            EventManager.getInstance().invokeEvent("loadFragment", KuleFragment.newInstance(), true);
        }


    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        rMainLay.getChildAt(4).requestFocus();
    }

    @Override
    public void onFocusChanged(View oldFocus, View newFocus) {
    }


}
