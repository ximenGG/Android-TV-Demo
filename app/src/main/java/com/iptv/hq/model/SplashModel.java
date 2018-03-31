package com.iptv.hq.model;

import com.iptv.hq.common.AppOtt;
import com.iptv.hq.common.Properties;
import com.iptv.hq.view.RequestView;
import com.iptv.hq.bean.SplashBean;
import com.iptv.hq.utils.HandlerUtils;
import com.iptv.hq.utils.JsonUtil;
import com.iptv.hq.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HQ on 2017/10/15
 */

public class SplashModel extends IModel{
    public void getNetIp(final RequestView requestView) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Properties.HTTP.NET_IP)
                .get()
                .tag("")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestView.onFailure(null, null, 0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response == null) {
                    ToastUtils.toast(AppOtt.mContext,"天哪噜~您已进入网络异次元世界！\\r\\n 请检查您的网络是否正常。");
                    return;
                }
                String string = response.body().string();
                final String substring = string.substring(string.indexOf("{"), string.indexOf("}") + 1);
                HandlerUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        requestView.onResponse(null, JsonUtil.parseJsonToBean(substring, SplashBean.class), 0);
                    }
                });


            }
        });
    }
}