package com.iptv.hq.model;

import com.iptv.hq.api.ApiHelper;
import com.iptv.hq.bean.KuLeBean;
import com.iptv.hq.bean.Result_Page;
import com.iptv.hq.view.RequestView;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.utils.HttpParams;
import com.iptv.hq.utils.ToastUtils;

import retrofit2.Callback;
import retrofit2.Response;

public class KuleModel extends IModel{
    public void getPage(final RequestView requestView) {
        ApiHelper.getApi()
                .getKulePage(ApiHelper.createBody(new HttpParams().put("code", "ad_lxyy_klok")))
                .enqueue(new Callback<Result_Page<KuLeBean>>() {
                    @Override
                    public void onResponse(retrofit2.Call<Result_Page<KuLeBean>> call, Response<Result_Page<KuLeBean>> response) {
                        if (response == null) {
                            ToastUtils.toast(AppOtt.mContext, "天哪噜~您已进入网络异次元世界！\\r\\n 请检查您的网络是否正常。");
                            return;
                        }
                        requestView.onResponse(call, response.body().getPage(), 0);
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Result_Page<KuLeBean>> call, Throwable throwable) {
                        requestView.onFailure(call, throwable, 0);
                    }
                });

    }
}
