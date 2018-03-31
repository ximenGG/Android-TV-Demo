package com.iptv.hq.ui.fragment;

import android.os.Bundle;
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
import com.iptv.hq.presenter.KulePresenter;
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
 * Created by ximen on 2018/1/8.
 */
public class KuleFragment extends BaseFragment<KuleModel, RequestView, KulePresenter> implements RequestView, View.OnClickListener {
    private RelativeLayout rMainLay;
    @Override
    public int createLayout() {
        return R.layout.fragment_kule;
    }

    @Override
    public KulePresenter createPresenter() {
        return new KulePresenter();
    }

    @Override
    public KuleModel createModel() {
        return new KuleModel();
    }

    @Override
    protected void initData() {
        mPresenter.show();
    }

    @Override
    public void init(View view) {
        rMainLay = (RelativeLayout) view.findViewById(R.id.r_main_lay);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        rMainLay.getChildAt(0).requestFocus();
    }

    @Override
    public void onFocusChanged(View oldFocus, View newFocus) {

    }

    @Override
    public void onResponse(Call call, Bean bean, int id) {
        if (id == 0) {
            KuLeBean kuLeModel = (KuLeBean) bean;
            for (int i = 0; i < rMainLay.getChildCount(); i++) {
                ImageView imageView = (ImageView) rMainLay.getChildAt(i);
                if (imageView == null) return;
                imageView.setOnClickListener(this);
                if (i == 0) {
                    Glide.with(imageView.getContext())
                            .load(R.mipmap.klk_house_point)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            .into(imageView);
                } else if (i == 1) {
                    Glide.with(imageView.getContext())
                            .load(R.mipmap.klk_house_point_2)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            .into(imageView);
                } else if (i == 2) {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getPagerecs().get(0).getImageVA())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            .into(imageView);
                } else if (i == 3) {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getPagerecs().get(1).getImageVA())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            .into(imageView);
                } else {
                    Glide.with(imageView.getContext())
                            .load(Properties.HTTP.PIC_URL + kuLeModel.getLayrecs().get(i - 4).getImageVA())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
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
        EventManager.getInstance().invokeEvent("loadFragment",OnlineFragment.newInstance(),true);
    }

    public static KuleFragment newInstance() {
        Bundle args = new Bundle();
        KuleFragment fragment = new KuleFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
