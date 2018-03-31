package com.iptv.hq.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.iptv.hq.R;
import com.iptv.hq.common.Properties;
import com.iptv.hq.view.RequestView;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.bean.MainBean;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.model.MainModel;
import com.iptv.hq.presenter.MainPresenter;
import com.iptv.hq.ui.views.dynamicbg.DynamicViewBG;
import com.iptv.hq.utils.ToastUtils;
import java.util.List;

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
 * Created by HQ on 2017/10/15.
 */
public class MainActivity extends BaseActivity<MainModel, RequestView, MainPresenter> implements View.OnClickListener, RequestView {
    RelativeLayout rMainLay;
    List<MainBean.LayrecsBean> layrecs;
    private DynamicViewBG kenBurnsView;

    @Override
    protected MainModel createModel() {
        return new MainModel();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_main);
        kenBurnsView = (DynamicViewBG) findViewById(R.id.kb_bg);
        rMainLay = (RelativeLayout) findViewById(R.id.r_main_lay);
        Glide.with(kenBurnsView.getContext()).load("http://p3.so.qhimgs1.com/t01a1631bc7d7fdcef3.jpg").into(kenBurnsView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        kenBurnsView.resume();
        mPresenter.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        kenBurnsView.restart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        kenBurnsView.pause();
    }

    @Override
    public void onClick(View view) {
        if (layrecs == null || layrecs.size() == 0) return;
        switch (view.getId()) {
            case R.id.riv_1:
                startActivity(KuleActivity.class);
                overridePendingTransition(0, 0);
                break;
            case R.id.riv_2:
                startPlay(17);
                break;
            case R.id.riv_3:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                startActivity("com.iptv.app_lx.ui.activity.Test2Activity");
                break;
            case R.id.riv_4:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.riv_5:
                startPlay(4);
                break;
            case R.id.riv_6:
                startPlay(5);
                break;
            case R.id.riv_7:
                startPlay(6);
                break;
            case R.id.riv_8:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.riv_9:
                startPlay(8);
                break;
            case R.id.riv_10:
                startPlay(9);
                break;
            case R.id.riv_11:
                startPlay(10);
                break;
            case R.id.riv_12:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.riv_13:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.riv_14:
                startPlay(13);
                break;
            case R.id.riv_15:
                startPlay(14);
                break;
            case R.id.riv_16:
                startPlay(15);
                break;
            case R.id.riv_17:
                startPlay(16);
                break;
        }

    }

    public void startPlay(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("code", layrecs.get(position).getEleValue());
        startActivity(Test2Activity.class, bundle);
    }

    @Override
    protected void onDestroy() {
        Glide.get(this).trimMemory(TRIM_MEMORY_COMPLETE);
        super.onDestroy();
    }

    @Override
    public void onResponse(retrofit2.Call call, Bean model, int id) {
        if (id == 0) {
            MainBean mainModel = (MainBean) model;
            layrecs = mainModel.getLayrecs();
            for (int i = 0; i < rMainLay.getChildCount(); i++) {
                ImageView imageView = (ImageView) rMainLay.getChildAt(i);
                imageView.setOnClickListener(this);
                if (imageView != null) {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + mainModel.getLayrecs().get(i).getImageVA())
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imageView);
                }
            }

        }
    }

    @Override
    public void onFailure(retrofit2.Call call, Throwable throwable, int id) {

    }

}
