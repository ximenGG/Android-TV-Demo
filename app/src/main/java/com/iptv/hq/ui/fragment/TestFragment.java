package com.iptv.hq.ui.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.iptv.hq.R;
import com.iptv.hq.bean.Bean;
import com.iptv.hq.bean.KuLeBean;
import com.iptv.hq.common.Properties;
import com.iptv.hq.model.KuleModel;
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
 * Created by ximen on 2018/1/16.
 */
public class TestFragment extends BasicFragment implements RequestView {
    KuleModel kuleModel = new KuleModel();
    private RelativeLayout rMainLay;
    @Override
    public View createView() {
        return inflate(R.layout.fragment_kule);
    }

    @Override
    public void onResume() {
        super.onResume();
        kuleModel.getPage(this);
    }

    @Override
    public void initView(View view) {
        rMainLay = (RelativeLayout) view.findViewById(R.id.r_main_lay);
    }
    @Override
    public void onResponse(Call call, Bean bean, int id) {
        if (id == 0) {
            KuLeBean kuLeModel = (KuLeBean) bean;
            for (int i = 0; i < rMainLay.getChildCount(); i++) {
                ImageView imageView = (ImageView) rMainLay.getChildAt(i);
                if (imageView == null) return;
                //imageView.setOnClickListener(this);
                if (i == 0) {
                    Glide.with(imageView.getContext())
                            .load(R.mipmap.klk_house_point)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
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
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(imageView);
                } else if (i == 3) {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getPagerecs().get(1).getImageVA())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(imageView);
                } else {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getLayrecs().get(i - 4).getImageVA())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            //.apply(RequestOptions.skipMemoryCacheOf(true))
                            .into(imageView);
                }
            }
        }
        complete();
        rMainLay.getChildAt(0).requestFocus();

    }

    @Override
    public void onFailure(Call call, Throwable throwable, int id) {

    }

    public static TestFragment newInstance() {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
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
