package com.iptv.hq.model;

import com.iptv.hq.common.AppOtt;
import com.iptv.hq.api.ApiHelper;
import com.iptv.hq.bean.Result_Page;
import com.iptv.hq.view.RequestView;
import com.iptv.hq.bean.MainBean;
import com.iptv.hq.utils.HttpParams;
import com.iptv.hq.utils.ToastUtils;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HQ on 2017/10/17
 */

public class MainModel extends IModel{

    public void getPage(final RequestView requestView) {
        ApiHelper.getApi()
                .getMainPage(ApiHelper.createBody(new HttpParams().put("code", "ad_lxyy_home_2_3")))
                .enqueue(new Callback<Result_Page<MainBean>>() {
            @Override
            public void onResponse(retrofit2.Call<Result_Page<MainBean>> call, Response<Result_Page<MainBean>> response) {
                if (response == null) {
                    ToastUtils.toast(AppOtt.mContext, "天哪噜~您已进入网络异次元世界！\\r\\n 请检查您的网络是否正常。");
                    return;
                }
                requestView.onResponse(call, response.body().getPage(), 0);
            }

            @Override
            public void onFailure(retrofit2.Call<Result_Page<MainBean>> call, Throwable throwable) {
                requestView.onFailure(call, throwable, 0);
            }
        });
    }

}