package com.iptv.hq.model;

import com.iptv.hq.api.ApiHelper;
import com.iptv.hq.bean.OnlineBean;
import com.iptv.hq.bean.PlayListBody;
import com.iptv.hq.bean.Result_Pb;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.utils.JsonUtil;
import com.iptv.hq.utils.ToastUtils;
import com.iptv.hq.view.RequestView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
public class OnlineModel extends IModel {

    public void getOnlineData(final String code, final RequestView requstView) {
        PlayListBody playListBody = new PlayListBody.Build()
                .code(code)
                .userId("MFZHb3c9336679880fa5")
                .project("lxyyad")
                .pageSize(1000)
                .cur(1)
                .build();
        ApiHelper.getApi().getOnlineDetail(ApiHelper.createBody(JsonUtil.parseBeanToJson(playListBody))).enqueue(new Callback<Result_Pb<OnlineBean>>() {
            @Override
            public void onResponse(Call<Result_Pb<OnlineBean>> call, Response<Result_Pb<OnlineBean>> response) {
                if (response == null) {
                    ToastUtils.toast(AppOtt.mContext, "天哪噜~您已进入网络异次元世界！\\r\\n 请检查您的网络是否正常。");
                    return;
                }
                requstView.onResponse(call, response.body().getPb(), 0);
            }

            @Override
            public void onFailure(Call<Result_Pb<OnlineBean>> call, Throwable throwable) {
                getOnlineData(code,requstView);
            }
        });
    }
}
