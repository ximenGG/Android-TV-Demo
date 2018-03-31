package com.iptv.hq.api;
import com.iptv.hq.bean.CollectBean;
import com.iptv.hq.bean.HistoryBean;
import com.iptv.hq.bean.KuLeBean;
import com.iptv.hq.bean.MainBean;
import com.iptv.hq.bean.OnlineBean;
import com.iptv.hq.bean.PlayInfosBean;
import com.iptv.hq.bean.PlayListBean;
import com.iptv.hq.bean.PlayResBean;
import com.iptv.hq.bean.Result_Page;
import com.iptv.hq.bean.Result_Pb;
import com.iptv.hq.common.Properties;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {
    /**
     * 获取ip的地理位置
     * @return
     */
    @GET(Properties.HTTP.NET_IP)
    Call<String> getNetIp();
    /**
     * 获取首页数据
     * {"code":"ad_lxyy_home_2_3"}
     * @param requestBody
     * @return
     */
    @POST(Properties.HTTP.GET_PAGE)
    Call<Result_Page<MainBean>> getMainPage(@Body RequestBody requestBody);

    /**
     * 获取卡拉OK的数据
     * {"code":"ad_lxyy_klok"}
     * @param requestBody
     * @return
     */
    @POST(Properties.HTTP.GET_PAGE)
    Call<Result_Page<KuLeBean>> getKulePage(@Body RequestBody requestBody);

    /**
     * 获取最新上线数据
     * {"code":"xs_sy_ott","userId":"MFZHb3c9336679880fa5","project":"lxyyad","pageSize":1000,"cur":1}
     * @param requestBody
     * @return
     */
    @POST(Properties.HTTP.GET_LIST_DETAIL)
    Call<Result_Pb<OnlineBean>> getOnlineDetail(@Body RequestBody requestBody);

    /**
     * 获取收藏数据
     * {"userId":"MFZHb3c9336679880fa5","project":"lxyyad","resType":1,"pageSize":6,"cur":1}
     * @param requestBody
     * @return
     */
    @POST(Properties.HTTP.GET_RES_LIST)
    Call<Result_Pb<CollectBean>> getCollectList(@Body RequestBody requestBody);

    /**
     * 获取播放历史数据
     * {"userId":"MFZHb3c9336679880fa5","project":"lxyyad","resType":1,"pageSize":6,"cur":1}
     * @param requestBody
     * @return
     */
    @POST(Properties.HTTP.GET_RES_LIST)
    Call<Result_Pb<HistoryBean>> getHistoryList(@Body RequestBody requestBody);

    /**
     * 获取播放列表数据
     * {"code":"ad_lxyy_rmgs","userId":"MFZHb3c9336679880fa5","project":"lxyyad","pageSize":1000,"cur":1}
     * @param requestBody
     * @return
     */
    @POST(Properties.HTTP.GET_LIST_DETAIL)
    Call<Result_Pb<PlayListBean>> getPlayList(@Body RequestBody requestBody);

    /**
     * 获取播放资源数据
     * {"resCode":"11007020000005","resType":1,"userId":"MFZHb3c9336679880fa5"}
     * @param requestBody
     * @return
     */
    @POST(Properties.HTTP.GET_PLAY_RES)
    Call<PlayResBean> getPlayRes(@Body RequestBody requestBody);

    /**
     * 解析播放资源数据
     * @return
     */
    @GET
    Call<PlayInfosBean> analysisPlayRes(@Url String url);
}
