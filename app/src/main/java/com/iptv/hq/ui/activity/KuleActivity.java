package com.iptv.hq.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.iptv.hq.R;
import com.iptv.hq.common.Properties;
import com.iptv.hq.view.RequestView;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.bean.KuLeBean;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.model.KuleModel;
import com.iptv.hq.presenter.KulePresenter;
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
 * Created by HQ on 2017/10/18.
 */
public class KuleActivity extends BaseActivity<KuleModel, RequestView, KulePresenter> implements RequestView, View.OnClickListener {
    private RelativeLayout rMainLayout;
    private List<KuLeBean.LayrecsBean> layrecs;
    private DynamicViewBG kenBurnsView;

    @Override
    protected KuleModel createModel() {
        return new KuleModel();
    }

    @Override
    protected KulePresenter createPresenter() {
        return new KulePresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_kule);
        kenBurnsView = (DynamicViewBG) findViewById(R.id.kb_bg);
        rMainLayout = (RelativeLayout) findViewById(R.id.r_main_lay);
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
        if (layrecs == null) return;
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.item_1:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.item_2:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.item_3:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.item_4:
                ToastUtils.toast(AppOtt.mContext, "该功能暂未完成");
                break;
            case R.id.item_5:
                bundle.putString("code", layrecs.get(0).getEleValue());
                startActivity(Test2Activity.class, bundle);
                break;
            case R.id.item_6:
                bundle.putString("code", layrecs.get(1).getEleValue());
                startActivity(Test2Activity.class, bundle);
                break;
            case R.id.item_7:
                bundle.putString("code", layrecs.get(2).getEleValue());
                break;
            case R.id.item_8:
                bundle.putString("code", layrecs.get(3).getEleValue());
                startActivity(Test2Activity.class, bundle);
                break;
            case R.id.item_9:
                bundle.putString("code", layrecs.get(4).getEleValue());
                startActivity(Test2Activity.class, bundle);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        if (!isMainThread()) {
            Glide.with(this).pauseRequests();
        }
        Glide.get(this).trimMemory(TRIM_MEMORY_COMPLETE);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.FLAG_KEEP_TOUCH_MODE) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResponse(retrofit2.Call call, Bean model, int id) {
        if (id == 0) {
            KuLeBean kuLeModel = (KuLeBean) model;
            layrecs = kuLeModel.getLayrecs();
            for (int i = 0; i < rMainLayout.getChildCount(); i++) {
                ImageView imageView = (ImageView) rMainLayout.getChildAt(i);
                if (imageView == null) return;
                imageView.setOnClickListener(this);
                if (i == 0) {
                    Glide.with(imageView.getContext())
                            .load(R.mipmap.klk_house_point)
                            //.apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            .into(imageView);
                } else if (i == 1) {
                    Glide.with(imageView.getContext())
                            .load(R.mipmap.klk_house_point_2)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(imageView);
                } else if (i == 2) {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getPagerecs().get(0).getImageVA())
                            // .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(imageView);
                } else if (i == 3) {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getPagerecs().get(1).getImageVA())
                            //.apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(imageView);
                } else {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getLayrecs().get(i - 4).getImageVA())
                            //.apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(imageView);
                }
            }
        }
    }

    @Override
    public void onFailure(retrofit2.Call call, Throwable throwable, int id) {

    }

}
