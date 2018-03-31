package com.iptv.hq.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.iptv.hq.common.AppOtt;
import com.iptv.hq.R;
import com.iptv.hq.view.RequestView;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.bean.SplashBean;
import com.iptv.hq.model.SplashModel;
import com.iptv.hq.presenter.SplashPresenter;
import com.iptv.hq.utils.ToastUtils;

public class SplashActivity extends BaseActivity<SplashModel, RequestView, SplashPresenter> implements RequestView {


    @Override
    protected SplashModel createModel() {
        return new SplashModel();
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        alphaAnimation(findViewById(R.id.splash));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void alphaAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(2000);
        view.startAnimation(alphaAnimation);
        alphaAnimation.start();
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onResponse(retrofit2.Call call, Bean model, int id) {
        if (id == 0) {
            SplashBean splashModel = (SplashBean) model;
            ToastUtils.toast(AppOtt.mContext, splashModel.getCname());
        }
    }

    @Override
    public void onFailure(retrofit2.Call call, Throwable throwable, int id) {

    }
}
