package com.iptv.hq.model;

import com.iptv.hq.api.ApiHelper;
import com.iptv.hq.view.RequestViews;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.common.UrlRunnable;
import com.iptv.hq.bean.PlayListBody;
import com.iptv.hq.bean.PlayListBean;
import com.iptv.hq.bean.Result_Pb;
import com.iptv.hq.bean.VideoInfo;
import com.iptv.hq.utils.JsonUtil;
import com.iptv.hq.utils.ThreadPool;
import com.iptv.hq.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;
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
 * Created by HQ on 2017/12/5.
 */
public class VideoListModel extends IModel{
    private static int SUCCESS = 0;
    private static int ERROR = 0;
    private List<VideoInfo> videoInfos = new ArrayList<>();

    public void getPlayList(String code, final RequestViews requstView) {
        PlayListBody playListBody = new PlayListBody.Build()
                .code(code)
                .userId("MFZHb3c9336679880fa5")
                .project("lxyyad")
                .pageSize(1000)
                .cur(1)
                .build();
        ApiHelper.getApi().getPlayList(ApiHelper.createBody(JsonUtil.parseBeanToJson(playListBody))).enqueue(new Callback<Result_Pb<PlayListBean>>() {
            @Override
            public void onResponse(Call<Result_Pb<PlayListBean>> call, Response<Result_Pb<PlayListBean>> response) {
                if (response == null) {
                    ToastUtils.toast(AppOtt.mContext, "天哪噜~您已进入网络异次元世界！\\r\\n 请检查您的网络是否正常。");
                    return;
                }
                requstView.onResponse(call, response.body().getPb(), 0);

            }

            @Override
            public void onFailure(Call<Result_Pb<PlayListBean>> call, Throwable throwable) {
                requstView.onFailure(call, throwable, 0);
            }
        });
    }

    /**
     * 批量解析播放
     * @param requestViews
     */
    public void analysisList(final List<PlayListBean.DataListBean> dataList, final RequestViews requestViews) {
        for (int i = 0; i < dataList.size(); i++) {
            VideoInfo videoInfo = new VideoInfo();
            PlayListBean.DataListBean data = dataList.get(i);
            videoInfo.setPosition(i);
            videoInfo.setResCode(data.getCode());
            videoInfo.setVideoType("1");
            videoInfo.setTitle(data.getName() + "-" + data.getArtistName());
            ThreadPool.execute(new UrlRunnable("MFZHb3c9336679880fa5", videoInfo) {
                @Override
                public void onSuccess(VideoInfo videoInfo) {
                    videoInfos.add(videoInfo);
                    SUCCESS++;
                    if (SUCCESS + ERROR == dataList.size()) {
                        SUCCESS = 0;
                        ERROR = 0;
                        requestViews.onVideoList(videoInfos);
                    }
                }

                @Override
                public void onError(VideoInfo videoInfo) {
                    ERROR++;
                    if (SUCCESS + ERROR == dataList.size()) {
                        SUCCESS = 0;
                        ERROR = 0;
                        requestViews.onVideoList(videoInfos);
                    }
                }
            });
        }
    }

}
