package com.iptv.hq.common;
import android.util.Log;
import com.iptv.hq.api.ApiHelper;
import com.iptv.hq.bean.PlayInfosBean;
import com.iptv.hq.bean.PlayResBean;
import com.iptv.hq.bean.VideoInfo;
import com.iptv.hq.utils.AnalysisUtils;
import com.iptv.hq.utils.HttpParams;
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
 * 　　　　  ┗┻┛　┗┻┛
 * Created by HQ on 2017/11/15.
 */
public abstract class UrlRunnable implements Runnable {
    private String userId;
    private VideoInfo videoInfo;

    public UrlRunnable(String userId, VideoInfo videoInfo) {
        this.userId = userId;
        this.videoInfo = videoInfo;
    }

    @Override
    public void run() {
        ApiHelper.getApi().getPlayRes(ApiHelper.createBody(new HttpParams()
                        .put("resCode", videoInfo.getResCode())
                        .put("resType", videoInfo.getVideoType())
                        .put("userId", userId)
                )
        ).enqueue(new Callback<PlayResBean>() {
            @Override
            public void onResponse(Call<PlayResBean> call, Response<PlayResBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().getPlayres() == null) {
                        videoInfo.setPlay(false);
                        onError(videoInfo);
                        return;
                    }
                    final String url = AnalysisUtils.generateOpenAPIURL(response.body().getPlayres().getPlayurl());
                    ApiHelper.getNoHeaderApi(Properties.HTTP.HTTP_URL).analysisPlayRes(url).enqueue(new Callback<PlayInfosBean>() {
                        @Override
                        public void onResponse(Call<PlayInfosBean> call, Response<PlayInfosBean> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getPlayInfoList() != null) {
                                    PlayInfosBean body = response.body();
                                    videoInfo.setPlayURL(body.getPlayInfoList().getPlayInfo().get(0).getPlayURL());
                                    //videoInfo.setPlayURL("http://202.99.114.93/88888891/16/20170309/269067607/269067607.ts");
                                    videoInfo.setCoverURL(body.getVideoBase().getCoverURL());
                                    videoInfo.setDuration((long) (Double.parseDouble(body.getVideoBase().getDuration())*1000));
                                    UrlRunnable.this.onSuccess(videoInfo);
                                    Log.d("UrlRunnable", "onResponse: "+videoInfo.getPlayURL());
                                } else {
                                    videoInfo.setPlay(false);
                                    onError(videoInfo);

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<PlayInfosBean> call, Throwable throwable) {
                            videoInfo.setPlay(false);
                            onError(videoInfo);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<PlayResBean> call, Throwable throwable) {
                videoInfo.setPlay(false);
                onError(videoInfo);
            }
        });
    }

    public abstract void onSuccess(VideoInfo videoInfo);

    public abstract void onError(VideoInfo videoInfo);
}
